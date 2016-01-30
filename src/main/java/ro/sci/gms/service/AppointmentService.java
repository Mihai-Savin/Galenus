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
import org.springframework.web.bind.annotation.RestController;

import ro.sci.gms.dao.inmemory.IMAppointmentDAO;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.User;

@Service
@RestController
@RequestMapping("/rest/apointments")
public class AppointmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private IMAppointmentDAO dao = new IMAppointmentDAO(); // for testing
															// purposes only
															// While offline no
															// SPRING Beans
															// Management
															// available.

	@RequestMapping(method = RequestMethod.POST)
	public Appointment save(Appointment appointment) throws ValidationException {
		// LOGGER.debug("Saving: " + appointment);
		validate(appointment);

		dao.update(appointment);

		return appointment;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Appointment get(@PathVariable("id") Long id) {

		LOGGER.debug("Getting employee for id: " + id);
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

		if (apt != null) {
			dao.delete(apt);
			return true;
		}

		return false;
	}

	private void validate(Appointment appointment) throws ValidationException {
		if (appointment == null) {
			throw new ValidationException("Invalid data. [BETA version err: Not enough data.]");
		} else {
			System.out.println("Valid data.");
		}
	}

	public void generateSome() throws ValidationException { // MOCK method for App testing purposes only
		Patient patient1 = new Patient();
		Patient patient2 = new Patient();
		Doctor doctor1 = new Doctor();
		Doctor doctor2 = new Doctor();
		patient1.setLastName("Lopez");
		patient1.setFirstName("Jennifer");
		patient2.setLastName("Salma");
		patient2.setFirstName("Hayek");
		doctor1.setLastName("Sigmund");
		doctor1.setFirstName("Freud");
		doctor2.setLastName("Albert");
		doctor2.setFirstName("Adler");
		Appointment appointment1 = new Appointment(patient1, doctor1);
		Appointment appointment2 = new Appointment(patient1, doctor2);
		Appointment appointment3 = new Appointment(patient2, doctor1);
		Appointment appointment4 = new Appointment(patient2, doctor2);
		appointment1.setTime(new Date());
		appointment2.setTime(new Date());
		appointment3.setTime(new Date());
		appointment4.setTime(new Date());
		save(appointment1);
		save(appointment2);
		save(appointment3);
		save(appointment4);
	}
}
