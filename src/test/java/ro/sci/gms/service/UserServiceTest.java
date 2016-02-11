package ro.sci.gms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.ApplicationTests;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;
import ro.sci.gms.temp.Li;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class UserServiceTest {

//	@Autowired
	@Resource(name="userService")
	UserService userService;

	private User user = new User();
	
	@After
	public void tearDown() {
//		Collection<Appointment> appointments = new LinkedList<>(aptService.getAll());
//
//		for (Appointment apt : appointments) {
//			aptService.delete(apt.getId());
//		}
	}

	@Before
	public void setup() {
		user.setUsername("nora.anghel");
		user.setPassword("sunt o blonda superba");
		user.setFirstName("Nora");
		user.setLastName("Anghel");
		user.setAddress("Cluj-Napoca");
		user.setPhone("+40 744 555 777");
		user.setEmail("nora.anghel@fantasyWorld.org");
		user.setRole(Role.user);
	}

	@Test
	public void checkSaveUser_valid() throws ValidationException {
		userService.save(user);

		assertTrue(user.getId() > 0);

		System.out.println(user.getId());
	}

	 
//	@Test(expected = ValidationException.class)
//	public void checkSaveUser_double_save() throws ValidationException {
//		userService.save(user);
//		Li.st(user.getId());
//		userService.save(user);
//		Li.st(user.getId());
//	}
//	
	
	@Test
	public void checkGetUser_valid() {
		User saved = null;
		try {
			userService.save(user);
			saved = user;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		User retrieved = userService.get(id);

		assertEquals(saved.getId(), retrieved.getId());

		System.out.println(retrieved.getFullName());
	}

	@Test
	public void checkDeleteUser_valid() {
		User saved = null;
		try {
			userService.save(user);
			saved = user;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		Long id = saved.getId();
	
		userService.delete(id);
	
		assertNull(userService.get(id));
	
		if (null == userService.get(id)) {
			System.out.println("User was deleted.");
		}
	}

	
}
