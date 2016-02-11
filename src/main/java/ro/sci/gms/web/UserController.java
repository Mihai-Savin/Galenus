package ro.sci.gms.web;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;
import ro.sci.gms.service.DoctorService;
import ro.sci.gms.service.PatientService;
import ro.sci.gms.service.UserService;
import ro.sci.gms.service.ValidationException;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private Patient loggedPatient;
	@Autowired
	private User loggedUser;

	@RequestMapping("")
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("user");
		modelAndView.addObject("user", loggedPatient);

		return modelAndView;
	}

	@RequestMapping("/patient/profile")
	public ModelAndView editPatient() {

		User patient = userService.get(loggedPatient.getId());
		
		ModelAndView modelAndView = new ModelAndView("patientedit");
		modelAndView.addObject("patient", patient);

		return modelAndView;
	}

	@RequestMapping("/patient")
	public String indexPatient() {
		return "user";
	}

	@RequestMapping(value="/patient/profile", method = RequestMethod.POST)
	public String save(@ModelAttribute Patient patient) throws ValidationException, SQLException {
		patient.setId(loggedPatient.getId());
		patient.setRole(Role.user);
		userService.save(patient);
		patient.see();
		return "success";
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=edit")
	public String edit(@RequestParam("id") Long id) {
		return "appointment_edit";
	}
}
