package ro.sci.gms.web;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ro.sci.gms.configuration.SecurityContextFacade;

public class SecurityContextHolderFacade implements SecurityContextFacade {

	public SecurityContext getContext() {
		return SecurityContextHolder.getContext();
	}

	public void setContext(SecurityContext securityContext) {
		SecurityContextHolder.setContext(securityContext);
	}

}