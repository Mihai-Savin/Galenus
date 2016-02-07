package ro.sci.gms.domain;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {
	private String role;
	
	public GrantedAuthorityImpl(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}

}
