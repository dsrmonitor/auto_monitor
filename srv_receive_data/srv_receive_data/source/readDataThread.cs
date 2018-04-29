using srv_receive_data.source.util;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using srv_receive_data.source.constant;

namespace srv_receive_data.source
{
    class readDataThread
    {
        private Log objLog;

        public readDataThread(Log log)
        {
            objLog = log;
        }

        public void Call()
        {
            System.Threading.ThreadStart ts =
            new System.Threading.ThreadStart(Execute);
            System.Threading.Thread t =
                new System.Threading.Thread(ts);
            t.IsBackground = true;
            t.Start();
        }
        private void Execute()
        {
            int count = 0;
            while (true)
            {
                objLog.WriteLog("Teste samuel"+count,Constants.LOG_DEBUG);
                System.Threading.Thread.Sleep(5000);
                count++;
            }

        }
    }
}
