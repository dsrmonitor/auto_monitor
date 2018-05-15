using FluentNHibernate.Mapping;
using Repository.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Mapping
{
    class sms_queue_sendMap : ClassMap<sms_queue_send>
    {
        public sms_queue_sendMap()
        {
            Id(c => c.id);
            Map(c => c.message);
            Map(c => c.recipient);
            Map(c => c.status);
            Table("sms_queue_send");
        }
    }
}
