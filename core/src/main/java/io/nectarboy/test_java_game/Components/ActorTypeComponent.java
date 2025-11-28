package io.nectarboy.test_java_game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ActorTypeComponent implements Component, Poolable {
	public enum Type {
		ACTOR_NULL,
		ACTOR_PLAYER,
		ACTOR_BIT,
		ACTOR_CHARGE,
		ACTOR_SKELETON
	}

	
	public Type type = Type.ACTOR_NULL;
	
	@Override
	public void reset() {
		type = Type.ACTOR_NULL;
	}
}
