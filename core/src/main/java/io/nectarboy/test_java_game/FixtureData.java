package io.nectarboy.test_java_game;

import io.nectarboy.test_java_game.Messages.MessageType;

public class FixtureData {
	public MessageType collisionMessageT;
	public FixtureData(MessageType collisionMessageT) {
		this.collisionMessageT = collisionMessageT;
	}
	public FixtureData() {
		collisionMessageT = MessageType.COLLISION;
	}
}