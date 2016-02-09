package ro.sci.gms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.Discussion;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.User;
import ro.sci.gms.service.DiscussionService;


@Controller
@RequestMapping("/discussions")
public class DiscussionController {
	
	@Autowired
	private Patient loggedPatient;
	@Autowired
	private Doctor loggedDoctor;
	@Autowired
	private DiscussionService discussionService;
	
	private User getLoggedInUser(String userType) {
		User loggedInUser;
		if (userType.equals("doctor")) {
			loggedInUser = loggedDoctor;
		} else {
			loggedInUser = loggedPatient;
		}
		return loggedInUser;
	}
	
	@RequestMapping(value="/template", method=RequestMethod.GET)
	public ModelAndView getDiscussionsTemplate(@RequestParam(value = "userType", required = true) String userType) {
		User user = getLoggedInUser(userType);	
		long userId = user.getId();
		Discussion discussion = discussionService.getDiscussionForUser(userId);
		ModelAndView modelAndView = new ModelAndView("discussions"); //discussions.html
		modelAndView.addObject("discussion", discussion);
		modelAndView.addObject("loggedUserId", user.getId());
		modelAndView.addObject("receiverId", discussionService.getMessageReceiverForUser(userId).getId());
		modelAndView.addObject("searchToken", "");
		return modelAndView;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView filterDiscussions(
			@RequestParam(value = "userId", required = true) long userId, 
			@RequestParam(value = "token", required = false) String token) {		
		Discussion discussion = discussionService.getFilteredDiscussionForUser(userId, token);
		ModelAndView modelAndView = new ModelAndView("discussions"); //discussions.html
		modelAndView.addObject("discussion", discussion);
		modelAndView.addObject("loggedUserId", userId);
		modelAndView.addObject("receiverId", discussionService.getMessageReceiverForUser(userId).getId());
		modelAndView.addObject("searchToken", token);
		return modelAndView;
	}
	
	@RequestMapping(value="/message", method=RequestMethod.POST)
	public ModelAndView addMessage(
			@RequestParam(value = "userId", required = true) long userId, 
			@RequestParam(value = "receiverId", required = true) long receiverId, 
			@RequestParam(value = "content", required = true) String content) {	
		discussionService.addMessage(userId, receiverId, content);
		Discussion discussion = discussionService.getDiscussionForUser(userId);
		ModelAndView modelAndView = new ModelAndView("discussions"); //discussions.html
		modelAndView.addObject("discussion", discussion);
		modelAndView.addObject("loggedUserId", userId);
		modelAndView.addObject("receiverId", receiverId);
		modelAndView.addObject("searchToken", "");
		return modelAndView;
	}

}
