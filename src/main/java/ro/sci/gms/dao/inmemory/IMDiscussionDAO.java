package ro.sci.gms.dao.inmemory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ro.sci.gms.dao.DiscussionDAO;
import ro.sci.gms.domain.Discussion.Message;

@Repository
public class IMDiscussionDAO implements DiscussionDAO {
	
	private List<Message> mockMessages = new ArrayList<Message>();
	
	@Override
	public void addMessage(Message message) {		
		mockMessages.add(message);
	}

	@Override
	public List<Message> getMessagesForUser(long userId) {
		List<Message> messages = new ArrayList<Message>();
		for (Message message : mockMessages) {
			if (message.getSender().getId() == userId || message.getReceiver().getId() == userId) {
				messages.add(message);
			}
		}
		return messages;
	}

	@Override
	public List<Message> getFilteredMessagesForUser(long userId, String searchToken) {
		List<Message> messages = new ArrayList<Message>();
		for (Message message : mockMessages) {
			if (message.getSender().getId() == userId || message.getReceiver().getId() == userId) {
				if (searchToken == null || searchToken.trim().isEmpty() || message.getContent().toLowerCase().contains(searchToken.toLowerCase())) {
					messages.add(message);
				}
			}
		}
		return messages;
	}

}
