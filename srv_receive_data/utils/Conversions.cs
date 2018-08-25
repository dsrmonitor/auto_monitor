using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
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
        public static string phoneNumberWithoutCountryCode(string input)
        {
            Regex r = new Regex(@"(?:\+)?(?:55)?(\d{10}\d?)");
            Match m = r.Match(input);
            if (m.Success)
            {
                return m.Groups[1].Value;
            }
            return "";
        }
        //Converte o formato que a coordenada chega do GT06 para coordenada geográfica
        public static double gt06ToGeographicCoords(Int32 value, string direction)
        {
            double tmpValue = Convert.ToDouble(value) / 30000;
            int degreee = Convert.ToInt32(Math.Floor(tmpValue / 60));
            double minutes = (tmpValue % 60);
            return ConvertDMSToDD(degreee, minutes, 0, direction);
        }
        public static double ConvertDMSToDD(int degrees, double minutes, double seconds, string direction)
        {
            double dd = degrees + minutes / 60 + seconds / (60 * 60);

            if (direction == "S" || direction == "W")
            {
                dd = dd * -1;
            } // Don't do anything for N or E
            return dd;
        }
    }
}
