package ro.sci.gms.domain;

import java.util.Date;

public class Appointment extends AbstractModel {
	Patient patient;
	Doctor doctor;
	String patientName;
	String doctorName;
	Date time;
	String details;

	public Appointment(Patient patient, Doctor doctor) {
		this.patient = patient;
		this.doctor = doctor;
		patientName = patient.getName(); //TEMP
		doctorName = doctor.getName(); //TEMP
		details = "default details"; //TEMP
	}

	public Date getTime() {
		return time;
	}

	public String getPatientName() { //Migh need removal. For testing purposes with thymeleaf
		return patientName;
	}
	
	public String getDoctorname() { //Migh need removal. For testing purposes with thymeleaf
		return doctorName;
	}
	

	public Patient getPatient() {
		return patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		StringBuilder appointment = new StringBuilder();

		appointment.append("Appointment ID : " + this.getId() + "\n");
		appointment.append("Patient : " + patient.getName() + "\n");
		appointment.append("Doctor : " + doctor.getName() + "\n");
		appointment.append("Time : " + this.getTime() + "\n");

		return appointment.toString();
	}

}
