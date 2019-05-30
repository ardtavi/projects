package ro.utcn.sd.dao;

import ro.utcn.sd.entities.Parkinglot;

public interface ParkinglotDao extends Dao<Parkinglot> {

    @Override
    Parkinglot find(long id);

    @Override
    void delete(Parkinglot objectToDelete);

    @Override
    void update(Parkinglot objectToUpdate);

    @Override
    void insert(Parkinglot objectToCreate);
}
