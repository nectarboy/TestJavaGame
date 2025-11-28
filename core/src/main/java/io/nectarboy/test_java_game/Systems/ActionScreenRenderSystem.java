package io.nectarboy.test_java_game.Systems;

import io.nectarboy.test_java_game.Background;
import io.nectarboy.test_java_game.Main;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Systems.SystemPriorityDictionary;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Comparator;

/**
 * Renders things the ActionScreen contains
 * Entities, background, etc 
 */
public class ActionScreenRenderSystem extends SortedIteratingSystem {
	private Main game;
	private Background background;
	
	private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);
	private ComponentMapper<SpriteComponent> spriteM = ComponentMapper.getFor(SpriteComponent.class);
	
	private static class Sorter implements Comparator<Entity>{
		private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);
		private ComponentMapper<SpriteComponent> spriteM = ComponentMapper.getFor(SpriteComponent.class);
	    public Sorter() {}

	    @Override
	    public int compare(Entity a, Entity b) {
	        return (int)Math.ceil(spriteM.get(a).zIndex - spriteM.get(b).zIndex); // ascending
	    }
	}
	
	public ActionScreenRenderSystem(Main game) {
		super(Family.all(SpriteComponent.class, TransformComponent.class).get(), new Sorter(), SystemPriorityDictionary.Priority.ACTION_SCREEN_RENDER.ordinal());
		this.game = game;
		background = new Background(game);
	}
	
	@Override
	public void update(float deltaTime) {
		game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        
        game.batch.begin();
        
		background.draw();
		
		super.update(deltaTime);
		
		game.batch.end();
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		SpriteComponent sprite = spriteM.get(entity);
		TransformComponent transform = transformM.get(entity);
		
		if (sprite.sprite == null)
			return;
		
		sprite.sprite.setCenter(transform.position.x + sprite.position.x, transform.position.y + sprite.position.y);
		sprite.sprite.setScale(transform.scale.x * sprite.scale.x, transform.scale.y * sprite.scale.y);
		sprite.sprite.setRotation(transform.rotation + sprite.rotation);
		sprite.sprite.draw(game.batch);
	}
	
}
