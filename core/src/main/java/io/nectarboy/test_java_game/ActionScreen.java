package io.nectarboy.test_java_game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.physics.box2d.*;

import io.nectarboy.test_java_game.Systems.*;
import io.nectarboy.test_java_game.Components.*;
import io.nectarboy.test_java_game.Factories.*;
import io.nectarboy.test_java_game.Messages.MessagePublisher;

public class ActionScreen implements Screen {
		
	public Main game;
	
	public ActionScreen(Main game) {
		this.game = game;
		
		AssetManager.getInstance().loadTexture("player_idle");
		AssetManager.getInstance().loadTexture("player_hurt");
		AssetManager.getInstance().loadTexture("bit_0");
		AssetManager.getInstance().loadTexture("bit_1");
		
		PlayerActorSystem playerActorSystem = new PlayerActorSystem(game);
		BitActorSystem bitActorSystem = new BitActorSystem(game);
		PhysicsSystem physicsSystem = new PhysicsSystem(game);
		playerActorSystem.messageListener.subscribeToPublisher(physicsSystem.collisionMessagePublisher);
		bitActorSystem.messageListener.subscribeToPublisher(physicsSystem.collisionMessagePublisher);

		game.engine.addSystem(playerActorSystem);
		game.engine.addSystem(bitActorSystem);
		game.engine.addSystem(physicsSystem);
		game.engine.addSystem(new ActionScreenRenderSystem(game));
		game.engine.addSystem(new KillEntitySystem(game));

		
		game.engine.addEntity(PlayerFactory.make(game.engine, game.world, game.viewport.getWorldWidth()/4, game.viewport.getWorldHeight()/2));
		game.engine.addEntity(BitFactory.make(game.engine, game.world, game.viewport.getWorldWidth()/2, game.viewport.getWorldHeight()/2, BitComponent.Type.ZERO));
		
		setupWorldStaticBarrier();
	}
	
	private void setupWorldStaticBarrier() {
		float viewWidth = game.viewport.getWorldWidth();
		float viewHeight = game.viewport.getWorldHeight();
		final float[][] rects = {
				{0, viewWidth, -1, 0},
				{-1, 0, 0, viewHeight},
				{viewWidth, viewWidth+1, 0, viewHeight},
				{0, viewWidth, viewHeight, viewHeight+1},
				{20, 40, 20, 40}
		};
		
		for (float[] rect : rects) {
			float x0 = rect[0];
			float x1 = rect[1];
			float y0 = rect[2];
			float y1 = rect[3];
			float width = x1 - x0;
			float height = y1 - y0;
			Vector2 position = new Vector2(x0 + width/2, y0 + height/2);
			PhysicsBodyFactory.makeBox(game.world, BodyDef.BodyType.StaticBody, false, position, width, height);
		}
	}
	
	private void logic() {
		
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		logic();
	}
	
	@Override
	public void resize(int width, int height) {
		game.viewport.update(width, height, true);
	}
	
	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
	
}
