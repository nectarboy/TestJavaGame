package io.nectarboy.test_java_game.Systems;

import io.nectarboy.test_java_game.Main;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Messages.*;
import io.nectarboy.test_java_game.Systems.*;

import java.util.Iterator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class KillEntitySystem extends IteratingSystem {
	private ComponentMapper<KillEntityComponent> killEntityM = ComponentMapper.getFor(KillEntityComponent.class);
	private ComponentMapper<PhysicsComponent> physicsM = ComponentMapper.getFor(PhysicsComponent.class);
	
	private Main game;
	
	public KillEntitySystem(Main game) {
		super(Family.all(KillEntityComponent.class).get(), SystemPriorityDictionary.Priority.KILL_ENTITY.ordinal());
		this.game = game;
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
//		KillEntityComponent killEntity = killEntityM.get(entity);
		PhysicsComponent physics = physicsM.get(entity);
		
		if (physics != null) {
			game.world.destroyBody(physics.body);
		}
		
		Iterator<Body> bodies = physics.bodies.iterator();
		while (bodies.hasNext()) {
			game.world.destroyBody(bodies.next());
		}
		
		game.engine.removeEntity(entity);
	}
	
//	@Override
//	public void removedFromEngine(Engine engine) {
//		super.removedFromEngine(engine);
//	}
}
