package Common;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class FacebookSystem {
	
	private static FacebookSystem facebookSystem = null;
	private Set<User> users;
	
	private FacebookSystem() {
		this.users = new HashSet<User>();
	}
	
	public synchronized static FacebookSystem getFacebookSystem() {
		if ( FacebookSystem.facebookSystem == null ) {
			FacebookSystem.facebookSystem = new FacebookSystem();
		}
		return FacebookSystem.facebookSystem;
	}
	
	void showAllAccounts() {
		for (User a : users) {
			System.out.println(a.toString());
		}
	}
	
	public HashSet<Profile> searchByName(String name) {
		Set<Profile> profiles = new HashSet<Profile>();
		for (User a : users) {
			if (a.getName().toLowerCase().contains(name.toLowerCase()) ) {
				profiles.add(a.getProfile());
			}
			
		}
		return (HashSet<Profile>) profiles;
	}
	
	public void register(User user) {
		this.users.add(user);
	}

	public User logIn(String email, String password) {
		
		for (User user : users) {
			if ( user.getEmail().equals(email) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	public void logOut(User user) {
		user.logOut();
	}
	
	public boolean containsEmail(String email) {
		for (User user : users) {
			if ( user.getEmail().equals(email)) {
				return true;
			}
		} 
		return false;
	}

}