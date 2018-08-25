using CRC;
using dsrUtil;
using dsrUtil.constant;
using dsrUtil.GT06Protocol;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using utils;

namespace srv_receive_data.source
{
    public class readGPRSDataThread
    {
        private Log objLog;
        private int socketPort;
        public readGPRSDataThread(Log log, int port)
        {
            objLog = log;
            socketPort = port;
        }
        public void Call()
        {
            System.Threading.ThreadStart ts =
            new System.Threading.ThreadStart(Execute);
            System.Threading.Thread t =
                new System.Threading.Thread(ts);
            t.IsBackground = true;
            t.Start();
        }
        private void Execute()
        {
            // Data buffer for incoming data.
            byte[] bytes = new Byte[1024];

            // Establish the local endpoint for the socket.
            // Dns.GetHostName returns the name of the 
            // host running the application.
            IPHostEntry ipHostInfo = Dns.Resolve(Dns.GetHostName());
            IPAddress ipAddress = ipHostInfo.AddressList[0];
            IPEndPoint localEndPoint = new IPEndPoint(ipAddress, socketPort);

            // Create a TCP/IP socket.
            Socket listener = new Socket(AddressFamily.InterNetwork,
                SocketType.Stream, ProtocolType.Tcp);            
            listener.Bind(localEndPoint);
            listener.Listen(10);
            while (true)
            {
                Socket handler = listener.Accept();
                try
                {
                    bytes = new byte[1024]; 
                        
                    do
                    {
                        int bytesRec = handler.Receive(bytes);
                        if (bytesRec > 0)
                        {
                            byte[] btResponse = processData(bytes);
                            handler.Send(btResponse);
                        }
                    } while (true);
                }
                catch
                {
                    handler.Shutdown(SocketShutdown.Both);
                    handler.Close();
                }
                objLog.writeDebugLog("Aguardando dados GPRS ...");
                Thread.Sleep(2000);
            } 
        }

        private byte[] processData(byte[] bytes)
        {
            Byte[] btResponse = null;
            //Get Package data
            GenericMessagePacket pkt = new GenericMessagePacket();
            pkt.startBit = BitConverter.ToUInt16(bytes, 0);
            pkt.packetLength = bytes[2];
            pkt.protocolNumber = bytes[3];
            //Inicializando o pacote genérico, que pode ser login, localizacao, alarme etc
            //o tamanho é calculado com base no tamanho total do pacote tirando 5 bytes
            //duvidas ver na documentação do protocolo
            pkt.data = new byte[pkt.packetLength - 5];
            for (int i = 0; i < (pkt.data.Length - 1); i++)
            {
                pkt.data[i] = bytes[i+4];
            }
            pkt.infoSerialNumber = BitConverter.ToUInt16(bytes, 12);
            pkt.errorCheck = BitConverter.ToUInt16(bytes, 14);
            pkt.stopBit = BitConverter.ToUInt16(bytes, 16);

            //Cria parametros para calculo do crc, em caso de dúvidas é possível verificar o calculo no site
            //http://crccalc.com/
            Parameters crcObjParameter1 = new Parameters("CRC-16/X-25", 16, 4129, 65535, true, true, 65535, 36061);
            //Cria o objeto para calculo
            Crc crcObj1 = new Crc(crcObjParameter1);
            //Calcula o src, no caso esto passando um offset de 2 porque as duas primeiras posições não entram
            //no cálculo do crc segundo o manual do protocolo
            Byte[] crcResult1 = crcObj1.ComputeHash(bytes, 2, 12);
            
            switch (pkt.protocolNumber)
            {
                case Constants.GT06_LOGIN_MSG:
                    LoginMessagePacket objLogin = new LoginMessagePacket();
                    objLogin.terminalID = BitConverter.ToUInt64(pkt.data, 0);

                    LoginMessagePacketResponse response = new LoginMessagePacketResponse();
                    response.startBit = pkt.startBit;
                    response.packetLength = 5;
                    response.protocolNumber = pkt.protocolNumber;
                    response.infoSerialNumber = pkt.infoSerialNumber;
                    response.stopBit = pkt.stopBit;



                    btResponse = new Byte[10];
                    //Preenche o vetor de resposta (btResponse)               
                    Buffer.BlockCopy(BitConverter.GetBytes(response.startBit), 0, btResponse, 0, 2);
                    Buffer.BlockCopy(BitConverter.GetBytes(response.packetLength), 0, btResponse, 2, 1);
                    Buffer.BlockCopy(BitConverter.GetBytes(response.protocolNumber), 0, btResponse, 3, 1);
                    Buffer.BlockCopy(BitConverter.GetBytes(response.infoSerialNumber), 0, btResponse, 4, 2);

                    //Cria parametros para calculo do crc, em caso de dúvidas é possível verificar o calculo no site
                    //http://crccalc.com/
                    Parameters crcObjParameter = new Parameters("CRC-16/X-25", 16, 4129, 65535, true, true, 65535, 36061);
                    //Cria o objeto para calculo
                    Crc crcObj = new Crc(crcObjParameter);
                    //Calcula o src, no caso esto passando um offset de 2 porque as duas primeiras posições não entram
                    //no cálculo do crc segundo o manual do protocolo
                    Byte[] crcResult = crcObj.ComputeHash(btResponse, 2, 4);
                    //O retorno eh um vetor de bytes de 8 posições, sendo que apenas as duas ultimas
                    //posições possuem o valor do crc
                    btResponse[6] = crcResult[crcResult.Length - 2];
                    btResponse[7] = crcResult[crcResult.Length - 1];

                    Buffer.BlockCopy(BitConverter.GetBytes(response.stopBit), 0, btResponse, 8, 2);
                    break;
               case Constants.GT06_LOCATION_DATA:
                    LocationDataPacket locationData = new LocationDataPacket();
                    locationData.year         = pkt.data[0];
                    locationData.month        = pkt.data[1];
                    locationData.day          = pkt.data[2];
                    locationData.hour         = pkt.data[3];
                    locationData.minute       = pkt.data[4];
                    locationData.second       = pkt.data[5];
                    locationData.satCount     = pkt.data[6];
                    locationData.south        = (pkt.data[7] << 24) + (pkt.data[8] << 16) + (pkt.data[9] << 8) + pkt.data[10];
                    //O parametro com valor S está sendo usado de forma fixa mas deverá ser ajustado pois considera
                    //que a coordenada está no hemisfério sul(o mesmo acontece para a longitude que usa como 
                    //padrão W de oeste). A direção pode ser coletada no parametro course status
                    locationData.convertedSouth = Conversions.gt06ToGeographicCoords(locationData.south, "S");
                    locationData.west         = (pkt.data[11] << 24) + (pkt.data[12] << 16) + (pkt.data[13] << 8) + pkt.data[14];
                    locationData.convertedWest = Conversions.gt06ToGeographicCoords(locationData.west, "W");
                    locationData.speed        = pkt.data[15];
                    locationData.courseStatus = BitConverter.ToUInt16(pkt.data, 16);
                    locationData.mcc          = BitConverter.ToUInt16(pkt.data, 18);
                    locationData.mnc          = pkt.data[20];
                    locationData.lac          = BitConverter.ToUInt16(pkt.data, 21);
                        //BitConverter.ToUInt16(pkt.data, 6);
                    string teste = "";
                    break;

            }
            return btResponse;           
        }
    }
}
