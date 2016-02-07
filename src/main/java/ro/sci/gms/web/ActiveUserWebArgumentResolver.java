package ro.sci.gms.web;

import java.lang.annotation.Annotation;
import java.security.Principal;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import ro.sci.gms.domain.User;

public class ActiveUserWebArgumentResolver implements WebArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		 Annotation[] annotations = methodParameter.getParameterAnnotations();

	        if(methodParameter.getParameterType().equals(User.class))
	        {
	            for(Annotation annotation : annotations)
	            {
	                if(ActiveUser.class.isInstance(annotation))
	                {
	                    Principal principal = webRequest.getUserPrincipal();
	                    return (User)((Authentication) principal).getPrincipal();
	                }
	            }
	        }

	        return WebArgumentResolver.UNRESOLVED;
	    }
	}