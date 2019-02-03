package Common;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Chat {

	
	private class Message extends TextContent{

		Message(String content, Profile author) throws Exception {
			super(content, author);
		}
		
		@Override
		public String toString() {
			return "[" + super.getStringTime() + "]" + super.getAuthor().getName() + ": " + super.getContent();
		}
	}
	
	
	private Profile sender;
	private Profile receiver;
	private LocalDateTime lastUpdate;
	private Set<Message> messages;
	private Comparator<Message> comparator = (Message message1,Message message2) -> message1.getTime().compareTo(message2.getTime());
	
	public Chat(Profile sender, Profile receiver) throws Exception {
		this.setSender(sender);
		this.setReceiver(receiver);
		this.lastUpdate = LocalDateTime.now();
		this.messages = Collections.synchronizedSortedSet(new TreeSet<Message>(comparator));
	}

	void sendMessage(String content, Profile author) throws Exception { //adding new message to the chat
		this.messages.add(new Message(content, author));
	}
	
	void printChat() {
		this.messages.forEach(System.out::println);
	}
	
	private void setSender(Profile sender) throws Exception {
		if(sender == null) {
			throw new Exception("Sender of chat is null!");
		}
		this.sender = sender;
	}

	private void setReceiver(Profile receiver) throws Exception {
		if(receiver == null) {
			throw new Exception("Receiver of chat is null!");
		}
		this.receiver = receiver;
	}

	LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	boolean hasParticipant(Profile profile) {
		if(this.sender.equals(profile) || this.receiver.equals(profile)) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
}
