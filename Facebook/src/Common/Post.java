package Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post extends TextContent{

	
	
	private class Comment extends TextContent implements Likeable{

		
		private Set<Profile> likes;
//		private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		private Comment(String content, Profile author) throws Exception {
			super(content, author);
			Set<Profile> list = new HashSet<Profile>();
			this.likes = Collections.synchronizedSet(list);
		}

		@Override
		public void addLike(Profile profile) throws Exception {
			if(profile == null) {
				throw new Exception("Profile that liked the comment is null!");
			}
			this.likes.add(profile);
		}

	}
	
	
	private List<Comment> comments;
	private Set<Profile> likes;
	private String photo;
	
	 Post(String content, Profile author) throws Exception {
		super(content, author);
		Set<Profile> listOfLikes = new HashSet<Profile>();
		this.likes = Collections.synchronizedSet(listOfLikes);
		
		List<Comment> listOfComments = new ArrayList<Comment>();
		this.comments = Collections.synchronizedList(listOfComments);
	}
	 
	 Post(String content, Profile author, String photo) throws Exception {
		 this(content, author);
		 this.setPhoto(photo);
	 }

	private void setPhoto(String photo) throws Exception {
		if(photo == null) {
			throw new Exception("Invalid photo!");
		}
		this.photo = photo;
	}
	 
	 
	 
}
