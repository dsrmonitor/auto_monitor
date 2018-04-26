using srv_receive_data.source.util;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace srv_receive_data.source
{
    class readDataThread
    {
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
                Log.Debug("Teste samuel"+count);
                System.Threading.Thread.Sleep(5000);
                count++;
            }

        }
    }
}
