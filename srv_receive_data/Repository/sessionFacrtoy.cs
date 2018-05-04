using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using NHibernate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository
{
    public class sessionFacrtoy
    {
        private static String conString = "Server=localhost;Port=5432;User Id=postgres;Password=admdb;Database=dsr_monitor;";
        private static ISessionFactory session;

        public static ISessionFactory createSession()
        {
            if (session != null)
            {
                return session;
            }
            try
            {
                IPersistenceConfigurer configDB = PostgreSQLConfiguration.PostgreSQL82.ConnectionString(conString);
                //var configMap = Fluently.Configure()
                //    .Database(configDB)
                //    .Mappings(c => 
                //        c.FluentMappings
                //        .AddFromAssemblyOf<Mapping.sms_not_recognizedMap>());
                var configMap = Fluently.Configure().Database(configDB).Mappings(c => c.FluentMappings.AddFromAssemblyOf<Mapping.sms_not_recognizedMap>());
                session = configMap.BuildSessionFactory();
            }
            catch(Exception ex)
            {
                String tst = ex.ToString();
            }
            

            return session;
        } 
        public static ISession openSession()
        {
            return createSession().OpenSession();
        }
    }
}
