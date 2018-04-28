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

namespace srv_receive_data
{
    public partial class srv_receive_data : ServiceBase
    {
        private Log objLog;
        public srv_receive_data()
        {
            InitializeComponent();            
            objLog = new Log();
            //Loga a inicialização do serviço
            objLog.Debug("Initializing the Service ...");
            objLog.Debug("Initializing the Service2 ...");

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
