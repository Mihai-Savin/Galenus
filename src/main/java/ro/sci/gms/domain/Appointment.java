package ro.sci.gms.domain;

import java.util.Date;

public class Appointment extends AbstractModel {
	Patient patient;
	Doctor doctor;
	String patientName;
	String doctorName;
	Date time;
	String details;

	public void createAppointment(Patient patient, Doctor doctor) {
		this.patient = patient;
		this.doctor = doctor;
		patientName = patient.getName(); //TEMP
		doctorName = doctor.getName(); //TEMP
		details = "default details"; //TEMP
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		this.patientName = patient.getName();
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
		this.doctorName = doctor.getName();
	}

	public Date getTime() {
		return time;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientName() { //Migh need removal. For testing purposes with thymeleaf
		return patientName;
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
