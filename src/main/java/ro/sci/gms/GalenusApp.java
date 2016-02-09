package ro.sci.gms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.gms.dao.AppointmentDAO;
import ro.sci.gms.dao.DiscussionDAO;
import ro.sci.gms.dao.inmemory.IMAppointmentDAO;
import ro.sci.gms.dao.inmemory.IMDiscussionDAO;
import ro.sci.gms.domain.Agenda;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class GalenusApp {
	public static void main(String[] args) {
		SpringApplication.run(GalenusApp.class, args);
		
	}
	
	@Bean
	public AppointmentDAO aptDAO() {
		return new IMAppointmentDAO();
				 //JDBCEmployeeDao("localhost", "5432", "test", "test", "test");
	}
	
	@Bean
	public DiscussionDAO discussionDAO() {
		return new IMDiscussionDAO();
	}
	
	@Bean
	public Patient loggedPatient() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("Arnold");
		doctor.setLastName("Schwarzenegger");
		doctor.setId(1);
		Agenda agenda = new Agenda();
		doctor.setAgenda(agenda);
		Patient patient = new Patient();
		patient.setFirstName("Angela");
		patient.setLastName("Merkel");
		patient.setId(2);
		patient.setDoctor(doctor);
		return patient;
	}
	
	@Bean
	public Doctor loggedDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("Arnold");
		doctor.setLastName("Schwarzenegger");
		doctor.setId(1);
		Agenda agenda = new Agenda();
		doctor.setAgenda(agenda);
		Patient patient = new Patient();
		patient.setFirstName("Angela");
		patient.setLastName("Merkel");
		patient.setId(2);
		doctor.setPatient(patient);
		return doctor;
	}

}
