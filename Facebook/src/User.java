import java.util.Scanner;

public class User {

	private String name;
	private String email;
	private String password;
	
	private Profile profile;
	
	
	public User(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.password = password;
		
		this.profile = new Profile(name);

		
	}

	@Override
	public int hashCode() {
		return this.email.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.email.equals(((User) obj).email);
	}

	@Override
	public String toString() {
		return "User: " + this.name;
	}

	public String getEmail() {
		return email;
	}

	String getPassword() {
		return password;
	}

	public Profile getProfile() {
		return profile;
	}


	
}
