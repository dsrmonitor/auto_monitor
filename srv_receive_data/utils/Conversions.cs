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
        public static bool OnlyHexInString(string test)
        {
            // For C-style hex notation (0xFF) you can use @"\A\b(0[xX])?[0-9a-fA-F]+\b\Z"
            return System.Text.RegularExpressions.Regex.IsMatch(test, @"\A\b[0-9a-fA-F]+\b\Z");
        }

    }
}
