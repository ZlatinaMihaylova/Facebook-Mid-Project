package Common;

public class Advertisement extends Thread{

	private FacebookSystem system = FacebookSystem.getFacebookSystem();
	private String text;
	private Profile author;
	private Photo photo;
	
	
	public Advertisement(String text, Profile author, String picturePath) throws Exception {
		this.text = text;
		this.author = author;
		this.photo = new Photo(FileHelper.getInstance().uploadPicture(picturePath, this.author.hashCode()+""));
		this.setDaemon(true);
	}


	@Override
	public void run() {
		
		try {
			
			Post advertisement = new Post(text, author, photo);
			for(User user : this.system.getUsers()) {
				user.addPostToNewsFeed(advertisement);
			}
			for(Page page : this.system.getPages()) {
				page.addPostToNewsFeed(advertisement);
			}
			
		} catch (Exception e) {
			System.out.println("Prekusnahte reklamata!");
			return;
		}
		
		
		
	}
	
	
	
}
