package Views;
import javax.swing.plaf.synth.SynthSpinnerUI;

import Common.FacebookSystem;
import Common.Post;
import Common.User;

public class Demo {

	public static void main(String[] args) throws Exception {

		LoginSystemWindow login = new LoginSystemWindow();
		LoginSystemWindow.main(null);

		FacebookSystem.getFacebookSystem().register(new User("Georgi Ivanov", "gosho@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Dimitur Georgiev", "mitko@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Aleksandur Kolev", "aleks2@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Hristo Martinov", "hristo@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Yordan Vladimirov", "dancho4@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Krasimir Ivanov", "krasi@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Plamen Dimitrov", "plamen@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Ani Angelova", "ani@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Mariq Angelova", "mimi@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Elena Ivanova", "eli@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Emiliq Kostadinova", "emi56@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Milena Georgieva", "mili@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Katq Dimitrova", "kati@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Margarita Pencheva", "magi58@abv.bg", "aad1213AAA"));
		FacebookSystem.getFacebookSystem().register(new User("Silviq Gocheva", "sisi@abv.bg", "aad1213AAA"));


		User zlati = new User("Zlati", "zlati@abv.bg", "Zmihaylova97");
		User zlati2 = new User("Zlati2222", "zlati2@abv.bg", "Zmihaylova97");
		FacebookSystem.getFacebookSystem().register(zlati);
		/*
		Post post = new Post("az sym zlati", zlati);
		zlati.addNewPostToProfile(post);
		post.addLike(zlati2);
		post.writeComment("zlati 2 komentira", zlati2);
		
		zlati.showNotifications();
		System.out.println("nishto");
		zlati.showNotifications();
		
		
		FacebookSystem.getFacebookSystem().register(zlati);
	
	*/
	
	
	
	}
}