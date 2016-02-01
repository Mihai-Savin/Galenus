package ro.sci.gms.dao;

import java.util.Collection;

import ro.sci.gms.domain.User;

public interface UserDAO extends BaseDAO<User>{

	Collection<User> searchById(String query);
}
