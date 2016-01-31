package ro.sci.ems.dao;

import java.util.Collection;

import ro.sci.ems.domain.User;

public interface UserDAO extends BaseDAO<User>{

	Collection<User> searchByName(String query);
	User findByUsername(String username);
}
