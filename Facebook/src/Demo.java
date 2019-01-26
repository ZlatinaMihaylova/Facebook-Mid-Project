
public class Demo {

	public static void main(String[] args) {
		
		FacebookSystem db = FacebookSystem.getDataBase();
		db.signUp();
		db.logIn();
		
		
		FacebookSystem.getDataBase().showAllAccounts();
		
	}
}
