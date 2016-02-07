package ro.sci.gms.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.GrantedAuthorityImpl;
import ro.sci.gms.domain.User;

//@Configurable
//@Transactional
@Component("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	@Qualifier("securityDao")
//	private SecurityDAO securityDAO;
	@Autowired
	private UserDAO userDAO;

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
	
	private String hashPassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		byte[] digest = md.digest();
		String hashedPassword = String.format("%064x", new java.math.BigInteger(1, digest));
		return hashedPassword;
	}

	// public static List<GrantedAuthority> getGrantedAuthorities(Set<Role>
	// roles) {
	// List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	//
	// for (Role role : roles) {
	// authorities.add(new SimpleGrantedAuthority(role.getName()));
	// }
	// return authorities;
	// }

//	public SecurityDAO getSecurityDAO() {
//		return securityDAO;
//	}
//
//	public void setSecurityDAO(SecurityDAO securityDAO) {
//		this.securityDAO = securityDAO;
//	}

}
