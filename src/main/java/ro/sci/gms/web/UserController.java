package ro.sci.gms.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.User;
import ro.sci.gms.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public ModelAndView index() {
		
//		try {
//			userService.generateSome();
//		} catch (ValidationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Collection<User> allUsers = userService.getAll();
		
		ModelAndView modelAndView  = new ModelAndView("user_list");
		modelAndView.addObject("allUsers", allUsers);
		
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
