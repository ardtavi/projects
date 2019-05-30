package ro.utcn.sd.dao.impl.jdbc;

import ro.utcn.sd.dao.TicketsDao;
import ro.utcn.sd.entities.Ticket;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * Your job is to implement JDBC. You should know that by now
 */
public class JdbcTicketsDao implements TicketsDao {
    @Override
    public Ticket find(long id) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Ticket objectToDelete) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Ticket objectToUpdate) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(Ticket objectToCreate) {
        throw new NotImplementedException();
    }

    @Override
    public Set<Ticket> findByUserId(long cartId) {
        throw new NotImplementedException();
    }

	@Override
	public Set<Ticket> findByParkinglotId(long parkinglotId) {
		// TODO Auto-generated method stub
		return null;
	}
}
