package io.nectarboy.test_java_game;

import com.badlogic.gdx.Gdx;

public class FrameLimiter {
		
	final float FRAME_MS = 1f / 60f;
	private float deltaCounter;
	
	public FrameLimiter() {
		deltaCounter = 0;
	}
	
	public boolean tick() {
		deltaCounter += Gdx.graphics.getDeltaTime();
		if (deltaCounter > FRAME_MS) {
       		deltaCounter -= FRAME_MS;
    		if (deltaCounter > FRAME_MS * 3) {
    			deltaCounter = FRAME_MS * 3;
    		}
    		return true;
    	}
    	return false;
	}
	
}
