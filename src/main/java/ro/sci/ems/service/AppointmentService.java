package ro.sci.ems.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.sci.ems.domain.Appointment;
import ro.sci.ems.domain.Doctor;
import ro.sci.ems.domain.Patient;

@Service
public class AppointmentService {
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveAppointment(Appointment appointment) throws ValidationException {
//		LOGGER.debug("Saving: " + appointment);
//		validate(appointment);

//		dao.update(appointment);
//
	}
	
	public Collection<Appointment> getAllAppointments(Doctor doctor){
		Collection appointmentsList = new ArrayList<Appointment>();
		appointmentsList.add(new Appointment(new Patient(), new Doctor()));
		return appointmentsList;
	}
	
	public Collection<Appointment> getAllAppointments(Patient patient){
		Collection appointmentsList = new ArrayList<Appointment>();
		appointmentsList.add(new Appointment(new Patient(), new Doctor()));
		return appointmentsList;
	}

}
