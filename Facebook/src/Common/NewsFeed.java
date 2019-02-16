package Common;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class NewsFeed {

	private Set<Post> posts;
	Comparator<Post> postComparator = (post1, post2) -> post2.getTime().compareTo(post1.getTime());

	public NewsFeed() {
		this.posts = Collections.synchronizedSet(new TreeSet<Post>(postComparator));
	}
	
	public void addPost(Post post) throws Exception {
		if(post == null) {
			throw new Exception("Null post can't be added to News Feed!");
		}
		this.posts.add(post);
	}
	 
	public void addPosts(Set<Post> posts) throws Exception{
		for ( Post post: posts) {
			this.addPost(post);
		}
	}

	public Set<Post> getPosts() {
		return posts;
	}

}
