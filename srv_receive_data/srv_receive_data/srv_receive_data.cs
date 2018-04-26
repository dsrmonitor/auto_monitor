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
        public srv_receive_data()
        {
            InitializeComponent();            

            //Loga a inicialização do serviço
            Log.Debug("Initializing the Service ...");

            readDataThread thread01 = new readDataThread();

            thread01.Call();
            //int count = 0;
            //while (true)
            //{
            //    objLog.WriteEntry("Test Samuel" + count);
            //    System.Threading.Thread.Sleep(5000);
            //}


        }

        protected override void OnStart(string[] args)
        {
        }

        protected override void OnStop()
        {
        }
    }
}
