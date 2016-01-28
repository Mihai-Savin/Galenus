package ro.sci.galenus.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

public class UserService implements UserDetailsManager {

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createUser(UserDetails arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(UserDetails arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean userExists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
