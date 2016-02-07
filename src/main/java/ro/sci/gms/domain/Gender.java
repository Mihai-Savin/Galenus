package ro.sci.gms.domain;

public enum Gender {
	MALE, FEMALE;

	public String toString() {
		if (this.equals(MALE)) {
			return "M";
		} else {
			return "F";
		}
	}

}
