package io.nectarboy.test_java_game.Systems;

import io.nectarboy.test_java_game.Background;
import io.nectarboy.test_java_game.Main;
import io.nectarboy.test_java_game.RNG;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Factories.*;
import io.nectarboy.test_java_game.Systems.SystemPriorityDictionary;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;

/**
 * Primarily responsible for spawning bits, enemies, and bosses.
 */

public class ActionScreenSpawnerSystem extends EntitySystem {
	
	private Main game;
	private int bitSpawnTick;
	
	public ActionScreenSpawnerSystem(Main game) {
		super();
		this.game = game;
		
		bitSpawnTick = 0;
	}
	
	private void updateBitSpawns() {
		float viewWidth = game.viewport.getWorldWidth();
		float viewHeight = game.viewport.getWorldHeight();
		
		bitSpawnTick++;
		
		if ((bitSpawnTick % 3) == 0) {
			bitSpawnTick = 0;
			
			BitComponent.Type type = RNG.getRandomInt(0, 1) == 1 ? BitComponent.Type.ONE : BitComponent.Type.ZERO;
			game.engine.addEntity(BitFactory.make(game.engine, game.world, RNG.randomFloat() * viewWidth, RNG.randomFloat() * viewHeight, type));
		}
	}
	
	@Override
	public void update(float deltaTime) {
		updateBitSpawns();
	}

}
