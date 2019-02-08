package Common;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Profile {
	
	private String highSchool;
	private String university;
	private String employer;
	private String currentCity;
	private String hometown;
	
	private User user;
	public void setUser(User user) {
		this.user = user;
	}

	private String name;
	private Set<Profile> friends;
	private Set<Profile> friendRequest;
//	private Set<Photo> photos;
	private Set<Post> posts;
	private Set<Chat> chats;

	public Profile(String name) {
		
		this.name = name;
		
		this.highSchool = "";
		this.university = "";
		this.employer = "";
		this.currentCity = "";
		this.hometown = "";
		
		this.friends = Collections.synchronizedSet(new HashSet<Profile>());
		this.friendRequest = new HashSet<Profile>();
		this.chats = Collections.synchronizedSet(new TreeSet<Chat>((Chat chat1,Chat chat2) -> chat2.getLastUpdate().compareTo(chat1.getLastUpdate())));
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
	
	public Chat startChat(Profile profile) throws Exception {   //find already existing chat or create a new one
		for(Chat chat : this.chats) {
			if(chat.hasParticipant(profile)) {
				return chat;
			}
		}
		Chat chat = new Chat(this, profile);
		this.chats.add(chat);
		profile.chats.add(chat);
		return chat;
	}
	
	public void updateInformation(String highSchool, String university, String employer, String currentCity, String hometown) {
		this.setHighSchool(highSchool);
		this.setUniversity(university);
		this.setEmployer(employer);
		this.setCurrentCity(currentCity);
		this.setHometown(hometown);
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

	public void setHighSchool(String highSchool) {
		if (highSchool != null ) {
			this.highSchool = highSchool;
		}
		
	}

	public void setUniversity(String university) {
		if (university != null ) {
			this.university = university;
		}
	}

	public void setEmployer(String employer) {
		if (employer != null ) {
			this.employer = employer;
		}
	}

	public void setCurrentCity(String currentCity) {
		if (currentCity != null ) {
			this.currentCity = currentCity;
		}
	}

	public void setHometown(String hometown) {
		if (hometown != null ) {
			this.hometown = hometown;
		}
	}

	@Override
	public String toString() {
		return this.name ;
	}
	

}