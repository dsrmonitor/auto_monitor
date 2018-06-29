using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace utils
{
    public class Conversions
    {
        public static string HextoString(string HexString)
        {
            string stringValue = "";
            for (int i = 0; i < HexString.Length / 4; i++)
            {

                string hexChar = HexString.Substring(i * 4, 4);
                int hexValue = Convert.ToInt32(hexChar, 16);
                if (hexValue <= 127) { 
                    stringValue += Char.ConvertFromUtf32(hexValue);
                }
            }
            return stringValue;
        }

    }
}
