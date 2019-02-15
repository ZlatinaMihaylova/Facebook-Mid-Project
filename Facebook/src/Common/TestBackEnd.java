package Common;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class TestBackEnd {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String image = "picture.png";
		
//		Post post = new Post("asdfasdfsd", profile, "C:\\Users\\Ivan\\Desktop\\IMG_20190213_103738.jpg");
//		post.addLike(profile);
		
		User asen = new User("Asen", "asen@abv.bg", "123456789");
//		user.uploadNewPicture("asfasf", "C:\\\\Users\\\\Ivan\\\\Desktop\\\\IMG_20190213_103738.jpg");
		
		User georgi = new User("Georgi", "georgi@abv.bg", "64564564");
		
		georgi.sendMessage(asen, "zdravei");
		asen.sendMessage(georgi, "zdr ");
		georgi.sendMessage(asen, "..");
		georgi.sendMessage(asen, "..?");
		asen.sendMessage(georgi, "6545614");
		georgi.printChat(asen);
	}

}
