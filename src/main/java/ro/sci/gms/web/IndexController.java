package ro.sci.gms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.sci.gms.domain.Patient;
import ro.sci.gms.service.UserService;
import ro.sci.gms.service.ValidationException;
import ro.sci.gms.temp.Li;

@Controller
public class IndexController {

	@Autowired
	UserService userService;
	
	@Autowired
	Patient loggedPatient;
	
	
	@RequestMapping("/")
	public String index() throws ValidationException {
		Li.st("I am in index.");
		userService.save(loggedPatient);
		userService.get(loggedPatient.getId());
		return "index";
	}



}
