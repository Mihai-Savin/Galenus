package ro.sci.gms.service;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import ro.sci.gms.domain.Agenda;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Doctor;

public class RandomTest {

	AppointmentService aptService = new AppointmentService();
	
	@Test
	public void seeHours() {
//		Doctor doctor = new Doctor();
//		Date day = new Date();
//		Collection<Integer> availableHours = aptService.getAvailableHours(doctor, day);
//		System.out.println("Existent appointment(s) at :");
//		
//		for (Integer hour : availableHours) {
//			System.out.println(hour);
//		}
//		
//		System.out.println();
	}
	
	@Test
	public void runBooking() {
		try {
			aptService.generateSome();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aptService.getAll();
		
		
		Doctor doctor = new Doctor();
		Agenda agenda1 = new Agenda();
		Agenda agenda2 = new Agenda();
		
		doctor.setAgenda(agenda1);
		
		agenda2 = doctor.getAgenda();
		Date today = new Date();
		today.setMonth(2); // not in weekend :)
		
		agenda2.book(today, 9);
		agenda2.book(today, 11);
		agenda2.book(today, 16);
		System.out.println("Booked hours: " + agenda2.getBookedHours(today));
		agenda2.cancelBooking(today, 9);
		
		System.out.println("Booked hours: " + agenda2.getBookedHours(today));
		
		System.out.println("Available hours: " + agenda2.getAvailableHours(today));
		
//		agenda2.book(today, 12);
//		
//		System.out.println("Available hours: " + agenda2.getAvailableHours(today));
		
	}
	
	@Test
	public void checkStuff() {
		try {
			aptService.generateSome();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<Appointment> allAppointments = aptService.getAll();
		
		Long aptID = null;
		
		for (Appointment appointment : allAppointments) {
			aptID = appointment.getId();
		}
		Appointment myAppointment = aptService.get(aptID);
		
		
		Doctor doctor = myAppointment.getDoctor();
		
		Date day = myAppointment.getDate();

		System.out.println(myAppointment.list());
		System.out.println(aptService.getAvailableHours(doctor, day));
		
		aptService.delete(myAppointment.getId());
		System.out.println(aptService.getAvailableHours(doctor, day));
	}
	

}
