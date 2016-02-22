package ro.sci.gms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
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
import ro.sci.gms.domain.Blood;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Gender;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class PatientServiceTest {

	@Autowired
	PatientService patientService;

	private Patient patient = new Patient();

	@After
	public void tearDown() {
		Collection<Patient> patientsList = new LinkedList<>(patientService.getAllPatients());

		for (User user : patientsList) {
			patientService.delete(user.getId());
		}
	}

	@Before
	public void setup() {
		patient.setUsername("lidia.buble");
		patient.setPassword("cantFrumos2016");
		patient.setFirstName("Lidia");
		patient.setLastName("Buble");
		patient.setAddress("Toronto");
		patient.setPhone("+40 744 555 777");
		patient.setEmail("lidia.buble@fantasyWorld.org");
		patient.setRole(Role.user);

		patient.setDateOfBirth(new Date());
		patient.setGender(Gender.FEMALE);
		patient.setMedicalBackground("I www to sing sing. Uuu...");
		patient.setBloodType(Blood.A);
		patient.setDoctor(new Doctor());
	}

	@Test
	public void checkSavePatient_valid() throws ValidationException, SQLException {
		patientService.save(patient);

		assertTrue(patient.getId() > 0);

		System.out.println(patient.getId());
	}
	
	@Test(expected = ValidationException.class)
	public void checkSavePatient_invalid_noUsername() throws ValidationException, SQLException {
		patient.setUsername(null);
		patientService.save(patient);
	}
	
	@Test(expected = ValidationException.class)
	public void checkSavePatient_invalid_noPassword() throws ValidationException, SQLException {
		patient.setPassword(null);
		patientService.save(patient);
	}

	
	@Test
	public void checkGetPatient_valid() throws SQLException {
		Patient saved = null;
		try {
			patientService.save(patient);
			saved = patient;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		Patient retrieved = patientService.getPatient(id);

		assertEquals(saved.getId(), retrieved.getId());

		System.out.println(retrieved.getFullName());
	}

	@Test
	public void checkGetPatient_invalid() {
		Long dubiousId = 6789L; 
		Patient retrieved = patientService.getPatient(dubiousId);
		assertNull(retrieved);
	}
	
	@Test
	public void checkGetAllPatients_valid() throws ValidationException, SQLException {
		patientService.save(patient);
		Collection<Patient> usersList = new LinkedList<>(patientService.getAllPatients());
		assertTrue(usersList.size() > 0);
	}
	
	@Test
	public void checkGetAllPatients_invalid() throws ValidationException {
		Collection<Patient> usersList = new LinkedList<>(patientService.getAllPatients());
		assertEquals(0, usersList.size());
	}
	
	@Test
	public void checkDeletePatient_valid() throws SQLException {
		Patient saved = null;
		try {
			patientService.save(patient);
			saved = patient;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		patientService.delete(id);

		assertNull(patientService.get(id));

		if (null == patientService.get(id)) {
			System.out.println("Patient was deleted.");
		}
	}

	@Test
	public void checkDeletePatient_invalid() {
		Long dubiousId = 6789L;
		
		boolean result = patientService.delete(dubiousId);
	
		assertTrue(!result);
	}

	@Test
	public void checkGetDeletedPatient() throws ValidationException, SQLException {
		patientService.save(patient);
		patientService.delete(patient.getId());
		Patient retrieved = patientService.getPatient(patient.getId());
		assertNull(retrieved);
	}
}