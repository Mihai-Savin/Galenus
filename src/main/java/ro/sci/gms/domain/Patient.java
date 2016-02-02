package ro.sci.gms.domain;

import java.util.Collection;
import java.util.Date;

public class Patient extends User {
	private Date dateOfBirth;
	private Gender gender;
	private Collection<String> medicalBackground;
	private Blood bloodType;
	private Doctor doctor;
	
	
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Collection<String> getMedicalBackground() {
		return medicalBackground;
	}
	public void setMedicalBackground(Collection<String> medicalBackground) {
		this.medicalBackground = medicalBackground;
	}
	public Blood getBloodType() {
		return bloodType;
	}
	public void setBloodType(Blood bloodType) {
		this.bloodType = bloodType;
	}
	

}
