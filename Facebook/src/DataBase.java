import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DataBase {
	
	private static DataBase dataBase = null;
	private Set<Account> accounts;
	
	private DataBase() {
		this.accounts = new HashSet<Account>();
	}
	
	public static DataBase getDataBase() {
		if ( DataBase.dataBase == null ) {
			DataBase.dataBase = new DataBase();
			
		}
		return DataBase.dataBase;
	}
	
	public static DataBase addToDataBase(Account account) {
		DataBase.getDataBase().accounts.add(account);
		return DataBase.dataBase;
	}
	
	void showAllAccounts() {
		for (Account a : accounts) {
			System.out.println(a.toString());
		}
	}
	
	User logIn(String email, String password) {
		for (Account a : accounts) {
			if ( a.getEmail().equals(email)) {
				while ( !a.getPassword().equals(password)) {
					System.out.println("Wrong password! Try again");
					password = new Scanner(System.in).nextLine();
				}
				return a.getUser();
			}
		}
		System.out.println("No user exists with such e-mail");
		return null;
	}
}
