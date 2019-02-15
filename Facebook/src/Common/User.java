package Common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User extends Profile implements Postable{
	private enum GenderType{MALE, FEMALE};
	
	
	
	private String email;
	private String password;
	private boolean isLoggedIn;
	private GenderType gender;
	
	private Set<User> friends;
	private Set<Profile> friendRequest;

	public User(String name, String email, String password) throws Exception {
		super(name);
		this.email = email;
		this.password = password;
//		this.setGender(gender);
		
		this.friends = Collections.synchronizedSet(new HashSet<User>());
		this.friendRequest = new HashSet<Profile>();
	}
	
	
	
	public void sendFriendRequest(User user) {
		if ( user != null) {
			user.addFriendRequestToTheList(this);
		}
	}
	
	public void addFriendRequestToTheList(Profile profile) {
		if ( profile != null) {
			this.friendRequest.add(profile);
		}
	}
	
	public void AcceptFriendRequest(Profile profile) {
		if ( profile != null) {
//			this.friends.add(profile);
		}
		
	}
	
	
	private void setGender(GenderType gender) throws Exception {
		if(gender == null) {
			throw new Exception("Invalid gender!");
		}
		this.gender = gender;
	}

	public void logIn() {
		this.isLoggedIn = true;
	}
	
	public void logOut() {
		this.isLoggedIn = false;
	}
	
	public String getEmail() {
		return email;
	}

	String getPassword() {
		return password;
	}
	
	@Override
	public int hashCode() {
		return this.email.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.email.equals(((User) obj).email);
	}

	@Override
	public String toString() {
		return "User: " + this.getName();
	}

	private void sharePostWithFriends(Post post) throws Exception {
		for(User friend : this.friends) {
			friend.addPostToNewsFeed(post);
		}
	}

	@Override
	public void uploadNewPicture(String description, String picturePath) throws Exception {
		if(description == null) {
			throw new Exception("Invalid photo description!");
		}
		Photo photo = new Photo(PictureUploader.getInstanceOfPictureDownloader().upload(picturePath, this.email));
		Post post = new Post(description, this, photo);
		this.addNewPhoto(photo);
		this.addNewPostToProfile(post);
		this.addPostToNewsFeed(post);
		this.sharePostWithFriends(post);
	}



	@Override
	public void writeNewStatus(String content) throws Exception {
		if(content == null) {
			throw new Exception("Content of status can't be null");
		}
		Post post = new Post(content, this);
		this.addNewPostToProfile(post);
		this.addPostToNewsFeed(post);
		this.sharePostWithFriends(post);
	}

	

	
}