package io.nectarboy.test_java_game.Messages;

import java.util.ArrayList;

public class MessagePublisher {
	private ArrayList<MessageListener> listeners;
	
	public MessagePublisher() {
		listeners = new ArrayList<MessageListener>();
	}
	
	void addListener(MessageListener listener) {
		listeners.add(listener);
	}
	
	void removeListener(MessageListener listener) {
		listeners.remove(listener);
	}
	
	public void publishMessage(Message message) {
		for (MessageListener listener : listeners) {
			listener.queueMessage(message);
		}
	}
}
