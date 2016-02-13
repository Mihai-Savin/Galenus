package ro.sci;

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
import ro.sci.gms.service.DoctorService;
import ro.sci.gms.service.PatientService;
import ro.sci.gms.service.UserService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationTests {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationTests.class, args);

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
	public AppointmentDAO aptDAO() {
		return new IMAppointmentDAO();
		// JDBCAppointmentDAO("localhost", "5432", "test", "test", "test");
	}

	@Bean
	public UserDAO userDAO() {
		return new //
		// IMUserDAO();
		JDBCUserDAO("localhost", "5432", "galenus", "postgres", "postgres");
		/*JDBCUserDAO("ec2-79-125-117-94.eu-west-1.compute-1.amazonaws.com",
				"5432", "d99d8uvcdiqh5q",
				"gsmxwxyrbiqutc", 
				"ifm7QuPfDxj7VYVqReCWKKQp9Z");
	*/
	
		
		
	
	}

	@Bean
	public JDBCPatientDAO patientDAO() {
		return new // IMUserDAO();
		JDBCPatientDAO("localhost", "5432", "galenus", "postgres", "postgres");
	}

	@Bean
	public JDBCDoctorDAO doctorDAO() {
		return new // IMUserDAO();
		JDBCDoctorDAO("localhost", "5432", "galenus", "postgres", "postgres");
	}

}
