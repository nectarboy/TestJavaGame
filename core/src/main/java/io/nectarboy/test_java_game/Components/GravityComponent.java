package io.nectarboy.test_java_game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.math.Vector2;

public class GravityComponent implements Component, Poolable {
	public Vector2 up = new Vector2(0f, 1f);
	public float max = 0; // Gravity will not make velocity exceed this magnitude
	public boolean forceMax = false; // When velocity > max, forceMax = true will force it to max; forceMax = false will simply not increase velocity 
	public float gravityUp = 0; // Gravity when velocity is up
	public float gravityDown = 0; // Gravity when velocity is down
	public float dampening = 1; // Dampening multiplier applied to velocity before gravity is added
	
	@Override
	public void reset() {
		up.set(0f, 1f);
		max = 0;
		forceMax = false;
		gravityUp = 0; // Gravity when velocity is up
		gravityDown = 0; // Gravity when velocity is down
	}
}
