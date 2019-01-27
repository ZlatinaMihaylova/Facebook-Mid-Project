import java.util.Scanner;

public class User {

	private String name;
	private String email;
	private String password;
	
	private FacebookSystem facebookSystem;
	private Profile profile;
	
	
	public User(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.password = password;
		
		this.profile = new Profile(name);
		FacebookSystem.getFacebookSystem().addNewUser(this);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + "]";
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
