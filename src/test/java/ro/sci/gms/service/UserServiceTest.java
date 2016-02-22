package ro.sci.gms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.ApplicationTests;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;
import ro.sci.gms.temp.Li;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class UserServiceTest {

	@Autowired
	@Resource(name="userService")
	UserService userService;

	private User user = new User();
	
	@Before
	public void setup() {
		user.setUsername("nora.anghel.1");
		user.setPassword("sunt o blondina superba");
		user.setFirstName("NorA");
		user.setLastName("AngheL");
		user.setAddress("Cluj-Napoca");
		user.setPhone("+40 744 555 777");
		user.setEmail("nora.anghel@fantasyWorld.org");
		user.setRole(Role.user);
	}
	
	@After
	public void tearDown() {
		Collection<User> usersList = new LinkedList<>(userService.getAll());

		for (User user : usersList) {
			userService.delete(user.getId());
		}
	}

	@Test
	public void checkSaveUser_valid() throws ValidationException {
		userService.save(user);

		assertTrue(user.getId() > 0);

		System.out.println(user.getId());
	}

	@Test(expected = ValidationException.class)
	public void checkSaveUser_invalid_noUsername() throws ValidationException {
		user.setUsername(null);
		userService.save(user);
	}
	
	@Test(expected = ValidationException.class)
	public void checkSaveUser_invalid_noPassword() throws ValidationException {
		user.setPassword(null);
		userService.save(user);
	}
	
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
	public void checkGetUser_invalid() {
		Long dubiousId = 6789L; 
		User retrieved = userService.get(dubiousId);
		assertNull(retrieved);
	}
	
	@Test
	public void checkGetAllUsers_valid() throws ValidationException {
		userService.save(user);
		Collection<User> usersList = new LinkedList<>(userService.getAll());
		assertTrue(usersList.size() > 0);
	}
	
	@Test
	public void checkGetAllUsers_invalid() throws ValidationException {
		Collection<User> usersList = new LinkedList<>(userService.getAll());
		assertEquals(0, usersList.size());
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
	
	@Test
	public void checkDeleteUser_invalid() {
		Long dubiousId = 6789L;
		
		boolean result = userService.delete(dubiousId);
	
		assertTrue(!result);
	}
	
	@Test
	public void checkGetDeletedUser() throws ValidationException {
		userService.save(user);
		userService.delete(user.getId());
		User retrieved = userService.get(user.getId());
		assertNull(retrieved);
	}
	
}
