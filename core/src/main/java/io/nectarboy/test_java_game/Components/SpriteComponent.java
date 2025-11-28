package io.nectarboy.test_java_game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SpriteComponent implements Component, Poolable {
	public Sprite sprite = null;
	public Vector2 position = new Vector2();
	public Vector2 scale = new Vector2(1f, 1f);
	public float rotation = 0f;
	public float zIndex = 0;
	
	@Override
	public void reset() {
		sprite = null;
		position.set(0f, 0f);
		scale.set(1f, 1f);
		rotation = 0f;
		zIndex = 0;
	}
}
