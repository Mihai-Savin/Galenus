package ro.sci.gms.domain;

import java.util.Date;
import java.util.List;

public class Discussion {
	
//	private Doctor doctor;
//	private Patient patient;
	private List<Message> messages;
	
	public static class Message {
		
		private Date date;
		private String content;
		private User sender;
		private User receiver;		
		
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public User getSender() {
			return sender;
		}
		public void setSender(User sender) {
			this.sender = sender;
		}
		public User getReceiver() {
			return receiver;
		}
		public void setReceiver(User receiver) {
			this.receiver = receiver;
		}		
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
