package io.nectarboy.test_java_game.Factories;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Sprite;

import io.nectarboy.test_java_game.AssetManager;
import io.nectarboy.test_java_game.Components.SpriteComponent;

public class SpriteComponentFactory {
	public static SpriteComponent make(PooledEngine engine, String textureName) {
		SpriteComponent sprite = engine.createComponent(SpriteComponent.class);
		sprite.sprite = new Sprite(AssetManager.getInstance().getTexture(textureName));
		
		return sprite;
	}
}
