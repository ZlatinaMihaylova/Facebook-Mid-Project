import java.util.Scanner;

public class User {

	private String name;
	private String email;
	private String password;
	
	private FacebookSystem facebookSystem;
	private Profile profile;
	
	
	public User() {
		System.out.println("Name: ");
		String name = new Scanner(System.in).nextLine();
		
		System.out.println("E-mail: ");
		String email = new Scanner(System.in).nextLine();
		while (!User.verifyEmail(email)) {
			System.out.println("Your email address is invalid. Please enter a valid address: ");
			email = new Scanner(System.in).nextLine();
		}
		
		System.out.println("Password: ");
		String password = new Scanner(System.in).nextLine();
		while (!User.verifyPassword(password)) {
			System.out.println("Your password must must be at least 5 characters long and have at least one lowercase letter, one capital letter and one number! Enter valid password:");
			password = new Scanner(System.in).nextLine();
		}

		this.name = name;
		this.email = email;
		this.password = password;
		
		this.profile = new Profile();
		
		System.out.println("Welcome to Facebook! ");
	}
	
	private static boolean verifyEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	private static boolean verifyPassword(String password) {
		boolean isLower = false;
		boolean isUpper = false;
		boolean isDigit = false;
		
		if ( password.length() < 5) {
			return false;
		}
		
		for ( int index = 0; index < password.length(); index++) {
			if (Character.isLowerCase(password.charAt(index))) {
				isLower = true;
			}
			if (Character.isUpperCase(password.charAt(index))) {
				isUpper = true;
			}
			
			if (Character.isDigit(password.charAt(index))) {
				isDigit = true;
			}
		}
		if (isLower && isUpper && isDigit ) {
			return true;
		}
		
		return false;
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

	public String getPassword() {
		return password;
	}


	
}
