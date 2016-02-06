package ro.sci.gms.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.User;
import ro.sci.gms.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private Patient loggedPatient;

	@RequestMapping("")
	public ModelAndView index() {

		Collection<User> allUsers = userService.getAll();

		ModelAndView modelAndView = new ModelAndView("user_list");
		modelAndView.addObject("allUsers", allUsers);

		return modelAndView;
	}

	@RequestMapping("/patient/profile")
	public ModelAndView editPatient() {

		ModelAndView modelAndView = new ModelAndView("patient_edit");
		modelAndView.addObject("patient", loggedPatient);

		return modelAndView;
	}

	@RequestMapping("/patient")
	public String indexPatient() {
		return "index_patient";
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
