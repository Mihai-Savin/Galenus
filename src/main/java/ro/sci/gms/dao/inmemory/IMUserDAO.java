package ro.sci.gms.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.User;

@Repository
public class IMUserDAO extends IMBaseDAO<User> implements UserDAO {

	@Override
	public Collection<User> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		
		Collection<User> all = new LinkedList<User>(getAll());
		for (Iterator<User> it = all.iterator(); it.hasNext();) {
			User usr = it.next();
			String ss = usr.getFirstName() + " " + usr.getLastName();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}
	
	@Override
	public User findByUsername(String username) {
		Collection<User> users = new LinkedList<User>(getAll());
		for (User user:users){
			if (username.equals(user.getUsername())) {
				return user;
			}
		}
		return null;
	}

	

}
