using dsrUtil.GT06Protocol;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

namespace dsrUtil.Serialization
{
    public class Serialization
    {
        public static byte[] ConverteObjectEmByte(LoginMessagePacketResponse obj)
        {
            if (obj == null)
            {
                return null;
            }

            BinaryFormatter bf = new BinaryFormatter();

            MemoryStream ms = new MemoryStream();

            //cria um array de bytes através do objeto obj
            bf.Serialize(ms, obj);

            //recebe o array referente ao objeto obj
            byte[] ret = ms.ToArray();

            return ret;
        }
    }
}
