package ro.sci.ems.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.sci.ems.dao.AppointmentDAO;
import ro.sci.ems.domain.Appointment;
import ro.sci.ems.domain.Doctor;
import ro.sci.ems.domain.Employee;
import ro.sci.ems.domain.Patient;

@Service
public class AppointmentService {
	
	private AppointmentDAO dao;
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveAppointment(Appointment appointment) throws ValidationException {
//		LOGGER.debug("Saving: " + appointment);
		validate(appointment);

		dao.update(appointment);

	}
	
	public Collection<Appointment> getAllAppointments(Doctor doctor){
		
		Collection appointmentsList = new ArrayList<Appointment>();
		appointmentsList.add(new Appointment(new Patient(), new Doctor())); //MOCK
		
		return appointmentsList;
	}
	
	public Collection<Appointment> getAllAppointments(Patient patient){
		
		Collection appointmentsList = new ArrayList<Appointment>();
		appointmentsList.add(new Appointment(new Patient(), new Doctor())); //MOCK
		
		return appointmentsList;
	}
	
//	Collection<Employee> search(@RequestParam(value = "query") String query) {
//		LOGGER.debug("Searching for " + query);
//		return dao.searchByName(query);
//	}
	
//	public boolean deleteAppointment(Long id){
//		Appointment appointment = new Appointment();
//		if (appointment != null) {
//			dao.delete(appointment);
//			return true;
//		}
//		return false;
//	}

	
	private void validate(Appointment appointment) throws ValidationException {
		System.out.println("Not implemented yet.");
	}
}
