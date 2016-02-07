package ro.sci.gms.web;

import java.security.Principal;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.Employee;
import ro.sci.gms.domain.User;
import ro.sci.gms.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
//	private final SecurityContextFacade securityContextFacade;
//	@Autowired
	private User currentUser;
//    private IAuthenticationFacade authenticationFacade;
//	private Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);
	
//	public EmployeeController(SecurityContextFacade securityContextFacade) {
//		this.securityContextFacade = securityContextFacade;
//	}
	
	@RequestMapping("")
	public ModelAndView index(Principal principal, @AuthenticationPrincipal User testUser) {
		
		Collection<Employee> allEmployees = employeeService.listAll();
//		Authentication authentication = authenticationFacade.getAuthentication();
//		User currentUser = (User) authentication.getPrincipal();
/*		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User testUser = (User)principal;
		for(GrantedAuthority ga:currentUser.getAuthorities()){
			LOGGER.debug(ga.getAuthority().toString());
		}
		for(GrantedAuthority ga:testUser.getAuthorities()){
			LOGGER.debug(ga.getAuthority().toString());
		}
*/	//	LOGGER.debug("Current user's name:" + user.getUsername());
		LOGGER.debug("Current user's name:" + testUser.getUsername());
		

		
		
		
		ModelAndView modelAndView  = new ModelAndView("employee_list");
		modelAndView.addObject("allEmployees", allEmployees);
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=add")
	public String add() {
		return "employee_edit";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, params = "action=edit")
	public String edit(@RequestParam("id") Long id) {
		return "employee_edit";
	}
}
