package Common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Page extends Profile implements Postable, Likeable{

	enum PageCategory{HOBBY, MEDIA, CELEBRITY, BUSINESS, PLACE, OTHER};
	
	private transient Set<Profile> followers;
	private transient User owner;
	private PageCategory category;
	
	
	public Page(String name, User owner, PageCategory category) throws Exception {
		super(name);
		this.followers = Collections.synchronizedSet(new HashSet<Profile>());
		this.setOwner(owner);
		this.setCategory(category);
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
		Photo photo = new Photo(FileHelper.getInstance().uploadPicture(picturePath, this.owner.getEmail()));
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
			profile.addLikedPage(this);
	}

	private void setOwner(User owner) throws Exception {
		if(owner == null) {
			throw new Exception("Owner of the page can't be null!");
		}
		this.owner = owner;
	}

	private void setCategory(PageCategory category) throws Exception {
		if(category == null) {
			throw new Exception("Category of the page can't be null!");
		}
		this.category = category;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Page) {
			return ((Page)obj).owner.equals(this.owner) && ((Page)obj).category.equals(this.category) && ((Page)obj).getName().equals(this.getName());
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.category.hashCode()*this.owner.hashCode()*this.getName().hashCode();
	}

}
