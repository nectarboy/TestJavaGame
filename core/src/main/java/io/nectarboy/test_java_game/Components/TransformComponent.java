package io.nectarboy.test_java_game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component, Poolable {
	public Vector2 position = new Vector2();
	public Vector2 scale = new Vector2(1f, 1f);
	public float rotation = 0f;
	
	@Override
	public void reset() {
		position.set(0f, 0f);
		scale.set(1f, 1f);
		rotation = 0f;
	}
}
