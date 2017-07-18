package main.objects;

public class UserObject {

	public String email;
	public String password;

	public UserObject withEmail(String email) {
		this.email = email;
		return this;
	}

	public UserObject withPassword(String password) {
		this.password = password;
		return this;
	}
}