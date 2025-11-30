package io.nectarboy.test_java_game.Factories;
import io.nectarboy.test_java_game.FixtureData;
import io.nectarboy.test_java_game.Main;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Messages.MessageType;
import io.nectarboy.test_java_game.Systems.PhysicsSystem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsBodyFactory {
	public static Body makeBody(World world, BodyDef.BodyType bodyType, Vector2 position) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(position.x, position.y);

		return world.createBody(bodyDef);
	}
	
	public static Fixture addBoxFixtureToBody(Body body, MessageType messageT, boolean isSensor, float width, float height, Vector2 center, float rotation) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2, center, rotation);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.isSensor = isSensor;
		fixtureDef.shape = shape;

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);
		
		FixtureData fixtureData = new FixtureData(messageT);
		fixture.setUserData(fixtureData);
	
		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		shape.dispose(); // ..? do we do this here
		
		return fixture;
	}
	
	public static Fixture addCircleFixtureToBody(Body body, MessageType messageT, boolean isSensor, float radius, Vector2 center, float rotation) {
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.isSensor = isSensor;
		fixtureDef.shape = shape;

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);
		
		FixtureData fixtureData = new FixtureData(messageT);
		fixture.setUserData(fixtureData);
	
		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		shape.dispose(); // ..? do we do this here
		
		return fixture;
	}
}
