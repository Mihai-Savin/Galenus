package ro.sci.gms;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.gms.dao.AppointmentDAO;
import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.dao.db.JDBCDoctorDAO;
import ro.sci.gms.dao.db.JDBCPatientDAO;
import ro.sci.gms.dao.db.JDBCUserDAO;
import ro.sci.gms.dao.inmemory.IMAppointmentDAO;
import ro.sci.gms.dao.inmemory.IMUserDAO;
import ro.sci.gms.domain.Agenda;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;
import ro.sci.gms.service.AppointmentService;
import ro.sci.gms.service.DoctorService;
import ro.sci.gms.service.PatientService;
import ro.sci.gms.service.UserService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class GalenusApp {

	/**
	 * 0 = InMemory; 1 = Local DB; 2 = Production DB on Heroku
	 */
	private int deployTo = 2 //
	;

	public static void main(String[] args) {
		SpringApplication.run(GalenusApp.class, args);

	}

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public PatientService patientService() {
		return new PatientService();
	}

	@Bean
	public DoctorService doctorService() {
		return new DoctorService();
	}

	@Bean
	public AppointmentService aptService() {
		return new AppointmentService();
	}
	
	
	@Bean
	public AppointmentDAO aptDAO() {
		return new IMAppointmentDAO();
		// JDBCAppointmentDAO("localhost", "5432", "test", "test", "test");
	}

	@Bean
	public UserDAO userDAO() {
		switch (deployTo) {
		case 1:
			return new JDBCUserDAO("localhost", "5432", "galenus", "postgres", "postgres");
		case 2:
			return new JDBCUserDAO("ec2-79-125-117-94.eu-west-1.compute.amazonaws.com", "5432", "d99d8uvcdiqh5q",
					"hjgepgsapjoops", "7wxWIzK0dN6Ea5vkIJ1WYvyT9p");
		}
		return new IMUserDAO();
	}

	@Bean
	public UserDAO patientDAO() {
		switch (deployTo) {
		case 1:
			return new JDBCPatientDAO("localhost", "5432", "galenus", "postgres", "postgres");
		case 2:
			return new JDBCPatientDAO("ec2-79-125-117-94.eu-west-1.compute.amazonaws.com", "5432", "d99d8uvcdiqh5q",
					"hjgepgsapjoops", "7wxWIzK0dN6Ea5vkIJ1WYvyT9p");
		}
		return new IMUserDAO();
	}

	@Bean
	public UserDAO doctorDAO() {
		switch (deployTo) {
		case 1:
			return new JDBCDoctorDAO("localhost", "5432", "galenus", "postgres", "postgres");
		case 2:
			return new JDBCDoctorDAO("ec2-79-125-117-94.eu-west-1.compute.amazonaws.com", "5432", "d99d8uvcdiqh5q",
					"hjgepgsapjoops", "7wxWIzK0dN6Ea5vkIJ1WYvyT9p");
		}
		return new IMUserDAO();
	}

	@Bean
	public Patient loggedPatient() {
		Patient patient = new Patient();
		Doctor doctor = new Doctor();
		Agenda agenda = new Agenda();
		doctor.setFirstName("Arnold");
		doctor.setLastName("Schwarzenegger");
		doctor.setAgenda(agenda);
		patient.setId(11226L);
		patient.setFirstName("Angela");
		patient.setLastName("Merkel");
		patient.setDoctor(doctor);
		patient.setUsername("angela.merkel");
		patient.setEmail("angela.merkel@bundesregierung.de");
		patient.setPhone("+49 89 636 48018");
		patient.setMedicalBackground("Loves to cuddle with muslim alien citizens.");
		patient.setDateOfBirth(new Date());
		patient.setPassword("1234");
		patient.setRole(Role.user);

		return patient;
	}

	@Bean
	public Doctor loggedDoctor() {
		return new Doctor();
	}
	
	@Bean
	public User loggedUser() {
		User user = new User();
		user.setFirstName("Angela");
		user.setLastName("Merkel");
		user.setUsername("angela.merkel");
		user.setEmail("angela.merkel@bundesregierung.de");
		user.setPhone("+49 89 636 48018");

		return user;
	}

}
