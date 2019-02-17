 package Common;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import sun.java2d.cmm.ProfileDeferralMgr;


public abstract class Profile {
	
	private String name;
	private String highSchool;
	private String university;
	private String employer;
	private String currentCity;
	private String birthPlace;
	private Photo profilePicture;
	private transient NewsFeed newsFeed;
	
	private Set<Photo> photos;
<<<<<<< HEAD
	private transient Set<Post> posts;
	private transient Set<Chat> chats;
	private transient Set<Page> likedPages;
=======
	private Set<Post> posts;
	private Set<Chat> chats;
	private Set<Page> likedPages;
	private Set<Notification> notifications;
>>>>>>> 0932678ce6c41ff243edfbd73fcda261ba78bcba
	
	private transient Chat lastChat;
	
	//Comparators
<<<<<<< HEAD
	private transient Comparator<Chat> chatComparator = (chat1, chat2) -> chat2.getLastUpdate().compareTo(chat1.getLastUpdate());
	private transient Comparator<Post> postComparator = (post1, post2) -> post2.getTime().compareTo(post1.getTime());
	private transient Comparator<Photo> photoComparator = (photo1, photo2) ->photo2.getTime().compareTo(photo1.getTime());
=======
	Comparator<Chat> chatComparator = (chat1, chat2) -> chat2.getLastUpdate().compareTo(chat1.getLastUpdate());
	Comparator<Post> postComparator = (post1, post2) -> post2.getTime().compareTo(post1.getTime());
	Comparator<Photo> photoComparator = (photo1, photo2) ->photo2.getTime().compareTo(photo1.getTime());
	Comparator<Notification> notificationComparator = (notification1, notification2) ->notification2.getTime().compareTo(notification1.getTime());
>>>>>>> 0932678ce6c41ff243edfbd73fcda261ba78bcba
	
	public Profile(String name) throws Exception {
		this.setName(name);
		this.highSchool = "";
		this.university = "";
		this.employer = "";
		this.currentCity = "";
		this.birthPlace = "";
		this.newsFeed = new NewsFeed();
		
		this.chats = Collections.synchronizedSet(new TreeSet<Chat>(chatComparator));
		this.posts = new TreeSet<Post>(postComparator);			//postove i snimki mogat da se dobavqt samo ot 1 nishka
		this.photos = new TreeSet<Photo>(photoComparator);
		this.notifications = new TreeSet<Notification>(notificationComparator);
		this.likedPages = new HashSet<Page>();
	}
	
	public Chat getLastChat() {
		return lastChat;
	}
	
	public HashSet<Profile> searchForProfile(String name) {
		HashSet<Profile> results = FacebookSystem.getFacebookSystem().searchByName(name);
		return results;
	}
	
	public void sendMessage( String content,Profile profile) throws Exception {
		Chat chat = this.findChat(profile);
		chat.sendMessage(content);
		profile.receiveMessage(content, this);
	}
	
	private void receiveMessage(String content,Profile profile) throws Exception {
		Chat chat = this.findChat(profile);
		chat.receiveMessage(content);;
	}
	
	public void printChat(Profile profile) throws Exception {
		System.out.println("---------------Chat with "+profile.getName()+"---------------");
		this.findChat(profile).printChat();
	}
	
	public Chat findChat(Profile profile) throws Exception {   //find already existing chat or create a new one
		for(Chat chat : this.chats) {
			if(chat.hasParticipant(profile)) {
				return chat;
			}
		}
		
		Chat chat = new Chat(profile, this);
		profile.chats.add(chat);
		profile.setLastChat(chat);
		chat = new Chat(this, profile);
		this.chats.add(chat);
		this.setLastChat(chat);
		return chat;
	}
	
	public void updateInformation(String highSchool, String university, String employer, String currentCity, String hometown) throws Exception {
		this.setHighSchool(highSchool);
		this.setUniversity(university);
		this.setEmployer(employer);
		this.setCurrentCity(currentCity);
		this.setBirthPlace(hometown);
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
	public String getBirthPlace() {
		return birthPlace;
	}
	private void setHighSchool(String highSchool) throws Exception {
		if(highSchool == null || highSchool.trim().length() == 0) {
			throw new Exception("Invalid high school entered!");
		}
		this.highSchool = highSchool;
	}
	private void setUniversity(String university) throws Exception {
		if(university == null || university.trim().length() == 0) {
			throw new Exception("Invalid university entered!");
		}
		this.university = university;
	}
	private void setEmployer(String employer) throws Exception {
		if(employer == null || employer.trim().length() == 0) {
			throw new Exception("Invalid employer entered!");
		}
		this.employer = employer;
	}
	private void setCurrentCity(String currentCity) throws Exception {
		if(currentCity == null || currentCity.trim().length() == 0) {
			throw new Exception("Invalid city entered!");
		}
		this.currentCity = currentCity;
	}
	private void setBirthPlace(String birthPlace) throws Exception {
		if(birthPlace == null || birthPlace.trim().length() == 0) {
			throw new Exception("Invalid birth place entered!");
		}
		this.birthPlace = birthPlace;
	}
	private void setName(String name) throws Exception {
		if(name == null || name.trim().length() == 0) {
			throw new Exception("Invalid name entered!");
		}
		this.name = name;
	}
	
	public void addNewPhoto(Photo photo) throws Exception {
		if(photo == null) {
			throw new Exception("Photo can't be null!");
		}
		this.photos.add(photo);
	}
	public void addNewPostToProfile(Post post) throws Exception {
		if(post == null) {
			throw new Exception("Post can't be null!");
		}
		this.posts.add(post);
	}
	public void addPostToNewsFeed(Post post) throws Exception {
		this.newsFeed.addPost(post);
	}
	public void setProfilePicture(Photo photo) throws Exception {
		if(!this.photos.contains(photo)) {
			throw new Exception("The photo should be uploaded by you in order to be your profile picture!");
		}
		this.profilePicture = photo;
	}
	public void addLikedPage(Page page) {
		this.likedPages.add(page);
	}
	
	public void addNotification(Notification notification) {
		this.notifications.add(notification);
	}
	
	public void showNotifications() {
		if ( this.notifications.isEmpty()) {
			System.out.println("You currently have no new notifications");
		}
		else {
			for ( Iterator<Notification> it = this.notifications.iterator(); it.hasNext(); ) {
				Notification notification = it.next();
				System.out.println(notification.showNotification());
				it.remove();
			}
		}
	}
	
	@Override
	public String toString() {
		return this.name ;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public NewsFeed getNewsFeed() {
		return newsFeed;
	}

	public Set<Chat> getChats() {
		return chats;
	}

	public void setLastChat(Chat lastChat) {
		this.lastChat = lastChat;
	}
	
}