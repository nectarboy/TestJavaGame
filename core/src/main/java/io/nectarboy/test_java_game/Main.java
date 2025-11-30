package io.nectarboy.test_java_game;

import io.nectarboy.test_java_game.Systems.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.*;

public class Main extends Game {
	
	final private float FRAME_MS = 1f / 60f;
	final private int METER = 32; 
	
	public SpriteBatch batch;
	public FitViewport viewport;
	public Screen screen;
	public PooledEngine engine;
	public World world;
//	public FrameLimiter frameLimiter;
	
	Box2DDebugRenderer debugRenderer;
	
	private float deltaCounter;
	private int tick;
	
	public void create() {
		batch = new SpriteBatch();
		viewport = new FitViewport(320 * PhysicsSystem.WORLD_SCALE, 240 * PhysicsSystem.WORLD_SCALE);
//		frameLimiter = new FrameLimiter();
		deltaCounter = 0;
		world = new World(new Vector2(0, 0), false);
		engine = new PooledEngine(250, 500, 250, 500);
		screen = new ActionScreen(this);
		this.setScreen(screen);
		
		debugRenderer = new Box2DDebugRenderer();
	}
	
	private void logic() {
		super.render();
		engine.update(0);
		tick++;
		
//		debugRenderer.render(world, viewport.getCamera().combined);
	}
	
	public int getTick() { return tick; }
	
	public void render() {
    	deltaCounter += Gdx.graphics.getDeltaTime();
    	if (deltaCounter > FRAME_MS) {
    		logic();
    		deltaCounter -= FRAME_MS;
    		if (deltaCounter > FRAME_MS) {
    			logic();
    			if (deltaCounter > FRAME_MS*5)
        			deltaCounter = FRAME_MS*5;
        		deltaCounter -= FRAME_MS;
    		}
    	}

	}
	
	public void dispose() {
		batch.dispose();
	}
	
}
