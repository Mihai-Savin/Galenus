package ro.sci.ems.web;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.ems.domain.Employee;
import ro.sci.ems.domain.GrantedAuthorityImpl;
import ro.sci.ems.service.EmployeeService;

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
	public ModelAndView index() {
		
		Collection<Employee> allEmployees = employeeService.listAll();
//		Authentication authentication = authenticationFacade.getAuthentication();
//		User currentUser = (User) authentication.getPrincipal();
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(GrantedAuthority ga:currentUser.getAuthorities()){
			LOGGER.debug(ga.getAuthority().toString());
		}
		
		
		
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
