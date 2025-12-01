package io.nectarboy.test_java_game;

import java.util.HashMap;

public class AssetPathDictionary {
	private HashMap<String, String> textures = new HashMap<>();
	
	private AssetPathDictionary() {
		textures.put("player_idle", "player_idle.png");
		textures.put("player_hurt", "player_hurt.png");
		textures.put("background_0", "background_0.png");
		textures.put("background_1", "background_1.png");
		textures.put("background_2", "background_2.png");
		textures.put("background_3", "background_3.png");
		textures.put("background_4", "background_4.png");
		textures.put("bit_0", "bit_0.png");
		textures.put("bit_1", "bit_1.png");
		textures.put("player_bullet_0", "player_bullet_0.png");
		textures.put("player_bullet_1", "player_bullet_1.png");
	}
	
	public String getTexturePath(String name) {
		if (textures.containsKey(name))
			return textures.get(name);
		return null;
	}
	
	private static class SingletonHolder {
		private static final AssetPathDictionary instance = new AssetPathDictionary();
	}
	
	public static AssetPathDictionary getInstance() {
		return SingletonHolder.instance;
	}
}
