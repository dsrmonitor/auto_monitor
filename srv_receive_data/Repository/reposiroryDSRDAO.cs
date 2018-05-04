using NHibernate;
using NHibernate.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository
{
    public class reposiroryDSRDAO<T> : dsrMonitorDAO<T> where T : class
    {
        public void insert(T entidade)
        {
            using (ISession session = sessionFacrtoy.openSession())
            {
                using (ITransaction transaction = session.BeginTransaction())
                {
                    try
                    {
                        session.Save(entidade);
                        transaction.Commit();
                    }
                    catch (Exception ex)
                    {
                        //adicionar tratamento de excessão
                        if (!transaction.WasCommitted)
                        {
                            transaction.Rollback();
                        }
                    }

                }
            }
        }
        public void delete(T entidade)
        {
            using (ISession session = sessionFacrtoy.openSession())
            {
                using (ITransaction transaction = session.BeginTransaction())
                {
                    try
                    {
                        session.Delete(entidade);
                        transaction.Commit();
                    }
                    catch (Exception ex)
                    {
                        //adicionar tratamento de excessão
                        if (!transaction.WasCommitted)
                        {
                            transaction.Rollback();
                        }
                    }

                }
            }
        }

        public IList<T> load()
        {
            using (ISession session = sessionFacrtoy.openSession())
            {
                return (from e in session.Query<T>() select e).ToList();
            }
        }

        public T loadFromId(int id)
        {
            using (ISession session = sessionFacrtoy.openSession())
            {
                return session.Get<T>(id);
            }
        }

        public void update(T entidade)
        {
            using (ISession session = sessionFacrtoy.openSession())
            {
                using (ITransaction transaction = session.BeginTransaction())
                {
                    try
                    {
                        session.Update(entidade);
                        transaction.Commit();
                    }
                    catch (Exception ex)
                    {
                        //adicionar tratamento de excessão
                        if (!transaction.WasCommitted)
                        {
                            transaction.Rollback();
                        }
                    }

                }
            }
        }
    }
}
