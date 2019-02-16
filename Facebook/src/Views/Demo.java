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
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani15@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani52@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani53@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani54@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani55@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani56@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani57@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani57@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani58@abv.bg", "aad1213AAA"));
		
		FacebookSystem.getFacebookSystem().register(new User("Ani vladimirova", "ani5@abv.bg", "aad1213AAA"));
		
		User zlati = new User("Zlati", "zlati@abv.bg", "Zmihaylova97");
		FacebookSystem.getFacebookSystem().register(zlati);
	
	
	
	
	
	}
}