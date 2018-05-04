using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FluentNHibernate.Mapping;
using Repository.Entities;

namespace Repository.Mapping
{
    
    public class sms_not_recognizedMap : ClassMap<sms_not_recognized>
    {
        public sms_not_recognizedMap()
        {
            Id(c => c.id);
            Map(c => c.text);
            Table("sms_not_recognized");
        }        
    }
}
