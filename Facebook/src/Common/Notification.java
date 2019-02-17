package Common;

import java.time.LocalDateTime;

public class Notification extends TextContent {
	
	public static class postLikeNotification extends Notification{

		public postLikeNotification(Profile notificationCauser,TextContent source) throws Exception {
			super(notificationCauser, " liked your post!",source);
		}
	}
	
	public static class postCommentNotification extends Notification{

		public postCommentNotification(Profile notificationCauser,TextContent source) throws Exception {
			super(notificationCauser, " commented on your post!",source);
		}
	}

	public static class commentLikeNotification extends Notification{

		public commentLikeNotification(Profile notificationCauser,TextContent source) throws Exception {
			super(notificationCauser, " liked your comment!",source);
		}
	}
	
	private TextContent source;
	private LocalDateTime time;

	private Notification(Profile notificationCauser, String notification,TextContent source) throws Exception {
		super(notification,notificationCauser);
		this.source = source;
		this.time = LocalDateTime.now();
	}

	public static Notification getNotification(Profile notificationCauser,String couse,TextContent source) throws Exception {
		if (couse.equals("postLike")) {
			return new postLikeNotification(notificationCauser, source);
		}
		if (couse.equals("postComment")) {
			return new postCommentNotification(notificationCauser, source);
		}
		if (couse.equals("commentLike")) {
			return new commentLikeNotification(notificationCauser, source);
		}
		return new Notification(notificationCauser,couse, source);
	}
	
	public TextContent getSource() {
		return this.source;
	}
	
	public String showNotification() {
		return "[" + super.getStringTime() + "] " + super.getAuthor() + this.getContent();
	}

	public LocalDateTime getTime() {
		return time;
	}
}
