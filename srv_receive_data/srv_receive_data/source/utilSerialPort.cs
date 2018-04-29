using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO.Ports;
using srv_receive_data.source.constant;

namespace srv_receive_data.source.util
{
    class utilSerialPort
    {
       public static SerialPort OpenPort(string p_strPortName,
            int p_uBaudRate, int p_uDataBits,
            int p_uReadTimeout, int p_uWriteTimeout, Log objLog)
        {

            //receiveNow = new AutoResetEvent(false);
            SerialPort port = new SerialPort();

            try
            {
                objLog.WriteLog("Abrindo porta serial("+p_strPortName+")", Constants.LOG_TRACE);
                port.PortName = p_strPortName;                 //COM1
                port.BaudRate = p_uBaudRate;                   //9600
                port.DataBits = p_uDataBits;                   //8
                port.StopBits = StopBits.One;                  //1
                port.Parity = Parity.None;                     //None
                port.ReadTimeout = p_uReadTimeout;             //300
                port.WriteTimeout = p_uWriteTimeout;           //300
                port.Encoding = Encoding.GetEncoding("iso-8859-1");
                //port.DataReceived += new SerialDataReceivedEventHandler
                //        (port_DataReceived);
                port.Open();
                port.DtrEnable = true;
                port.RtsEnable = true;
            }
            catch (Exception ex)
            {
                objLog.WriteLog("Erro ao abrir porta serial:" + ex, Constants.LOG_EXCEPTION);
                throw ex;
            }
            return port;
        }
    }
}
