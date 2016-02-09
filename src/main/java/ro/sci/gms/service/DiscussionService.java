package ro.sci.gms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.gms.dao.DiscussionDAO;
import ro.sci.gms.domain.Discussion;
import ro.sci.gms.domain.Discussion.Message;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.User;

@Service
public class DiscussionService {
	
	@Autowired
	private Patient patient;
	@Autowired
	private Doctor doctor;
	
	@Autowired
	private DiscussionDAO discussionDAO;
	
	public Discussion getDiscussionForUser(long userId) {
		List<Message> messages = discussionDAO.getMessagesForUser(userId);
		Discussion discussion = new Discussion();
		discussion.setMessages(messages);
		return discussion;
	}
	
	public Discussion getFilteredDiscussionForUser(long userId, String searchToken) {
		List<Message> messages = discussionDAO.getFilteredMessagesForUser(userId, searchToken);
		Discussion discussion = new Discussion();
		discussion.setMessages(messages);
		return discussion;
	}
	
	public void addMessage(long senderId, long receiverId, String content) {
		// TODO: call UserService to retrieve sender/receiver by id
		User sender = (senderId == doctor.getId()) ? doctor : patient;
		User receiver = (receiverId == doctor.getId()) ? doctor : patient;
		Message message = new Message();
		message.setContent(content);
		message.setReceiver(receiver);
		message.setSender(sender);
		message.setDate(new Date());
		discussionDAO.addMessage(message);
	}
	
	public User getMessageReceiverForUser(User user) {
		if (user instanceof Doctor) {
			return ((Doctor)user).getPatient();
		} else if (user instanceof Patient) {
			return ((Patient)user).getDoctor();
		} else {
			return null;
		}
	}
	
	public User getMessageReceiverForUser(long userId) {
		// TODO: call UserService to retrieve the user by id
		User user = (userId == doctor.getId()) ? doctor : patient;
		return getMessageReceiverForUser(user);
	}

}
