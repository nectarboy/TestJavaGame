package io.nectarboy.test_java_game;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.utils.viewport.FitViewport;
import io.nectarboy.test_java_game.AssetPathDictionary;

public class AssetManager {

	private HashMap<String, Texture> textures = new HashMap<>();
	
	public void loadTexture(String name) {
		String path = AssetPathDictionary.getInstance().getTexturePath(name);
		if (path == null)
			return;
		Texture texture = new Texture(path);
		texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		textures.put(name, texture);
	}
	
	public void unloadTexture(String name) {
		if (textures.containsKey(name))
			textures.remove(name);
	}
	
	public void unloadAllTextures() {
		textures = new HashMap<>();
	}
	
	public Texture getTexture(String name) {
		if (textures.containsKey(name))
			return textures.get(name);
		return null;
	}
	
	
	private static class SingletonHolder {
		private static final AssetManager instance = new AssetManager();
	}
	
	public static AssetManager getInstance() {
		return SingletonHolder.instance;
	}
}
