using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Entities
{
    public class sms_queue_send
    {
        public virtual int id { get; set; }
        public virtual String message { get; set; }
        public virtual Boolean status { get; set; }
        public virtual String recipient { get; set; }
    }
}
