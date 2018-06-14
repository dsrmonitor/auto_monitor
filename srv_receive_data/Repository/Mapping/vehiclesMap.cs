using FluentNHibernate.Mapping;
using Repository.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Mapping
{
    class vehiclesMap: ClassMap<vehicles>
    {
        public vehiclesMap()
        {
            Id(c => c.id);
            Map(c => c.license);
            Map(c => c.name);
            Map(c => c.phone_number);
            Map(c => c.chassi_number);
            Map(c => c.description);
            //Map(c => c.updated_at);
            //Map(c => c.deleted_at);
            //Map(c => c.last_update);
            Table("vehicles");
        }
    }
}
