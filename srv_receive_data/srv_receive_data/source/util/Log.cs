﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;
using System.IO;

namespace srv_receive_data.source.util
{
class Log
    {
    public String logFileName { get;}

    //private StreamWriter sw;
    public Log()
    {
        //Busca o diretório atual
        String logPath = Environment.CurrentDirectory+"\\Logs\\";

        if (!Directory.Exists(logPath))
        {
            Directory.CreateDirectory(logPath);
        }
        //Monta o nome do log com a data atual
        String logName = "src_service_" + DateTime.Now.Day + "-" + DateTime.Now.Month + "-" + DateTime.Now.Year + "_" +
                        DateTime.Now.Hour + "_" + DateTime.Now.Minute + "_" + DateTime.Now.Second + ".log";

        logFileName = logPath + logName;
    }
    public bool Debug(string Valor)
    {

        try
        {
            if (!File.Exists(logFileName))
            {
                // Create a file to write to.
                using (StreamWriter sw = File.CreateText(logFileName))
                {
                    sw.WriteLine(Valor);
                }
            }
            else
            {
                using (StreamWriter sw = File.AppendText(logFileName))
                {
                    sw.WriteLine(Valor);
                }
            }
            return true;
        }
        catch
        {
            return false;
        }
    }
  }
}
