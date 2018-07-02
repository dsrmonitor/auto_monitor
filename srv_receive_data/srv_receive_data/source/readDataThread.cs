using srv_receive_data.source.util;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using srv_receive_data.source.constant;
using System.IO.Ports;
using dsrUtil;

namespace srv_receive_data.source
{
    public class readDataThread
    {
        
        private Log objLog;
        private SMSMonitor objSmsMonitor;
        private object serialBlock;

        //Inserido para testes, apagar depois
        SerialPort objSerialPort;

        public readDataThread(SerialPort port,  Log log, object block)
        {
            objLog = log;
            objSerialPort = port;
            serialBlock = block;
            //Cria o objeto que irá manipular sms
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
            int count = 0;
            while (true)
            {
                lock(serialBlock)
                {
                    objSmsMonitor.readMessages();
                    string t;
                    try
                    {

                        objSerialPort.Open();
                        t = objSerialPort.ReadExisting();
                    }
                    finally
                    {
                        objSerialPort.Close();
                    }
                    objLog.writeDebugLog("Teste samuel " + count + " serialPortData:" + t);
                }
                System.Threading.Thread.Sleep(5000);
                count++;
            }

        }
    }
}
