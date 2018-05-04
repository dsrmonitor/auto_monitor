using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository
{
    public interface dsrMonitorDAO<T>
    {
        void insert(T entidade);
        void update(T entidade);
        void delete(T entidade);
        T loadFromId(int id);
        IList<T> load();

    }
}
