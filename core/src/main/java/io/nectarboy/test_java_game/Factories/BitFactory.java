package io.nectarboy.test_java_game.Factories;

import io.nectarboy.test_java_game.RNG;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Factories.*;
import io.nectarboy.test_java_game.Systems.*;
import io.nectarboy.test_java_game.Messages.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.*;

public class BitFactory {
	public static Entity make(PooledEngine engine, World world, float x, float y, BitComponent.Type bitType) {
		Entity entity = engine.createEntity();
		
		BitComponent bit = engine.createComponent(BitComponent.class);
		bit.speedFactor = RNG.randomFloat();
		bit.type = bitType;
		bit.width = 8 * PhysicsSystem.WORLD_SCALE;
		bit.height = bit.width;
		entity.add(bit);
		
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.position.x = x;
		transform.position.y = y;
		entity.add(transform);
		
		ActorTypeComponent type = engine.createComponent(ActorTypeComponent.class);
		type.type = ActorTypeComponent.Type.ACTOR_BIT;
		entity.add(type);
		
		PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
		physics.collisionMessageT = MessageType.COLLISION_BIT;
		
		Body body = PhysicsBodyFactory.makeBox(world, BodyDef.BodyType.DynamicBody, true, transform.position, bit.width, bit.height);
		body.setUserData(entity);
		physics.body = body;
		entity.add(physics);

		SpriteComponent sprite = SpriteComponentFactory.make(engine, bitType == BitComponent.Type.ZERO ? "bit_0" : "bit_1");
		sprite.scale.scl(PhysicsSystem.WORLD_SCALE);
		sprite.zIndex = 1 - bit.speedFactor;
		entity.add(sprite);
		
		return entity;
	}
}
