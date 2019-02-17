package Common;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import Views.ProfileWindow;

public class Chat {

	
	public class Message extends TextContent{
		
		private Profile receiver;
		
		Message(String content, Profile author,Profile receiver) throws Exception {
			super(content, author);
			this.receiver = receiver;
		}
		
		@Override
		public String toString() {
			return "[" + super.getStringTime() + "]" + super.getAuthor().getName() + ": " + super.getContent();
		}
	}
	
	private Profile myProfile;
	private Profile otherPerson;
	private LocalDateTime lastUpdate;
	private Set<Message> messages;
	private Comparator<Message> comparator = (message1, message2) -> {
		return message1.getTime().compareTo(message2.getTime()) == 0 ? 1 : message1.getTime().compareTo(message2.getTime());
	};
	
	public Chat(Profile myProfile, Profile otherPerson) throws Exception {
		this.myProfile = myProfile;
		this.otherPerson = otherPerson;
		this.lastUpdate = LocalDateTime.now();
		this.messages = Collections.synchronizedSortedSet(new TreeSet<Message>(comparator));
	}

	void sendMessage(String content) throws Exception { //adding new message to the chat
		this.messages.add(new Message(content, myProfile, otherPerson));
	}
	
	void receiveMessage(String content) throws Exception {
		this.messages.add(new Message(content, otherPerson, myProfile));
	}
	void printChat() {
		this.messages.forEach(System.out::println);
	}
	

	LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	boolean hasParticipant(Profile profile) {
		if(this.myProfile.equals(profile) || this.otherPerson.equals(profile)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Chat) {
			return ((Chat)obj).myProfile.equals(this.otherPerson) && ((Chat)obj).myProfile.equals(this.otherPerson);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.otherPerson.hashCode()*this.myProfile.hashCode();
	}

	@Override
	public String toString() {
		return this.otherPerson.toString();
	}

	public Profile getOtherPerson() {
		return otherPerson;
	}

	public Set<Message> getMessages() {
		return Collections.unmodifiableSet(messages);
	}
	
	
	
}
