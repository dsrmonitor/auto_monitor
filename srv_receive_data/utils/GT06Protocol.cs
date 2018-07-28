using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace dsrUtil.GT06Protocol
{
    
    public class LoginMessagePacket
    {
        public UInt16 startBit { get; set; }
        public Byte packetLength { get; set; }
        public Byte protocolNumber { get; set; }
        public UInt64 terminalID { get; set; }
        public UInt16 infoSerialNumber { get; set; }
        public UInt16 errorCheck { get; set; }
        public UInt16 stopBit { get; set; }
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
}
