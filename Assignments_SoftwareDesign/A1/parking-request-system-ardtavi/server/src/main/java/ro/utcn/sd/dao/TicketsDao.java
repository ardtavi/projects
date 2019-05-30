package ro.utcn.sd.dao;

import ro.utcn.sd.entities.Ticket;

import java.util.Set;

public interface TicketsDao extends Dao<Ticket> {

    @Override
    Ticket find(long id);

    @Override
    void delete(Ticket objectToDelete);

    @Override
    void update(Ticket objectToUpdate);

    @Override
    void insert(Ticket objectToCreate);

    Set<Ticket> findByUserId(long userId);

	Set<Ticket> findByParkinglotId(long parkinglotId);
}
