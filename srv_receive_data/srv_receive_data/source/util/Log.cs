using System;
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
        
        public static bool Debug(string Valor)
                {
            //Directory.GetCurrentDirectory()+"
            string path = "c:\\Logs\\src_service_data_samuel_.log";//+ DateTime.Now.Year + "." + DateTime.Now.Month + "." + DateTime.Now.Day + ".log";

                    try
                    {

                        if (!System.IO.File.Exists(path))
                        {
                            using (System.IO.StreamWriter sw = System.IO.File.CreateText(path))
                            {
                                sw.WriteLine(Valor);
                            }
                            return true;
                        }
                        else
                        {
                            using (System.IO.StreamWriter sw = System.IO.File.AppendText(path))
                            {
                                sw.WriteLine(Valor);
                            }
                            return true;
                        }

                    }
                    catch (Exception e)
                    {
                        //Console.WriteLine("The process failed: {0}", e.ToString());
                        return false;
                    }
                }
            }
}
