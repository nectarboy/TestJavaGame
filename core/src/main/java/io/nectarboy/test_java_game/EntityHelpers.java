package io.nectarboy.test_java_game;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import io.nectarboy.test_java_game.Components.KillEntityComponent;

public class EntityHelpers {
	private static ComponentMapper<KillEntityComponent> killEntityM = ComponentMapper.getFor(KillEntityComponent.class);
	
	public static void queueKillEntity(Main game, Entity entity) {
		KillEntityComponent killEntity = killEntityM.get(entity);
		if (killEntity != null)
			return;
		
		entity.add(game.engine.createComponent(KillEntityComponent.class));
	}
}
