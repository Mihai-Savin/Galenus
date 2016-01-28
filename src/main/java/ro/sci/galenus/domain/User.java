package ro.sci.galenus.domain;

public class User extends AbstractModel {
	private String userName;
	private String hashedPassword;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String email;
	private boolean isVerified;
	private Role role;

}
