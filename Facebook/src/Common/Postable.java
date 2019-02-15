package Common;

public interface Postable {

	
	void writeNewStatus(String content) throws Exception;
	void uploadNewPicture(String description, String picturePath) throws Exception;
	
	
}
