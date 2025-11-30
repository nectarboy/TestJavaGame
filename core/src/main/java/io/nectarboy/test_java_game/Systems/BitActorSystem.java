package io.nectarboy.test_java_game.Systems;

import io.nectarboy.test_java_game.EntityHelpers;
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


public class BitActorSystem extends IteratingSystem {
	private ComponentMapper<BitComponent> bitM = ComponentMapper.getFor(BitComponent.class);
	private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);
	private ComponentMapper<PhysicsComponent> physicsM = ComponentMapper.getFor(PhysicsComponent.class);
	
	private Main game;
	public MessageListener messageListener = new MessageListener();
	
	public BitActorSystem(Main game) {
		super(Family.all(BitComponent.class, TransformComponent.class).get());
		this.game = game;
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
				case ACTOR_BIT_COLLECTED:
					System.out.println("Bit dies.");
					EntityHelpers.queueKillEntity(game, recipient);
					break;
				case ACTOR_BIT_HOMEIN:
					System.out.println("Bit homes in.");
					Entity target = m.getSender();
					TransformComponent targetTransform = transformM.get(target);
					if (targetTransform == null)
						break;
						
					BitComponent bit = bitM.get(recipient);
					TransformComponent transform = transformM.get(recipient);
					PhysicsComponent physics = physicsM.get(recipient);
					
					if (bit.homedIn)
						break;
				
					bit.homedIn = true;
					physics.velocity.add(targetTransform.position.cpy().sub(transform.position).scl(0.33f));
					physics.velocity.scl(0.75f);
					if (physics.velocity.x < 0)
						physics.velocity.x *= -1;
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
		BitComponent bit = bitM.get(entity);
		TransformComponent transform = transformM.get(entity);
		PhysicsComponent physics = physicsM.get(entity);
		if (physics == null)
			return;
		
		// Destroy bits out of bounds
		if (
				transform.position.x > game.viewport.getWorldWidth() + bit.width + bit.height ||
				transform.position.y > game.viewport.getWorldHeight() + bit.width + bit.height ||
				transform.position.y < 0 - bit.width - bit.height
		) {
			EntityHelpers.queueKillEntity(game, entity);
		}
		
		if (bit.homedIn) {
			
		}
		else {
			float speed = (1.5f * bit.speedFactor + 3f) * PhysicsSystem.WORLD_SCALE;
			physics.velocity.set(speed, 0);
		}
	}
	
	@Override
	public void removedFromEngine(Engine engine) {
		messageListener.unsubscribeToAllPublishers();
		super.removedFromEngine(engine);
	}
}
