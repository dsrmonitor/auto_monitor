using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Entities
{
    public class vehicles
    {
        public virtual int id { get; set; }
        public virtual string license { get; set; }
        public virtual string name { get; set; }
        public virtual string phone_number { get; set; }
        public virtual string chassi_number { get; set; }
        public virtual string description { get; set; }
        public virtual DateTime updated_at { get; set; }
        public virtual DateTime deleted_at { get; set; }
        public virtual DateTime last_update { get; set; }
    }
}
