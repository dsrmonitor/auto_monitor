using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using dsrUtil.constant;
using dsrUtil;
using dsrUtil.GT06Protocol;
using dsrUtil.Serialization;

namespace TCPSocketTest
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            IPAddress localAddr = IPAddress.Parse("127.0.0.1");
            TcpListener servidor = new TcpListener(8686);
            servidor.Start();

            while (true)
            {
                Socket conexao = servidor.AcceptSocket();

                //NetworkStream socketStream = new NetworkStream(conexao);
                byte[] bytes = new byte[256];
                conexao.Receive(bytes);

                //Get Package data
                LoginMessagePacket pkt = new LoginMessagePacket();
                pkt.startBit = BitConverter.ToUInt16(bytes, 0);
                pkt.packetLength = bytes[2];
                pkt.protocolNumber = bytes[3];
                pkt.terminalID = BitConverter.ToUInt64(bytes, 4);
                pkt.infoSerialNumber = BitConverter.ToUInt16(bytes, 12);
                pkt.errorCheck = BitConverter.ToUInt16(bytes, 14);
                pkt.stopBit = BitConverter.ToUInt16(bytes, 16);

                Byte[] tst = new Byte[pkt.packetLength - 1];
                Array.Copy(bytes, 2, tst, 0, tst.Length);
                
                Byte[] crcResposta = new byte[2];

                CalculaCRC(tst, ref crcResposta);

                if (pkt.protocolNumber == Constants.GT06_LOGIN_MSG)
                {
                    LoginMessagePacketResponse response = new LoginMessagePacketResponse();
                    response.startBit = pkt.startBit;
                    response.packetLength = 5;
                    response.protocolNumber = pkt.protocolNumber;
                    response.infoSerialNumber = pkt.infoSerialNumber;
                    response.errorCheck = pkt.errorCheck;
                    response.stopBit = pkt.stopBit;


                    




                    Byte[] btResponse = new Byte[10];                    
                    Buffer.BlockCopy(BitConverter.GetBytes(response.startBit), 0, btResponse, 0, 2);
                    Buffer.BlockCopy(BitConverter.GetBytes(response.packetLength), 0, btResponse, 2, 1);
                    Buffer.BlockCopy(BitConverter.GetBytes(response.protocolNumber), 0, btResponse, 3, 1);
                    Buffer.BlockCopy(BitConverter.GetBytes(response.infoSerialNumber), 0, btResponse, 4, 2);
                    Buffer.BlockCopy(BitConverter.GetBytes(response.errorCheck), 0, btResponse, 6, 2);
                    Buffer.BlockCopy(BitConverter.GetBytes(response.stopBit), 0, btResponse, 8, 2);

                    
                    conexao.Send(btResponse);

                }

                conexao.Close();

                System.Threading.Thread.Sleep(5000);
            }

        }

        private  void CalculaCRC(byte[] Mensagem, ref byte[] Valor_CRC)
        {
            //Function expects a modbus message of any length as well as a 2 byte CRC array in which to
            //return the CRC values:

            ushort CRC_Inicial = 0xFFFF;
            byte CRC_ALTO = 0xFF, CRC_BAIXO = 0xFF;
            char CRC_TEMP;

            for (int Dados = 0; Dados < (Mensagem.Length) - 2; Dados++)
            {
                CRC_Inicial = (ushort)(CRC_Inicial ^ Mensagem[Dados]);

                for (int BITS = 0; BITS < 8; BITS++)
                {
                    CRC_TEMP = (char)(CRC_Inicial & 0x0001);
                    CRC_Inicial = (ushort)((CRC_Inicial >> 1) & 0x7FFF);

                    if (CRC_TEMP == 1)
                        CRC_Inicial = (ushort)(CRC_Inicial ^ 0xA001);
                }
            }
            Valor_CRC[1] = CRC_ALTO = (byte)((CRC_Inicial >> 8) & 0xFF);
            Valor_CRC[0] = CRC_BAIXO = (byte)(CRC_Inicial & 0xFF);
        }
    }
}
