package Common;


public class TestBackEnd {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
//		Post post = new Post("asdfasdfsd", profile, "C:\\Users\\Ivan\\Desktop\\IMG_20190213_103738.jpg");
		
		User asen = new User("Asen", "asen@abv.bg", "123456789");
//		user.uploadNewPicture("asfasf", "C:\\\\Users\\\\Ivan\\\\Desktop\\\\IMG_20190213_103738.jpg");
		
		User georgi = new User("Georgi", "georgi@abv.bg", "64564564");
		
		Advertisement adv = new Advertisement("sdfsdggsd", asen, "C:\\\\Users\\\\Ivan\\\\Desktop\\\\IMG_20190213_103738.jpg");
		adv.start();
		
		FileHelper.getInstance().createJson(asen, "asenJson");
	}

}
