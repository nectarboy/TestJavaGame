package io.nectarboy.test_java_game.Systems;

import io.nectarboy.test_java_game.Main;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Messages.*;
import io.nectarboy.test_java_game.Systems.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class PlayerActorSystem extends IteratingSystem {
	private ComponentMapper<PlayerComponent> playerM = ComponentMapper.getFor(PlayerComponent.class);
	private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);
	private ComponentMapper<PhysicsComponent> physicsM = ComponentMapper.getFor(PhysicsComponent.class);
	
	public MessageListener messageListener = new MessageListener();
	
	public PlayerActorSystem(Main game) {
		super(Family.all(PlayerComponent.class, TransformComponent.class).get());
	}
	
	private void handleMessages() {
		Message nextM = messageListener.getNextMessage();
		while (nextM != null) {
			Message m = nextM;
			nextM = messageListener.getNextMessage();
			
			Entity recipient = m.getRecipient();
			if (!getFamily().matches(recipient))
				continue;
			
			switch (m.getType()) {
				case COLLISION:
					System.out.println("Collide with unknown.");
					break;
				case COLLISION_BIT:
					System.out.println("Collide with bit.");
					break;
			}
		}
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		handleMessages();
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		PlayerComponent player = playerM.get(entity);
		TransformComponent transform = transformM.get(entity);
		PhysicsComponent physics = physicsM.get(entity);
		
		final float SPEED = 5 * PhysicsSystem.WORLD_SCALE;
		
		// Input
		Vector2 direction = new Vector2();
		
		if (Gdx.input.isKeyPressed(Input.Keys.W))
			direction.y += 1;
		if (Gdx.input.isKeyPressed(Input.Keys.S))
			direction.y -= 1;
		if (Gdx.input.isKeyPressed(Input.Keys.D))
			direction.x += 1;
		if (Gdx.input.isKeyPressed(Input.Keys.A))
			direction.x -= 1;
		
		if (direction.x != 0 && direction.y != 0)
			direction.scl(0.9f);
		
		physics.velocity.set(direction.scl(SPEED));
	}
	
	@Override
	public void removedFromEngine(Engine engine) {
		messageListener.unsubscribeToAllPublishers();
		super.removedFromEngine(engine);
	}
}
