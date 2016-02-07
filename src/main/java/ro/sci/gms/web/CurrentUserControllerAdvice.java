package ro.sci.gms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ro.sci.gms.domain.User;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

    @ModelAttribute("currentUser")
    public User getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (User) authentication.getPrincipal();
        	//(CurrentUser) authentication.getPrincipal();
    }


}