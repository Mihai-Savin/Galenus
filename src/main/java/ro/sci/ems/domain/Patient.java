package ro.sci.ems.domain;

import java.util.Collection;
import java.util.Date;

public class Patient {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((medicalBackground == null) ? 0 : medicalBackground.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (gender != other.gender)
			return false;
		if (medicalBackground == null) {
			if (other.medicalBackground != null)
				return false;
		} else if (!medicalBackground.equals(other.medicalBackground))
			return false;
		return true;
	}
	

}
