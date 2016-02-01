package ro.sci.gms.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import ro.sci.gms.service.ValidationException;

public class Agenda {

	HashMap<Date, LinkedList<Integer>> agenda = new HashMap<>();

	public LinkedList<Integer> getAvailableHours(Date date) {

		try {
			validate(date);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LinkedList<Integer> bookedHours;
		LinkedList<Integer> availableHours = new LinkedList<>();

		if (agenda.containsKey(date)) {
			bookedHours = agenda.get(date);
			for (Integer hour = 8; hour < 17; hour++) {
				if (!bookedHours.contains(hour)) {
					availableHours.add(hour);
				}
			}
		} else {
			for (Integer hour = 8; hour < 17; hour++) {
				availableHours.add(hour);
			}
		}

		return availableHours;

	}

	public LinkedList<Integer> getBookedHours(Date day) {
		return agenda.get(day);
	}

	public void book(Date date, Integer hour) {
		try {
			validate(date);
			validate(hour);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		date.setHours(0);
		date.setMinutes(0);
		
		LinkedList<Integer> hours;

		if (agenda.containsKey(date)) {
			hours = agenda.get(date);
		} else {
			hours = new LinkedList<>();
		}

		if (!hours.contains(hour)) {
			hours.add(hour);
		}

		agenda.put(date, hours);
	}

	public void cancelBooking(Date date, Integer hour) {
		try {
			validate(date);
			validate(hour);
			validateBooking(date, hour);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		date.setHours(0);
		date.setMinutes(0);

		LinkedList<Integer> hours;

		hours = agenda.get(date);
		hours.remove(hour);

		agenda.put(date, hours);

	}

	private void validateBooking(Date date, Integer hour) throws ValidationException {
		LinkedList<Integer> hours;

		if (agenda.containsKey(date)) {
			hours = agenda.get(date);
			if (!hours.contains(hour)) {
				throw new ValidationException("No bookings at this time.");
			}
		} else {
			throw new ValidationException("No bookings on this date.");
		}
	}

	private void validate(Date date) throws ValidationException {
		if ((date.getDay() == 0 || date.getDay() == 6)) {
			throw new ValidationException("Valid operations on week-days only.");
		}

	}

	private void validate(Integer hour) throws ValidationException {
		if (hour < 8 || hour > 17) {
			throw new ValidationException("Valid operations between 8-17 only.");
		}
	}

}
