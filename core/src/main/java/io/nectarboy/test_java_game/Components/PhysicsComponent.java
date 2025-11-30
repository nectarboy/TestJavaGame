package io.nectarboy.test_java_game.Components;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

import io.nectarboy.test_java_game.Messages.MessageType;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsComponent implements Component, Poolable {
	public Body body = null;
	public LinkedList<Body> bodies = new LinkedList<Body>();
	public LinkedList<Vector2> bodyOffsets = new LinkedList<Vector2>();
	public Vector2 acceleration = new Vector2();
	public Vector2 velocity = new Vector2();
	
	@Override
	public void reset() {
		body = null;
		bodies.clear();
		bodyOffsets.clear();
		acceleration.set(0f, 0f);
		velocity.set(0f, 0f);
	}
}
