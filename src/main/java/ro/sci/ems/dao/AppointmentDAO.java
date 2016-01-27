package ro.sci.ems.dao;

import java.util.Collection;

import ro.sci.ems.domain.Appointment;

public interface AppointmentDAO extends BaseDAO<Appointment>{

	Collection<Appointment> searchById(String query);
}
