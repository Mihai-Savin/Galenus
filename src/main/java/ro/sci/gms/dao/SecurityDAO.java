package ro.sci.gms.dao;

import ro.sci.gms.domain.User;

public interface SecurityDAO {
	public boolean validateUser(User user);

	public User authenticateUser(User user);

	public User getUser(User user);

}
