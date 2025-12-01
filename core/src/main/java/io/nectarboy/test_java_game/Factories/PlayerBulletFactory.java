package io.nectarboy.test_java_game.Factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import io.nectarboy.test_java_game.Components.ActorTypeComponent;
import io.nectarboy.test_java_game.Components.BitComponent;
import io.nectarboy.test_java_game.Components.PhysicsComponent;
import io.nectarboy.test_java_game.Components.PlayerBulletComponent;
import io.nectarboy.test_java_game.Components.SpriteComponent;
import io.nectarboy.test_java_game.Components.TransformComponent;
import io.nectarboy.test_java_game.Messages.MessageType;
import io.nectarboy.test_java_game.Systems.PhysicsSystem;

public class PlayerBulletFactory {
	public static Entity make(PooledEngine engine, World world, float x, float y, int level) {
		Entity entity = engine.createEntity();
		
		PlayerBulletComponent playerBullet = engine.createComponent(PlayerBulletComponent.class);
		playerBullet.setLevel(level);
		entity.add(playerBullet);
		
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.position.x = x;
		transform.position.y = y;
		entity.add(transform);
		
		ActorTypeComponent type = engine.createComponent(ActorTypeComponent.class);
		type.type = ActorTypeComponent.Type.ACTOR_PLAYER_BULLET;
		entity.add(type);
		
		PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
		physics.body = PhysicsBodyFactory.makeBody(world, BodyDef.BodyType.DynamicBody, transform.position);
		physics.body.setUserData(entity);
		PhysicsBodyFactory.addBoxFixtureToBody(physics.body, MessageType.COLLISION_PLAYER_BULLET, true, playerBullet.width, playerBullet.height, new Vector2(), 0);
		entity.add(physics);
//		physics.velocity.x = 1f * PhysicsSystem.WORLD_SCALE;

		SpriteComponent sprite = SpriteComponentFactory.make(engine, level < 3 ? "player_bullet_0" : "player_bullet_1");
		sprite.scale.scl(PhysicsSystem.WORLD_SCALE);
		sprite.zIndex = 2;
		entity.add(sprite);
		
		return entity;
	}
}
