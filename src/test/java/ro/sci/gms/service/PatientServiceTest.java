package ro.sci.gms.service;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Date;

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



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class PatientServiceTest {

	@Autowired
	PatientService patientService;

	private Patient patient = new Patient();
	
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
		patient.setUserName("lidia.buble");
		patient.setPassword("cantFrumos2016");
		patient.setFirstName("Lidia");
		patient.setLastName("Buble");
		patient.setAdress("Toronto");
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

	
	 
/*	@Test(expected = ValidationException.class)
	public void checkSaveUser_double_save() throws ValidationException {
		userService.save(user);
		userService.save(user);
	}
*/
/*	@Test
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

		assertEquals(saved, retrieved);

		System.out.println(retrieved);
	}
*/
/*	@Test
	public void checkGetAppointment_noAppointments() {

		Collection<Appointment> all = aptService.getAll(patient1);

		assertEquals(0, all.size());

		System.out.println(all);
	}
*/
/*	@Test
	public void checkGetAllAppointments_patient_valid() {

		try {
			aptService.save(appointment1);
			aptService.save(appointment2);
			aptService.save(appointment3);
			aptService.save(appointment4);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collection<Appointment> all = aptService.getAll(patient1);

		for (Appointment ap : all) {
			System.out.println(ap);
		}

		System.out.println(all);

		assertEquals(2, all.size());

	}
*/
/*	@Test
	public void checkGetAllAppointments_doctor_valid() {

		try {
			aptService.save(appointment1);
			aptService.save(appointment2);
			aptService.save(appointment3);
			aptService.save(appointment4);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collection<Appointment> all = aptService.getAll(doctor1);

		for (Appointment ap : all) {
			System.out.println(ap.list());
		}

		System.out.println(all);

		assertEquals(2, all.size());

	}
*/
/*	@Test
	public void checkDeleteAppointment_valid() {
		Appointment saved = null;
		try {
			aptService.save(appointment1);
			saved = appointment1;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		aptService.delete(id);

		assertNull(aptService.get(id));

		if (null == aptService.get(id)) {
			System.out.println("Appointment was deleted.");
		}
	}
*/
/*	@Test
	public void checkSaveAppointment_saveTwoApts() throws ValidationException {

		aptService.save(appointment1);
		aptService.save(appointment3);

		assertTrue(appointment1.getId() > 0);
		assertTrue(appointment3.getId() > 0);

	}
*/
}
