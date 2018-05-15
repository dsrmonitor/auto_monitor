using dsrUtil;
using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using NHibernate;
using NHibernate.Tool.hbm2ddl;
using Repository.Mapping;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using dsrUtil.constant;

namespace Repository
{
    public class sessionFacrtoy
    {
        private static String conString;
        private static ISessionFactory session;
        private static Log objLog;

        public static void setObjLog(Log obj)
        {
            objLog = obj;
        }

        public static void setConString(String aConStr)
        {
            conString = aConStr;
        }
        public static ISessionFactory createSession()
        {
            if (session != null)
            {
                return session;
            }
            try
            {
                IPersistenceConfigurer configDB = PostgreSQLConfiguration.PostgreSQL82.ConnectionString(conString);      

                var configMap = Fluently.Configure().Database(configDB).Mappings(c => c.FluentMappings.AddFromAssemblyOf<Mapping.sms_not_recognizedMap>());

                


                session = configMap.BuildSessionFactory();
            }
            catch(Exception ex)
            {
                objLog.writeExceptionLog("Erro ao conectar no banco de dados:"+ex.ToString());
            }
            

            return session;
        } 
        public static ISession openSession()
        {
            return createSession().OpenSession();
        }
    }
}
