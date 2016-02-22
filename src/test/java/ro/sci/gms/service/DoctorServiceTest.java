package ro.sci.gms.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.ApplicationTests;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class DoctorServiceTest {

	@Autowired
	DoctorService doctorService;

	private Doctor doctor = new Doctor();

	@After
	public void tearDown() {
		Collection<Doctor> doctorsList = new LinkedList<>(doctorService.getAllDoctors());

		for (User user : doctorsList) {
			doctorService.delete(user.getId());
		}
	}

	@Before
	public void setup() {
		doctor.setUsername("lidia.buble");
		doctor.setPassword("cantFrumos2016");
		doctor.setFirstName("Lidia");
		doctor.setLastName("Buble");
		doctor.setAddress("Toronto");
		doctor.setPhone("+40 744 555 777");
		doctor.setEmail("lidia.buble@fantasyWorld.org");
		doctor.setRole(Role.user);

		doctor.setTitle("dr.");
		doctor.setSpecialty("ORL");
		doctor.setYearsOfExperience(10);
		doctor.setPatient(new Patient());
	}

	@Test
	public void checkSaveDoctor_valid() throws ValidationException, SQLException {
		doctorService.save(doctor);

		assertTrue(doctor.getId() > 0);

		System.out.println(doctor.getId());
	}

	@Test(expected = ValidationException.class)
	public void checkSaveDoctor_invalid_noUsername() throws ValidationException, SQLException {
		doctor.setUsername(null);
		doctorService.save(doctor);
	}
	
	@Test(expected = ValidationException.class)
	public void checkSaveDoctor_invalid_noPassword() throws ValidationException, SQLException {
		doctor.setPassword(null);
		doctorService.save(doctor);
	}
	
	@Test
	public void checkGetDoctor_valid() throws SQLException {
		Doctor saved = null;
		try {
			doctorService.save(doctor);
			saved = doctor;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		Doctor retrieved = doctorService.getDoctor(id);

		assertEquals(saved.getId(), retrieved.getId());

		System.out.println(retrieved.getFullName());
	}

	@Test
	public void checkGetDoctor_invalid() {
		Long dubiousId = 6789L; 
		Doctor retrieved = doctorService.getDoctor(dubiousId);
		assertNull(retrieved);
	}
	
	@Test
	public void checkGetAllDoctors_valid() throws ValidationException, SQLException {
		doctorService.save(doctor);
		Collection<Doctor> usersList = new LinkedList<>(doctorService.getAllDoctors());
		assertTrue(usersList.size() > 0);
	}
	
	@Test
	public void checkGetAllDoctors_invalid() throws ValidationException {
		Collection<Doctor> usersList = new LinkedList<>(doctorService.getAllDoctors());
		assertEquals(0, usersList.size());
	}
	
	@Test
	public void checkDeleteDoctor_valid() throws SQLException {
		Doctor saved = null;
		try {
			doctorService.save(doctor);
			saved = doctor;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		doctorService.delete(id);

		assertNull(doctorService.get(id));

		if (null == doctorService.get(id)) {
			System.out.println("Patient was deleted.");
		}
	}
	
	@Test
	public void checkDeleteDoctor_invalid() {
		Long dubiousId = 6789L;
		
		boolean result = doctorService.delete(dubiousId);
	
		assertTrue(!result);
	}

	@Test
	public void checkGetDeletedDoctor() throws ValidationException, SQLException {
		doctorService.save(doctor);
		doctorService.delete(doctor.getId());
		Doctor retrieved = doctorService.getDoctor(doctor.getId());
		assertNull(retrieved);
	}
}