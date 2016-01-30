package ro.sci.gms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.gms.Application;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AppointmentServiceTest {

	AppointmentService aptService = new AppointmentService();

	private Patient patient1 = new Patient();
	private Patient patient2 = new Patient();
	private Doctor doctor1 = new Doctor();
	private Doctor doctor2 = new Doctor();
	private Appointment appointment1 = new Appointment(patient1, doctor1);
	private Appointment appointment2 = new Appointment(patient1, doctor2);
	private Appointment appointment3 = new Appointment(patient2, doctor1);
	private Appointment appointment4 = new Appointment(patient2, doctor2);
	
	@Before
	public void setup() {
		patient1.setLastName("Lopez");
		patient1.setFirstName("Jennifer");
		patient2.setLastName("Salma");
		patient2.setFirstName("Hayek");
		doctor1.setLastName("Sigmund");
		doctor1.setFirstName("Freud");
		doctor2.setLastName("Albert");
		doctor2.setFirstName("Adler");
		appointment1.setTime(new Date());
		appointment2.setTime(new Date());
		appointment3.setTime(new Date());
		appointment4.setTime(new Date());
	}

	@Test
	public void checkSaveAppointment_valid() {
		Appointment saved = null;
		try {
			saved = aptService.save(appointment1);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(saved);
		assertTrue(saved.getId() > 0);

		System.out.println(appointment1);
	}

	@Test
	public void checkGetAppointment_valid() {
		Appointment saved = null;
		try {
			saved = aptService.save(appointment1);
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		Appointment retrieved = aptService.get(id);

		assertEquals(appointment1, retrieved);

		System.out.println(retrieved.getDetails());
	}

	@Test
	public void checkGetAppointment_noAppointments() {

		Collection<Appointment> all = aptService.getAll(patient1);

		assertEquals(0, all.size());

		System.out.println(all);
	}

	@Test
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

	@Test
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
			System.out.println(ap);
		}
		
		System.out.println(all);
		
		assertEquals(2, all.size());

	}

	@Test
	public void checkDeleteAppointment_valid() {
		Appointment saved = null;
		try {
			saved = aptService.save(appointment1);
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

}
