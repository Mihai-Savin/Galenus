package ro.sci.gms.dao;

import java.util.Collection;

import ro.sci.gms.domain.Appointment;

public interface AppointmentDAO extends BaseDAO<Appointment>{

	Collection<Appointment> searchById(String query);
}
