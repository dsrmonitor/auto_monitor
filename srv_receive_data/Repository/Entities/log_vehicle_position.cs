using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Entities
{
    public class log_vehicle_position
    {
        public virtual int id { get; set; }
        public virtual int vehicle_id { get; set; }
        public virtual string west { get; set; }
        public virtual string south { get; set; }
        public virtual float speed { get; set; }
        public virtual DateTime timestamp { get; set; }

    }
}
