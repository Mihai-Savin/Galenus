package ro.sci.ems.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.sci.ems.domain.Doctor;
import ro.sci.ems.domain.Patient;

public class AppointmentServiceTest {

	AppointmentService appointmentSv = new AppointmentService();
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void checkGetAllAppointments_doctor_valid() {
		Assert.assertNotNull(appointmentSv.getAllAppointments(new Doctor()));
	}
	
	@Test
	public void checkGetAllAppointments_patient_valid() {
		Assert.assertNotNull(appointmentSv.getAllAppointments(new Patient()));
	}


}
