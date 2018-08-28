using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace dsrUtil.GT06Protocol
{
    public class GenericMessagePacket
    {
        public UInt16 startBit { get; set; }
        public Byte packetLength { get; set; }
        public Byte protocolNumber { get; set; }
        public Byte[] data { get; set; }
        public UInt16 infoSerialNumber { get; set; }
        public UInt16 errorCheck { get; set; }
        public UInt16 stopBit { get; set; }
    }
    public class LoginMessagePacket
    {        
        public string terminalID { get; set; }        
    }
    [Serializable()]
    public class LoginMessagePacketResponse
    {
        public UInt16 startBit { get; set; }
        public Byte packetLength { get; set; }
        public Byte protocolNumber { get; set; }
        public UInt16 infoSerialNumber { get; set; }
        public UInt16 errorCheck { get; set; }
        public UInt16 stopBit { get; set; }
    }
    public class LocationDataPacket
    {
        public byte year { get; set; }
        public byte month { get; set; }
        public byte day { get; set; }
        public byte hour { get; set; }
        public byte minute { get; set; }
        public byte second { get; set; }
        public byte satCount { get; set; }
        public Int32 south { get; set; }
        public double convertedSouth { get; set; }
        public Int32 west { get; set; }
        public double convertedWest { get; set; }
        public byte speed { get; set; }
        //este atributo deve ser decodificado em bits conforme a documentação do protocolo
        //mas por hora não vou implementar, ver no futuro quais informações serão uteis
        public UInt16 courseStatus { get; set; }
        public UInt16 mcc { get; set; }
        public byte mnc { get; set; }
        public UInt16 lac { get; set; }
        //Será possível adicionar aqui o cell id, que são tres bytes. Verificar como decodificar
        //isso em u ma variavel

    }
}
