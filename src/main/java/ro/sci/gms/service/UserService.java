package ro.sci.gms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.GrantedAuthorityImpl;
import ro.sci.gms.domain.User;
@Service
public class UserService implements UserDetailsService{
	//private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDAO userDAO;
	
	public User save(User user) {
		if (user.getId() <= 0 && !userDAO.searchByName(user.getLastName()).isEmpty()) {
			throw new IllegalArgumentException("Duplicated User");
		}
		return userDAO.update(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user != null) {
			System.out.println("Fetching login details for " + user.toString());
			String role = ""+user.getRole();
			List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
			gas.add(new GrantedAuthorityImpl(role));
			user.setAuthorities(gas);
//			UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true,
//					true, true, AuthorityUtils.createAuthorityList(role));
			return user;
		}
		else {
			throw new UsernameNotFoundException("The username was not found");
		}
	}
}

