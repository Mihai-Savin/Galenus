package ro.sci.ems.dao;

import ro.sci.ems.domain.User;

public interface SecurityDAO {
	public boolean validateUser(User user);

	public User authenticateUser(User user);

	public User getUser(User user);

}
