package ro.sci.gms.service;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.sci.gms.dao.inmemory.IMAppointmentDAO;
import ro.sci.gms.domain.Agenda;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.User;

@Service
@RestController
@RequestMapping("/rest/appointments")
public class AppointmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentService.class);

	@Autowired
	private IMAppointmentDAO dao = new IMAppointmentDAO();

	// @RequestMapping(value = "/{doctor}&{day}", method = RequestMethod.GET)
	public Collection<Integer> getAvailableHours(Doctor doctor, Date date) {

		Collection<Integer> availableHours //
		= doctor.getAgenda().getAvailableHours(date);

		return availableHours;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(Appointment appointment) throws ValidationException {
		LOGGER.debug("Saving: " + appointment);
		validate(appointment);

//		BEGIN --- This goes to the userDAO code ---
		Doctor doctor = appointment.getDoctor();
		Agenda agenda = doctor.getAgenda();
		Date date = appointment.getDate();
		Integer time = appointment.getTime().getHours();
		agenda.book(date, time);
//		 END --- This goes to the userDAO code ---

		dao.update(appointment);

		// return appointment;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Appointment get(@PathVariable("id") Long id) {

		LOGGER.debug("Getting appointment for id: " + id);
		return dao.findById(id);

	}

	public Collection<Appointment> getAll(User user) {

		Collection appointmentsList = dao.getAll(user);

		return appointmentsList;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Appointment> getAll() {

		Collection appointmentsList = dao.getAll();

		return appointmentsList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable("id") Long id) {
		LOGGER.debug("Deleting appointment for id: " + id);
		Appointment apt = dao.findById(id);

		// BEGIN --- This goes to the userDAO code ---
		Doctor doctor = apt.getDoctor();
		Agenda agenda = doctor.getAgenda();
		Date date = apt.getDate();
		Integer time = apt.getTime().getHours();
		agenda.cancelBooking(date, time);
		// END --- This goes to the userDAO code ---

		if (apt != null) {
			dao.delete(apt);
			return true;
		}

		return false;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "query")
	Collection<Appointment> search(@RequestParam(value = "query") String query) {
		LOGGER.debug("Searching for " + query);
		return dao.search(query);
	}
	

	private void validate(Appointment appointment) throws ValidationException {
		if (appointment == null) {
			throw new ValidationException("Invalid data. [BETA version err: Not enough data.]");
		} else {
			System.out.println("Valid data.");
		}
	}

	public void generateSome() throws ValidationException { // MOCK method for
															// App testing
															// purposes only
		Patient patient1 = new Patient();
		// Patient patient2 = new Patient();
		Doctor doctor1 = new Doctor();
		// Doctor doctor2 = new Doctor();
		patient1.setLastName("Lopez");
		patient1.setFirstName("Jennifer");
		// patient2.setLastName("Salma");
		// patient2.setFirstName("Hayek");
		doctor1.setLastName("Sigmund");
		doctor1.setFirstName("Freud");
		doctor1.setAgenda(new Agenda());
		// doctor2.setLastName("Albert");
		// doctor2.setFirstName("Adler");

		Date date = new Date();

		Date time1 = new Date();
		Date time2 = new Date();

		date.setDate(22);
		date.setHours(0);

		time1.setHours(8);
		time2.setHours(13);

		Appointment appointment1 = new Appointment();
		Appointment appointment2 = new Appointment();

		// Appointment appointment3 = new Appointment(patient2, doctor1);
		// Appointment appointment4 = new Appointment(patient2, doctor2);
		appointment1.createAppointment(patient1, doctor1);
		appointment2.createAppointment(patient1, doctor1);

		appointment1.setDate(date);
		appointment1.setTime(time1);

		appointment2.setDate(date);
		appointment2.setTime(time2);

		// appointment2.setTime(new Date());
		// appointment3.setTime(new Date());
		// appointment4.setTime(new Date());
		save(appointment1);
		save(appointment2);
		// save(appointment2);
		// save(appointment3);
		// save(appointment4);
	}
}
