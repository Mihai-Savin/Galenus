package ro.sci.gms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.sci.gms.service.ValidationException;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() throws ValidationException {
		return "index";
	}
}
