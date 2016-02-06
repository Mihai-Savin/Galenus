package ro.sci.gms.domain;

public enum Role {
	user, admin;
	
	public String toString() {
		if (this.equals(user)) {
			return "user";
		} else {
			return "admin";
		}
	}
}
