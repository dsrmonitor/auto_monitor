using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Entities
{
    public class sms_not_recognized
    {
        public virtual int id { get; set; }
        public virtual String message { get; set; }
        public virtual int index { get; set; }
        public virtual String status { get; set; }
        public virtual String sender { get; set; }
        public virtual String alphabet { get; set; }
        public virtual DateTime inserted_at { get; set; }
    }
}
