package Common;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Profile {
	
	private String name;
	private String highSchool;
	private String university;
	private String employer;
	private String currentCity;
	private String birthPlace;
	private Photo profilePhoto;
	
	private Set<Photo> photos;
	private Set<Post> posts;
	private Set<Chat> chats;
	
	//Comparators
	Comparator<Chat> chatComparator = (chat1, chat2) -> chat2.getLastUpdate().compareTo(chat1.getLastUpdate());
	Comparator<Post> postComparator = (post1, post2) -> post2.getTime().compareTo(post1.getTime());
	Comparator<Photo> photoComparator = (photo1, photo2) ->photo2.getTime().compareTo(photo1.getTime());
	
	public Profile(String name) throws Exception {
		this.setName(name);
		this.highSchool = "";
		this.university = "";
		this.employer = "";
		this.currentCity = "";
		this.birthPlace = "";
		
		this.chats = Collections.synchronizedSet(new TreeSet<Chat>(chatComparator));
		this.posts = new TreeSet<Post>(postComparator);			//postove i snimki mogat da se dobavqt samo ot 1 nishka
		this.photos = new TreeSet<Photo>(photoComparator);
	}
	
	public HashSet<Profile> searchForProfile(String name) {
		HashSet<Profile> results = FacebookSystem.getFacebookSystem().searchByName(name);
		return results;
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
	public void setProfilePicture(String photoPath) throws Exception {
		if(photoPath == null || !ImageFormatValidator.getInstance().validate(photoPath)) {
			throw new Exception("Invalid photo path!");
		}
		File uploadingPhoto = new File(photoPath);
		File uploadedPhoto = new File("src\\resources\\"+uploadingPhoto.hashCode()+".jpg");
		uploadedPhoto.createNewFile();
		try (InputStream is = new BufferedInputStream(new FileInputStream(uploadingPhoto));
			OutputStream os = new BufferedOutputStream(new FileOutputStream(uploadedPhoto))) {
				int b = is.read();
				while (b != -1) {
					os.write(b);
					b = is.read();
				}
		}
		this.profilePhoto = new Photo(uploadedPhoto);
	}

	@Override
	public String toString() {
		return this.name ;
	}
	

}