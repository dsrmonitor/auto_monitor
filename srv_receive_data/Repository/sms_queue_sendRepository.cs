using NHibernate;
using NHibernate.Linq;
using Repository.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository
{
    public class sms_queue_sendRepository : reposiroryDSRDAO<sms_queue_send>
    {
        public IList<sms_queue_send> loadForStatus(Boolean value)
        {
            using (ISession session = sessionFacrtoy.openSession())
            {
                return session.Query<sms_queue_send>()
                              .Where(p => p.status == value)
                              .ToList();
            }
        }
    }
}
