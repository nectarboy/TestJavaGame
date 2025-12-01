package io.nectarboy.test_java_game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

import io.nectarboy.test_java_game.Systems.PhysicsSystem;

import com.badlogic.gdx.math.Vector2;

public class PlayerBulletComponent implements Component, Poolable {
	public int level = 0;
	public float speed = 0;
	public float width = 0;
	public float height = 0;
	
	public void setLevel(int level) {
		this.level = level;
		switch (level) {
			case 1:
			case 2:
				speed = (10f + 4f*((float)level-1f)) * PhysicsSystem.WORLD_SCALE;
				width = 16 * PhysicsSystem.WORLD_SCALE;
				height = 2 * PhysicsSystem.WORLD_SCALE;
				break;
			case 3:
				speed = 16f * PhysicsSystem.WORLD_SCALE;
				width = 16 * PhysicsSystem.WORLD_SCALE;
				height = 4 * PhysicsSystem.WORLD_SCALE;
				break;
		}
	}
	
	public void reset() {
		level = 0;
		speed = 0;
		width = 0;
		height = 0;
	}
}
