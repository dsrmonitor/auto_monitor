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

namespace srv_receive_data
{
    public partial class srv_receive_data : ServiceBase
    {
        private Log objLog;
        public srv_receive_data()
        {
            InitializeComponent();

            IniFile init = new IniFile(Constants.INIT_PATH, Constants.INIT_NAME);
            objLog = new Log(init.IniReadInt("levelLog"), Constants.INIT_PATH);
            //Loga a inicializacao do servico
            objLog.WriteLog("Initializing the Service ...", Constants.LOG_TRACE);

            readDataThread thread01 = new readDataThread(objLog);

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
