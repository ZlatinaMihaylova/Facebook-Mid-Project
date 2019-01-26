import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class FacebookSystem {
	
	private static FacebookSystem facebookSystem = null;
	private Set<User> users;
	
	private FacebookSystem() {
		this.users = new HashSet<User>();
	}
	
	public static FacebookSystem getDataBase() {
		if ( FacebookSystem.facebookSystem == null ) {
			FacebookSystem.facebookSystem = new FacebookSystem();
			
		}
		return FacebookSystem.facebookSystem;
	}
/*	
	public static DataBase addToDataBase(User user) {
		DataBase.getDataBase().users.add(user);
		return DataBase.dataBase;
	}
*/
	void showAllAccounts() {
		for (User a : users) {
			System.out.println(a.toString());
		}
	}
	
	User logIn() {
		System.out.println("Log in:");
		
		System.out.println("Email: ");
		String email = new Scanner(System.in).nextLine();
		
		System.out.println("Password: ");
		String password = new Scanner(System.in).nextLine();
		
		for (User user : users) {
			if ( user.getEmail().equals(email)) {
				while ( !user.getPassword().equals(password)) {
					System.out.println("Wrong password! Try again");
					password = new Scanner(System.in).nextLine();
				}
				return user;
			}
		}
		System.out.println("No user exists with such e-mail");
		return null;
	}
	
	User signUp() {
		System.out.println("Sign up:");
		
		User newUser = new User();
		this.users.add(newUser);
		return newUser;
	}
}
