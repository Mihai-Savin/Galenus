package ro.sci.gms.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.User;

@Repository
public class IMUserDAO extends IMBaseDAO<User> implements UserDAO {

	public Collection<User> searchById(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<User> all = new LinkedList<>(getAll());
		for (Iterator<User> it = all.iterator(); it.hasNext();) {
			User user = it.next();
		}
		return all;
	}

	public Collection<User> getAll(User user) {

		Collection<User> all = new LinkedList<>(getAll());
		Collection<User> usersAppointments = new LinkedList<>();

//		for (User user : all) {
//			if ((appointment.getDoctor().equals(user)) || (appointment.getPatient().equals(user))) {
//				usersAppointments.add(appointment);
//			}
//		}

		return usersAppointments;
	}
	
	public Collection<User> search(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		
		Collection<User> all = new LinkedList<>(getAll());
//		for (Iterator<User> it = all.iterator(); it.hasNext();) {
//			User apt = it.next();
//			String bulkData = apt.getDoctorName() + " " + apt.getPatientName() + //
//					" " + apt.getDate() + " " + apt.getTime() + " " + apt.getDetails();
//			if (!bulkData.toLowerCase().contains(query.toLowerCase())) {
//				it.remove();
//			}
//		}
		return all;
	}

	
}
