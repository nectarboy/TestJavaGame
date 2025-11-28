package io.nectarboy.test_java_game.Messages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Deque;

public class MessageListener {
	private ArrayList<MessagePublisher> subscribedPublishers;
	private Deque<Message> messageQueue;
	
	public MessageListener() {
		subscribedPublishers = new ArrayList<>();
		messageQueue = new LinkedList<>();
	}
	
	public Iterator<Message> getMessageIterator() {
		return messageQueue.iterator();
	}
	
	public Message getNextMessage() {
		if (messageQueue.isEmpty()) return null;
		return messageQueue.pop();
	}
	
	public void queueMessage(Message message) {
		messageQueue.addLast(message);
	}
	
	public void subscribeToPublisher(MessagePublisher publisher) {
		publisher.addListener(this);
		subscribedPublishers.add(publisher);
	}
	public void unsubscribeToPublisher(MessagePublisher publisher) {
		publisher.removeListener(this);
		subscribedPublishers.remove(publisher);
	}

	public void unsubscribeToAllPublishers() {
		for (MessagePublisher publisher : subscribedPublishers) {
			publisher.removeListener(this);
		}
		subscribedPublishers.clear();
	}
	
	public void clearAllMessages() {
		messageQueue.clear();
	}
}
