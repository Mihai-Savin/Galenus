package ro.sci.gms.domain;

import java.util.Date;


public class Appointment extends AbstractModel{
	Patient patient;
	Doctor doctor;
	Date time;
	

	public Appointment(Patient patient, Doctor doctor) {
		this.patient = patient;
		this.doctor = doctor;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

}
