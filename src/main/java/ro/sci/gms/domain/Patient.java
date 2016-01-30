package ro.sci.gms.domain;

import java.util.Collection;
import java.util.Date;

public class Patient extends User {
	private Date dateOfBirth;
	private Gender gender;
	private Collection medicalBackground;
	private Blood bloodType;
	
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
	public Collection getMedicalBackground() {
		return medicalBackground;
	}
	public void setMedicalBackground(Collection medicalBackground) {
		this.medicalBackground = medicalBackground;
	}
	public Blood getBloodType() {
		return bloodType;
	}
	public void setBloodType(Blood bloodType) {
		this.bloodType = bloodType;
	}
	

}
