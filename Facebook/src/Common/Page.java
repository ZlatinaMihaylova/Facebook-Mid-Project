package Common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Page extends Profile implements Postable, Likeable{

	
	private Set<Profile> followers;
	private User owner;
	
	
	public Page(String name, User owner) throws Exception {
		super(name);
		this.followers = Collections.synchronizedSet(new HashSet<Profile>());
	}
	
	private void shareWithFollowers(Post post) throws Exception {
		for(Profile follower : this.followers) {
			follower.addPostToNewsFeed(post);
		}
	}
	

	@Override
	public void writeNewStatus(String content) throws Exception {
		if(content == null) {
			throw new Exception("Content of status can't be null");
		}
		Post post = new Post(content, this);
		this.addNewPostToProfile(post);
		this.addPostToNewsFeed(post);
		this.shareWithFollowers(post);
	}

	@Override
	public void uploadNewPicture(String description, String picturePath) throws Exception {
		if(description == null) {
			throw new Exception("Invalid photo description!");
		}
		Photo photo = new Photo(PictureUploader.getInstanceOfPictureDownloader().upload(picturePath, this.owner.getEmail()));
		Post post = new Post(description, this, photo);
		this.addNewPhoto(photo);
		this.addNewPostToProfile(post);
		this.addPostToNewsFeed(post);
		this.shareWithFollowers(post);
		
	}

	@Override
	public void addLike(Profile profile) throws Exception {
		 if(profile == null) {
				throw new Exception("Profile that liked the page is null!");
			}
			this.followers.add(profile);
	}

}
