package io.nectarboy.test_java_game.Systems;

import io.nectarboy.test_java_game.FixtureData;
import io.nectarboy.test_java_game.Main;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Messages.Message;
import io.nectarboy.test_java_game.Messages.MessagePublisher;
import io.nectarboy.test_java_game.Systems.SystemPriorityDictionary;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsSystem extends IteratingSystem {
	final public static float WORLD_SCALE = 0.02f; // Move somewhere else?
	final public static float I_WORLD_SCALE = 1 / WORLD_SCALE;
	
	private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);
	private ComponentMapper<PhysicsComponent> physicsM = ComponentMapper.getFor(PhysicsComponent.class);
	
	private Main game;
	public MessagePublisher collisionMessagePublisher = new MessagePublisher();
	
	private class CollisionListener implements ContactListener {
		@Override
		public void endContact(Contact contact) {
			
		}
		
		@Override
		public void beginContact(Contact contact) {
			Fixture fixtureA = contact.getFixtureA();
			Fixture fixtureB = contact.getFixtureB();
			Entity a = (Entity)fixtureA.getBody().getUserData();
			Entity b = (Entity)fixtureB.getBody().getUserData();
			if (a == null || b == null) {
//				System.out.println(a);
//				System.out.println(b);
				return;
			}
			
			PhysicsComponent physicsA = physicsM.get(a);
			PhysicsComponent physicsB = physicsM.get(b);
			if (physicsA == null || physicsB == null)
				return;
			
//			System.out.println("Hi.");
			FixtureData dataA = (FixtureData)fixtureA.getUserData();
			FixtureData dataB = (FixtureData)fixtureB.getUserData();
			if (dataA == null || dataB == null)
				return;
			
			collisionMessagePublisher.publishMessage(new Message(dataA.collisionMessageT, a, b));
			collisionMessagePublisher.publishMessage(new Message(dataB.collisionMessageT, b, a));
		}
		
		@Override
		public void preSolve(Contact contact, Manifold manifold) {
			
		}
		
		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			
		}

	}
	
	public PhysicsSystem(Main game) {
		super(Family.all(TransformComponent.class, PhysicsComponent.class).get(), SystemPriorityDictionary.Priority.PHYSICS.ordinal());
		this.game = game;
		game.world.setContactListener(new CollisionListener());
	}
	
	@Override
	public void update(float deltaTime) {
		ImmutableArray<Entity> entities = getEntities();
		for (Entity entity : entities) {
			TransformComponent transform = transformM.get(entity);
			PhysicsComponent physics = physicsM.get(entity);
			
			physics.velocity.add(physics.acceleration);

			Body body = physics.body;
			if (body == null) continue;
			
			body.setTransform(transform.position, transform.rotation * MathUtils.degreesToRadians);
			body.setLinearVelocity(physics.velocity);
		}
		
		game.world.step(1f, 6, 2);
		super.update(deltaTime);
	}

	public void processEntity(Entity entity, float deltaTime) {
		TransformComponent transform = transformM.get(entity);
		PhysicsComponent physics = physicsM.get(entity);
		
		Body body = physics.body;
		if (body != null) {
			transform.position.set(body.getPosition());
		}
		
//		physics.velocity.add(physics.acceleration);
//		transform.position.add(physics.velocity);
	}

}