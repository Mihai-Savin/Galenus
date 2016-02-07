package ro.sci.galenus.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.sci.gms.domain.User;
import ro.sci.gms.service.UserService;
import ro.sci.gms.service.ValidationException;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public void save(User user, BindingResult bindingResult) throws ValidationException {
		LOGGER.debug("Saving: " + user);
		try {
			userService.save(user);
		} catch (Exception e) {			
//			result = renderEditPage(student.getId());	
			bindingResult.addError(new ObjectError("user",e.getMessage()));
			
		}
	}


}
