using srv_receive_data.source.constant;
using srv_receive_data.source.util;
using System;
using System.Collections.Generic;
using System.IO.Ports;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;
using Repository;
using Repository.Entities;

namespace srv_receive_data.source
{
    class SMSMonitor
    {
        private SerialPort objSerialPort;
        private Log objLog;
        AutoResetEvent receiveNow = null;

        public SMSMonitor(SerialPort serial, Log log)
        {
            objSerialPort = serial;
            objLog = log;
        }
        private string ExecCommand(SerialPort port, string command, int responseTimeout)
        {
            try
            {

                port.DiscardOutBuffer();
                port.DiscardInBuffer();
                port.Write(command + "\r");

                string input = ReadResponse(port, responseTimeout);
                if ((input.Length == 0) || ((!input.EndsWith("\r\n> ")) && (!input.EndsWith("\r\nOK\r\n"))))
                    input = "";
                return input;
                
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public string ReadResponse(SerialPort port, int timeout)
        {
            DateTime timeTimeout = DateTime.Now.AddMilliseconds(timeout);
            string buffer = string.Empty;
            try
            {
                do
                {
                    string t = port.ReadExisting();
                    buffer += t;
                }
                while (!buffer.EndsWith("\r\nOK\r\n") && !buffer.EndsWith("\r\n> ")
                       && !buffer.Contains("ERROR"));// &&  (DateTime.Now<timeTimeout));
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return buffer;
        }
        public String readMessages()
        {
            try
            {
                receiveNow = new AutoResetEvent(false);
                
                // Check connection
                string input = ExecCommand(objSerialPort, "AT", 1000);

                if (input != "")
                {
                    // Use message format "Text mode"
                    ExecCommand(objSerialPort, "AT+CMGF=1", 1000);
                    // Use character set "PCCP437"
                    ExecCommand(objSerialPort, "AT+CSCS=\"PCCP437\"", 1000);
                    // Select SIM storage
                    ExecCommand(objSerialPort, "AT+CPMS=\"SM\"", 1000);
                    //Read the messages
                    input = ExecCommand(objSerialPort, "AT+CMGL=\"ALL\"", 50000000);

                    if (input != "")
                    {
                        List<SMSMessage> messages = parseSMSMessage(input);

                        foreach(var msg in messages)
                        {
                            objLog.WriteLog("=======Arrive Message =========",Constants.LOG_TRACE);
                            objLog.WriteLog("Contato: " + msg.sender,Constants.LOG_TRACE);
                            objLog.WriteLog("Mensagem: " + msg.message, Constants.LOG_TRACE);
                            objLog.WriteLog("===============================", Constants.LOG_TRACE);
                            

                            deleteMsg(msg.index);
                            


                        }
                    }
                    else
                    {
                        objLog.WriteLog("não leu a mensagem",Constants.LOG_TRACE);
                    }

                    //objLog.WriteLog("Arrive Message:" + input, Constants.LOG_TRACE);

                    
                }
                else
                {
                    objLog.WriteLog("Moden indisponível para leitura de mensagens",Constants.LOG_DEBUG);
                }
            }
            catch (Exception ex)
            {
                objLog.WriteLog("Erro ao ler SMS:" + ex, Constants.LOG_EXCEPTION);
                throw ex;
            }
            return "";
        }
        private List<SMSMessage> parseSMSMessage(string input)
        {
            List<SMSMessage> result = new List<SMSMessage>();
            try
            {

                Regex r = new Regex(@"\+CMGL: (\d*),""(.+)"",""(.+)"",(.*),""(.+)""\r\n(.+)\r\n");
                Match m = r.Match(input);                
                while (m.Success)
                {
                    SMSMessage msg = new SMSMessage();
                    msg.index = Convert.ToInt32(m.Groups[1].Value);
                    msg.status = m.Groups[2].Value;
                    msg.sender = m.Groups[3].Value;
                    msg.alphabet = m.Groups[4].Value;
                    msg.sentDate = m.Groups[5].Value;
                    msg.message = m.Groups[6].Value;
                
                    result.Add(msg);
                    m = m.NextMatch();
                }

               
            }
            catch (Exception ex)
            {
                objLog.WriteLog("Erro ao processar SMS:" + ex, Constants.LOG_EXCEPTION);
                throw ex;
            }
            return (result);
        }
        private bool deleteMsg(int index)
        {
            // Check connection
            string input = ExecCommand(objSerialPort, "AT", 1000);

            if (input != "")
            {
                input = ExecCommand(objSerialPort, "AT+CMGD=" + index, 1000);
            }
            if (input.Contains("OK"))
            {
                return (true);
            }
            else
            {
                return (false);
            }
        }
    }
}
