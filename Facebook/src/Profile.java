import java.util.Map;
import java.util.Set;

public class Profile {
	
	
	private String name;
	private UserInformation information;
//	private Set<Photo> photos;
	private Set<Post> posts;
//	private Set<Message> messages;

	public Profile(String name) {
		
		this.information = new UserInformation();
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public UserInformation getInformation() {
		return information;
	}

	

}
