package Common;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class TextContent {

	private Profile author;
	private String content;
	private LocalDateTime time;
	
	
	TextContent (String content, Profile author) throws Exception {
		this.setContent(content);
		this.setAuthor(author);
		this.time = LocalDateTime.now();
	}
	
	
	
	
	private void setAuthor(Profile author) throws Exception {
		if(author == null) {
			throw new Exception("Author of comment is null!");
		}
		this.author = author;
	}

	private void setContent(String content) throws Exception {
		if(content == null || content.trim().length() == 0) {
			throw new Exception("Invalid content of comment!");
		}
		this.content = content;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TextContent) {
			return this.author.equals(((TextContent)obj).author) && this.content.equals(((TextContent)obj).content) && this.time.equals(((TextContent)obj).time);
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.author.hashCode()*this.content.hashCode()*this.time.hashCode();
	}
	
	
}
