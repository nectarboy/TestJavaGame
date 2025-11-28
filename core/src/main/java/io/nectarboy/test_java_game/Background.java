package io.nectarboy.test_java_game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Background {
	private Main game;
	private SpriteBatch batch;
	private Texture[] textures;
	private final float[] plxFactors = {0.1f, 0.2f, 0.3f, 0.5f, 1.0f};
	
	public Background(Main game) {
		this.game = game;
		batch = new SpriteBatch();
		textures = new Texture[5];
		for (int i = 0; i < textures.length; i++) {
			AssetManager.getInstance().loadTexture("background_" + (textures.length - 1 - i));
			textures[i] = AssetManager.getInstance().getTexture("background_" + (textures.length - 1 - i));
			textures[i].setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		}
	}
	
	public void draw() {
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();
        for (int i = 0; i < textures.length; i++) {
        	float scroll = -(float)game.getTick() / 100.0f * plxFactors[i];
        	game.batch.draw(textures[i], 0, 0, worldWidth, worldHeight, 1 + scroll, 1, scroll, 0);
        }
	}
}
