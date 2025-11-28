package io.nectarboy.test_java_game.Messages;

import com.badlogic.ashley.core.Entity;

public class Message {
	private MessageType type;
	private Entity sender;
	private Entity recipient;
	
	public Message(MessageType type, Entity sender, Entity recipient) {
		this.type = type;
		this.sender = sender;
		this.recipient = recipient;
	}
	
	public MessageType getType() { return type; }
	public Entity getSender() { return sender; }
	public Entity getRecipient() { return recipient; }
}
