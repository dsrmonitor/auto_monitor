using dsrUtil;
using dsrUtil.constant;
using Repository;
using Repository.Entities;
using System;
using System.Collections.Generic;
using System.IO.Ports;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace srv_receive_data.source
{
    class sendDataThread
    {
        private Log objLog; 
        private SMSMonitor objSmsMonitor;

        public sendDataThread(SerialPort port, Log log)
        {
            objLog = log;
            objSmsMonitor = new SMSMonitor(port, objLog);

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
          
            while (true)
            {
                sms_queue_sendRepository dao = new sms_queue_sendRepository();
                IList<sms_queue_send> lista = dao.loadForStatus(true);

                foreach (var msg in lista)
                {
                    objSmsMonitor.sendMsg(msg);
                    objLog.writeTraceLog("Mensagem a ser enviada:" + msg.message);
                    dao.delete(msg);
                }                
                System.Threading.Thread.Sleep(5000);                
            }

        }
    }
}
