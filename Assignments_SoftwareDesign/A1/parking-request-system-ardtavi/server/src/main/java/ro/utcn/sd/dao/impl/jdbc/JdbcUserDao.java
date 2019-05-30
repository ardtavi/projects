package ro.utcn.sd.dao.impl.jdbc;

import ro.utcn.sd.dao.UserDao;
import ro.utcn.sd.entities.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JdbcUserDao implements UserDao {
    @Override
    public User find(long id) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(User objectToDelete) {
        throw new NotImplementedException();
    }

    @Override
    public void update(User objectToUpdate) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(User objectToCreate) {
        throw new NotImplementedException();
    }
}
