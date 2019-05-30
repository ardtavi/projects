/*************************************************************************
 * ULLINK CONFIDENTIAL INFORMATION
 * _______________________________
 *
 * All Rights Reserved.
 *
 * NOTICE: This file and its content are the property of Ullink. The
 * information included has been classified as Confidential and may
 * not be copied, modified, distributed, or otherwise disseminated, in
 * whole or part, without the express written permission of Ullink.
 ************************************************************************/
package ro.utcn.sd.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.dao.TicketsDao;
import ro.utcn.sd.dao.impl.hibernate.util.HibernateUtil;
import ro.utcn.sd.entities.User;
import ro.utcn.sd.entities.Parkinglot;
import ro.utcn.sd.entities.Ticket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HibernateTicketDao implements TicketsDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    @Transactional(readOnly = true)
    public Ticket find(long id) {
        Session currentSession = sessionFactory.openSession();
        Ticket tickets = currentSession.get(Ticket.class, id);
        return tickets;
    }

    @Override
    public void delete(Ticket objectToDelete) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public void update(Ticket objectToUpdate) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
        currentSession.close();
    }

    @Override
    public void insert(Ticket objectToCreate) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();
        currentSession.close();
    }

    // I know this is horrible to look at at first. I just wanted to show you some Criteria API
    // Use HSQL instead
    @Override
    public Set<Ticket> findByUserId(long userId) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();

        CriteriaQuery<Ticket> criteriaQuery = builder.createQuery(Ticket.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.where(builder.equal(root.get("id"), userId));
        CriteriaQuery<Ticket> ticketCriteriaQuery = criteriaQuery.select(root.join("tickets"));
        List<Ticket> resultList = currentSession.createQuery(ticketCriteriaQuery).getResultList();

        transaction.commit();
        currentSession.close();

        return new HashSet<>(resultList);
    }
    
    public Set<Ticket> findByParkinglotId(long parkinglotId) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();

        CriteriaQuery<Ticket> criteriaQuery = builder.createQuery(Ticket.class);
        Root<Parkinglot> root = criteriaQuery.from(Parkinglot.class);

        criteriaQuery.where(builder.equal(root.get("id"), parkinglotId));
        CriteriaQuery<Ticket> ticketCriteriaQuery = criteriaQuery.select(root.join("tickets"));
        List<Ticket> resultList = currentSession.createQuery(ticketCriteriaQuery).getResultList();

        transaction.commit();
        currentSession.close();

        return new HashSet<>(resultList);
    }
}
