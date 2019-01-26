import java.util.Map;

public class User {
	
	private Account account;
	private UserInformation information;

	public User() {
		this.account = new Account();
		this.account.setUser(this);
		
		System.out.println("Now you have to enter some information about yourself.");
		this.information = new UserInformation();
		
	}

	

}
