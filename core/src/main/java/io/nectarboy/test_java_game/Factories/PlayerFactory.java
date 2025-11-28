package io.nectarboy.test_java_game.Factories;

import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Factories.*;
import io.nectarboy.test_java_game.Systems.*;
import io.nectarboy.test_java_game.Messages.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.*;

public class PlayerFactory {
	
	public static Entity make(PooledEngine engine, World world, float x, float y) {
		Entity entity = engine.createEntity();
		
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.position.x = x;
		transform.position.y = y;
		entity.add(transform);
				
		PlayerComponent player = engine.createComponent(PlayerComponent.class);
		entity.add(player);
		
		ActorTypeComponent type = engine.createComponent(ActorTypeComponent.class);
		type.type = ActorTypeComponent.Type.ACTOR_PLAYER;
		entity.add(type);
		
		PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
		physics.collisionMessageT = MessageType.COLLISION_PLAYER;
		
		Body body = PhysicsBodyFactory.makeBox(world, BodyDef.BodyType.DynamicBody, transform.position, 16 * PhysicsSystem.WORLD_SCALE, 16 * PhysicsSystem.WORLD_SCALE);
		body.setUserData(entity);
		physics.body = body;
		entity.add(physics);
	
		SpriteComponent sprite = SpriteComponentFactory.make(engine, "player_idle");
		sprite.scale.scl(PhysicsSystem.WORLD_SCALE);
		entity.add(sprite);
		
//		MessageListenerComponent messageListener = engine.createComponent(MessageListenerComponent.class);
//		entity.add(messageListener);
		
		return entity;
	}
	
}
