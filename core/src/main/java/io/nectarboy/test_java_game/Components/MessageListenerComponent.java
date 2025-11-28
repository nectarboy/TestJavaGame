package io.nectarboy.test_java_game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import io.nectarboy.test_java_game.Messages.MessageListener;

public class MessageListenerComponent implements Component, Poolable {
	public MessageListener listener = new MessageListener();
	
	@Override
	public void reset() {
		listener.unsubscribeToAllPublishers();
		listener.clearAllMessages();
	}
}
