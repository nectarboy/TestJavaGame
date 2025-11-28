package io.nectarboy.test_java_game.Factories;
import io.nectarboy.test_java_game.Main;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Systems.PhysicsSystem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsBodyFactory {
	public static Body makeBox(World world, BodyDef.BodyType bodyType, boolean isSensor, Vector2 position, float width, float height) {
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(position.x, position.y);

		Body body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.isSensor = isSensor;
		fixtureDef.shape = shape;

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);
	
		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		shape.dispose();
		
		return body;
	}
}
