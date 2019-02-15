package Views;
import Common.FacebookSystem;
import Common.User;

public class Demo {

	public static void main(String[] args) throws Exception {

		LoginSystemWindow login = new LoginSystemWindow();
		LoginSystemWindow.main(null);

		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani 2", "ani1@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani 3", "ani2@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("gosho 1", "ani3@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani 5", "ani4@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani5@abv.bg", "aad1213AAA"));
		
		FacebookSystem.getFacebookSystem().register(new User("Zlati", "zlati@abv.bg", "Zmihaylova97"));
	}
}