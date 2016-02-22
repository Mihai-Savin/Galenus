package ro.sci.gms.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.sci.gms.service.ValidationException;
import ro.sci.gms.temp.Li;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() throws ValidationException {
		Li.st("Website accessed @ " + new Date() + ". Index page served to default VIEW.");
		return "index";
	}
}
