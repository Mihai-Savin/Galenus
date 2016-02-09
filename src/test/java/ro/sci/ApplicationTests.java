package ro.sci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.gms.dao.AppointmentDAO;
import ro.sci.gms.dao.inmemory.IMAppointmentDAO;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationTests {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationTests.class, args);
		
	}
	
	@Bean
	public AppointmentDAO aptDAO() {
		return new IMAppointmentDAO();
				 //JDBCEmployeeDao("localhost", "5432", "test", "test", "test");
	}

}
