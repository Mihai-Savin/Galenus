package ro.sci.gms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.User;
@Service
public class UserService {
	//private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDAO dao;
	
	public User save(User user) {
		if (user.getId() <= 0 && !dao.searchByName(user.getLastName()).isEmpty()) {
			throw new IllegalArgumentException("Duplicated User");
		}
		return dao.update(user);
	}

/*	@RequestMapping(method = RequestMethod.GET)
	public Collection<Employee> listAll() {
		return dao.getAll();
	}

	@RequestMapping(method = RequestMethod.GET, params = "query")
	Collection<Employee> search(@RequestParam(value = "query") String query) {
		LOGGER.debug("Searching for " + query);
		return dao.searchByName(query);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable("id") Long id) {
		LOGGER.debug("Deleting employee for id: " + id);
		Employee emp = dao.findById(id);
		if (emp != null) {
			dao.delete(emp);
			return true;
		}

		return false;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Employee get(@PathVariable("id") Long id) {
		LOGGER.debug("Getting employee for id: " + id);
		return dao.findById(id);

	}*/

/*	@RequestMapping(method = RequestMethod.POST)
	public void save(User user, BindingResult bindingResult) throws ValidationException {
		LOGGER.debug("Saving: " + user);
//		validate(employee);

		dao.update(user);
	}*/

	/*private void validate(Employee employee) throws ValidationException {
		Date currentDate = new Date();
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(employee.getFirstName())) {
			errors.add("First Name is Empty");
		}

		if (StringUtils.isEmpty(employee.getLastName())) {
			errors.add("Last Name is Empty");
		}

		if (employee.getGender() == null) {
			errors.add("Gender is Empty");
		}

		if (StringUtils.isEmpty(employee.getJobTitle())) {
			errors.add("JobTitle is Empty");
		}

		if (employee.getBirthDate() == null) {
			errors.add("BirthDate is Empty");
		} else {
			if (currentDate.before(employee.getBirthDate())) {
				errors.add("Birthdate in future");
			}
			
			Calendar c = GregorianCalendar.getInstance();
			c.setTime(new Date());
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 18);
			if (employee.getBirthDate().after(c.getTime())) {
				errors.add("Too young to get employeed");
			}
			
			c.set(Calendar.YEAR, 1901);
			if (employee.getBirthDate().before(c.getTime())) {
				errors.add("Too old to get employeed");
			}
			
		}

		if (employee.getEmploymentDate() == null) {
			errors.add("EmploymentDate is Empty");
		} else {
			if (currentDate.before(employee.getEmploymentDate())) {
				errors.add("EmploymentDate in future");
			}
		}
		
		if (employee.getBirthDate() != null && employee.getEmploymentDate() != null) {
			if (employee.getEmploymentDate().before(employee.getBirthDate())) {
				errors.add("EmploymentDate before BirthDate");
			}
		}
		
		

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}*/
}

