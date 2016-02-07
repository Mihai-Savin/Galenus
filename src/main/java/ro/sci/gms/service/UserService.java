package ro.sci.gms.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.User;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	/*
	 * No instantiation trough new needed, Spring takes care of this.
	 * 
	 * @Autowired was firstly used. After producing more specific classes, this
	 * annotation was not working anymore. Functional@7.2.16:18.
	 */
	@Resource(name = "userDAO")
	private UserDAO<User> userDAO;

	public void save(User user) throws ValidationException {
		LOGGER.debug("Saving: " + user);

		validate(user);
		userDAO.update(user);
	}

	public User get(Long id) {
		LOGGER.debug("Getting user for id: " + id);
		return userDAO.findById(id);
	}

	public Collection<User> getAll() {
		Collection<User> usersList = userDAO.getAll();

		return usersList;
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting user for id: " + id);

		User user = userDAO.findById(id);
		if (user != null) {
			userDAO.delete(user);
			return true;
		}

		return false;
	}

	private void validate(User user) throws ValidationException {
		// Minimal validation. Needs extension.
		if (user == null) {
			throw new ValidationException("Invalid data. [BETA version err: Not enough data.](091)");
		} else {
			System.out.println("Valid data.");
		}
	}
}
