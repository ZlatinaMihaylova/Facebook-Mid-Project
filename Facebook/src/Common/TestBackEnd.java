package Common;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class TestBackEnd {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String image = "picture.png";
		Profile profile = new Profile("sadasd");
		
		Post post = new Post("asdfasdfsd", profile, "C:\\Users\\Ivan\\Desktop\\IMG_20190213_103738.jpg");
		post.addLike(profile);
		
	}

}
