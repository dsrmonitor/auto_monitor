using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace dsrUtil.constant
{
    public static class Constants
    {
        //Constantes para níveis de log
        public const int LOG_EXCEPTION = 1;
        public const int LOG_TRACE = 2;
        public const int LOG_DEBUG = 3;

        //Diretório para ini
        public const string INIT_PATH = "C:\\nsrMonitor";
        public const string INIT_NAME = "nsrMonitor.ini";

        //Constantes relacionadas ao protocolo de comunicação do GT06
        public const byte GT06_LOGIN_MSG     =  1;
        public const byte GT06_LOCATION_DATA = 18;
    }

}
