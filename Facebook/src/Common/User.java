package Common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User extends Profile{
	private enum GenderType{MALE, FEMALE};
	
	
	
	private String email;
	private String password;
	private boolean isLoggedIn;
	private GenderType gender;
	
	private Set<Profile> friends;
	private Set<Profile> friendRequest;

	public User(String name, String email, String password) throws Exception {
		super(name);
		this.email = email;
		this.password = password;
		this.setGender(gender);
		
		this.friends = Collections.synchronizedSet(new HashSet<Profile>());
		this.friendRequest = new HashSet<Profile>();
	}
	
	
	
	public void sendFriendRequest(User user) {
		if ( user != null) {
			user.addFriendRequestToTheList(this);
		}
	}
	
	public void addFriendRequestToTheList(Profile profile) {
		if ( profile != null) {
			this.friendRequest.add(profile);
		}
	}
	
	public void AcceptFriendRequest(Profile profile) {
		if ( profile != null) {
			this.friends.add(profile);
		}
		
	}
	
	
	private void setGender(GenderType gender) throws Exception {
		if(gender == null) {
			throw new Exception("Invalid gender!");
		}
		this.gender = gender;
	}

	public void logIn() {
		this.isLoggedIn = true;
	}
	
	public void logOut() {
		this.isLoggedIn = false;
	}
	
	public String getEmail() {
		return email;
	}

	String getPassword() {
		return password;
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
		return "User: " + this.getName();
	}

	

	
}