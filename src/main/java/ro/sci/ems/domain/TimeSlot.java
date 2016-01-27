package ro.sci.ems.domain;

import java.util.Date;

public class TimeSlot {
	
	private Date date;
	private Long appointmentId;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	

}
