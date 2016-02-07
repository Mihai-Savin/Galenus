package ro.sci.gms.dao;

import java.util.Collection;

import ro.sci.gms.domain.User;

public interface UserDAO extends BaseDAO<User>{

	Collection<User> searchByName(String query);
	User findByUsername(String username);
}
