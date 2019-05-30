package ro.utcn.sd.dao.impl.jdbc;

import ro.utcn.sd.dao.UserDao;
import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.dao.ParkinglotDao;
import ro.utcn.sd.dao.TicketsDao;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public UserDao getUserDao() {
        return new JdbcUserDao();
    }

    @Override
    public TicketsDao getTicketsDao() {
        return new JdbcTicketsDao();
    }

	@Override
	public ParkinglotDao getParkinglotsDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
