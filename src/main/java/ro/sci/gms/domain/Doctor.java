package ro.sci.gms.domain;

import java.util.Collection;

public class Doctor extends User {
	private String title;
	private String specialty;
	private int yearsOfExperience;
	private int numberOfPatients;
	private String picture;
	private Collection agenda;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public int getNumberOfPatients() {
		return numberOfPatients;
	}
	public void setNumberOfPatients(int numberOfPatients) {
		this.numberOfPatients = numberOfPatients;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Collection getAgenda() {
		return agenda;
	}
	public void setAgenda(Collection agenda) {
		this.agenda = agenda;
	}
	
	

}
