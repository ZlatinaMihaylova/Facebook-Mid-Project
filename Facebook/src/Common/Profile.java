package Common;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Profile {
	
	private String highSchool;
	private String university;
	private String employer;
	private String currentCity;
	private String hometown;
	
	private String name;
	private Set<Profile> friends;
	private Set<Profile> friendRequest;
//	private Set<Photo> photos;
	private Set<Post> posts;
//	private Set<Message> messages;

	public Profile(String name) {
		
		this.name = name;
		
		this.highSchool = "";
		this.university = "";
		this.employer = "";
		this.currentCity = "";
		this.hometown = "";
		
		this.friends = new HashSet<Profile>();
		this.friendRequest = new HashSet<Profile>();
	}
	
	public HashSet<Profile> searchForProfile(String name) {
		HashSet<Profile> results = FacebookSystem.getFacebookSystem().searchByName(name);
		return results;
	}
	
	public void sendFriendRequest(Profile profile) {
		if ( profile != null) {
			profile.addFriendRequestToTheList(this);
		}
	}
	
	public void addFriendRequestToTheList(Profile profile) {
		if ( profile != null) {
			this.friendRequest.add(profile);
		}
	}
	
	public void AcceptFriendRequest(Profile profile) {
		if ( profile != null) {
			this.friends.add(profile);
		}
		
	}
	
	public void updateInformation(String highSchool, String university, String employer, String currentCity, String hometown) {
		this.highSchool = highSchool;
		this.university = university;
		this.employer = employer;
		this.currentCity = currentCity;
		this.hometown = hometown;
	}

	public String getName() {
		return name;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public String getUniversity() {
		return university;
	}

	public String getEmployer() {
		return employer;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public String getHometown() {
		return hometown;
	}
	

}
