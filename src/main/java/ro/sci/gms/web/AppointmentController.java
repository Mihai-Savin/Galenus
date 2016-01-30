package ro.sci.gms.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.Appointment;
import ro.sci.gms.service.AppointmentService;
import ro.sci.gms.service.ValidationException;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService aptService;
	
	@RequestMapping("")
	public ModelAndView index() {
		
		try {
			aptService.generateSome();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collection<Appointment> allAppointments = aptService.getAll();
		
		ModelAndView modelAndView  = new ModelAndView("appointment_list");
		modelAndView.addObject("allAppointments", allAppointments);
		
		return modelAndView;
	}

	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, params = "action=add")
	public String add() {
		return "appointment_edit";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, params = "action=edit")
	public String edit(@RequestParam("id") Long id) {
		return "appointment_edit";
	}
}
