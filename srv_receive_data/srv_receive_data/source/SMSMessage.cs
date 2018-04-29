using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace srv_receive_data.source
{
    class SMSMessage
    {
        public int index { get; set; }
        public String status { get; set; }
        public String sender { get; set; }
        public String alphabet { get; set; }
        public String sentDate { get; set; }
        public String message { get; set; }
    }
}
