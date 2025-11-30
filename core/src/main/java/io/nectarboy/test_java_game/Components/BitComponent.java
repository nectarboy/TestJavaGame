package io.nectarboy.test_java_game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.math.Vector2;

public class BitComponent implements Component, Poolable {
	public static enum Type {
		ZERO,
		ONE
	}
	
	public Type type = Type.ZERO;
	public float speedFactor = 0;
	public float width = 0;
	public float height = 0;
	
	public boolean homedIn = false;
	
	public void reset() {
		type = Type.ZERO;
		speedFactor = 0;
		width = 0;
		height = 0;
		homedIn = false;
	}
}
