package io.nectarboy.test_java_game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.math.Vector2;

public class PlayerComponent implements Component, Poolable {
	public enum State {
		NORMAL
	};
	
	public State state = State.NORMAL;
	
	@Override
	public void reset() {
		state = State.NORMAL;
	}
}
