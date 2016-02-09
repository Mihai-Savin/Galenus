package ro.sci.gms.dao;

import java.util.List;

import ro.sci.gms.domain.Discussion.Message;

public interface DiscussionDAO {

	public List<Message> getMessagesForUser(long userId);

	public List<Message> getFilteredMessagesForUser(long userId, String searchToken);

	public void addMessage(Message message);

}
