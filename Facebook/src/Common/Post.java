package Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post extends TextContent implements Likeable{

	
	
	private class Comment extends TextContent implements Likeable{

		private Post post;
		private Set<Profile> likes;
//		private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		private Comment(String content, Profile author, Post post) throws Exception {
			super(content, author);
			this.setPost(post);
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
		

		private void setPost(Post post) throws Exception {
			if(post == null) {
				throw new Exception("You can't add comment to a null post!");
			}
			this.post = post;
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
	 
	 void writeComment(String content, Profile author) throws Exception {  //add new comment to post
		 this.comments.add(new Comment(content, author, this));
	 }
	 
	 void deleteComment(Comment comment) throws Exception {  // delete comment from post if existing
		 if(this.comments.contains(comment)) {
			 comments.remove(comment);
		 } else {
			 throw new Exception("Comment could not be found int this post!");
		 }
	 }
	 
	 @Override
	public void addLike(Profile profile) throws Exception {		//add like to the post
		 if(profile == null) {
				throw new Exception("Profile that liked the post is null!");
			}
			this.likes.add(profile);
	}

	private void setPhoto(String photo) throws Exception {
		if(photo == null) {
			throw new Exception("Invalid photo!");
		}
		this.photo = photo;
	}

	
	 
	 
	 
}
