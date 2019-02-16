package Common;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class FacebookSystem {
	
	private static FacebookSystem facebookSystem = null;
	private Set<User> users;
	private Set<Page> pages;
	
	
	private FacebookSystem() {
		this.users = Collections.synchronizedSet(new HashSet<User>());
		this.pages = Collections.synchronizedSet(new HashSet<Page>());
	}
	
	public synchronized static FacebookSystem getFacebookSystem() {
		if ( FacebookSystem.facebookSystem == null ) {
			FacebookSystem.facebookSystem = new FacebookSystem();
		}
		return FacebookSystem.facebookSystem;
	}
	
	public void showAllAccounts() {
		for (User a : users) {
			System.out.println(a.toString());
		}
	}
	
	public HashSet<Profile> searchByName(String name) {
		Set<Profile> profiles = new HashSet<Profile>();
		for (User a : this.users) {
			if (a.getName().toLowerCase().contains(name.toLowerCase()) ) {
				profiles.add(a);
			}
		}
		for(Page page : this.pages) {
			if (page.getName().toLowerCase().contains(name.toLowerCase()) ) {
				profiles.add(page);
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