using FluentNHibernate.Mapping;
using Repository.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Mapping
{
    public class log_vehicle_positionMap: ClassMap<log_vehicle_position>
    {
        public log_vehicle_positionMap()
        {
            Id(c => c.id);
            Map(c => c.vehicle_id);
            Map(c => c.west);
            Map(c => c.south);
            Map(c => c.speed);
            Map(c => c.timestamp);
            Map(c => c.origin);
            Table("log_vehicle_position");
        }
    }
}
