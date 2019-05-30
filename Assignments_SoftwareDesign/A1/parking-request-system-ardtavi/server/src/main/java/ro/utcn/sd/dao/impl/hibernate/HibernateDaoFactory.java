package ro.utcn.sd.dao.impl.hibernate;

import ro.utcn.sd.dao.UserDao;


import ro.utcn.sd.dao.DaoFactory;
import ro.utcn.sd.dao.ParkinglotDao;
import ro.utcn.sd.dao.TicketsDao;

public class HibernateDaoFactory extends DaoFactory {
    @Override
    public UserDao getUserDao() {
        return new HibernateUserDao();
    }

    @Override
    public TicketsDao getTicketsDao() {
        return new HibernateTicketDao();
    }

	@Override
	public ParkinglotDao getParkinglotsDao() {
		
		 return new HibernateParkinglotDao();
	}


    
}
