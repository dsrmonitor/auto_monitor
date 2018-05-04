using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.ServiceProcess;
using System.Text;
using System.Threading.Tasks;
using srv_receive_data.source.util;
using srv_receive_data.source;
using srv_receive_data.source.constant;
using System.IO.Ports;
using Repository.Entities;
using Repository;

namespace srv_receive_data
{
    public partial class srv_receive_data : ServiceBase
    {
        private Log objLog;
        private SerialPort modemPort;
        public srv_receive_data()
        {
            InitializeComponent();
            //Carrega o arquivo ini
            IniFile init = new IniFile(Constants.INIT_PATH, Constants.INIT_NAME);

            sms_not_recognized sms = new sms_not_recognized();
            sms_not_recognizedRepository dao = new sms_not_recognizedRepository();
            sms.text = "Teste Samuel";
            dao.insert(sms);

            //Cria o objeto de log
            objLog = new Log(init.IniReadInt("levelLog"), Constants.INIT_PATH);
            //Loga a inicializacao do servico
            objLog.WriteLog("Initializing the Service ...", Constants.LOG_TRACE);

            //Conectando a porta serial
            modemPort = utilSerialPort.OpenPort(
                init.IniReadString("comPort", "COM1"),
                init.IniReadInt("baudRate", 9600),
                init.IniReadInt("dataBits", 8),
                init.IniReadInt("readTimeout", 300),
                init.IniReadInt("writeTimeout",300),
                objLog);

            readDataThread thread01 = new readDataThread(modemPort, objLog);

            thread01.Call();
        }

        protected override void OnStart(string[] args)
        {
        }

        protected override void OnStop()
        {
        }
    }
}
