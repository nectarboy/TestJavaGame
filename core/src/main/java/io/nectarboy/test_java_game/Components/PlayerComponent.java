package io.nectarboy.test_java_game.Components;

import java.util.ArrayList;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.math.Vector2;

public class PlayerComponent implements Component, Poolable {
	public enum State {
		NORMAL
	};
	
	public State state = State.NORMAL;
	
	public static class GunConfig {
		public int minimumAmmo = 0;
		public int bulletCooldown = 0;
		public int bulletLevel = 0;
		public int bulletCount = 0;
		public int ammoUsage;
		
		public GunConfig(int minimumAmmo, int bulletCooldown, int bulletLevel, int bulletCount, int ammoUsage) {
			this.minimumAmmo = minimumAmmo;
			this.bulletCooldown = bulletCooldown;
			this.bulletLevel = bulletLevel;
			this.bulletCount = bulletCount;
			this.ammoUsage = ammoUsage;
		}
	}
	
	public ArrayList<GunConfig> gunConfigs = new ArrayList<>();
	 
	public int health = 0;
	public int ammo = 0;
	public int bulletCooldownTicks = 0;
	
	public int pendingByte = 0;
	public int currentBit = 0;
	 
	public GunConfig getCurrentGunConfig() {
		GunConfig best = null;
		for (int i = 0; i < gunConfigs.size(); i++) {
			GunConfig config = gunConfigs.get(i);
			if (ammo >= config.minimumAmmo)
				best = config;
		}
		return best;
	}
	
	public void addBit(BitComponent.Type bitType) {
		if (currentBit == 7) {
			ammo += pendingByte;
			currentBit = 0;		
			pendingByte = 0;
		}
		int bit = bitType == BitComponent.Type.ONE ? 1 << currentBit : 0;
		pendingByte += bit;
		currentBit++;
	}
	
	@Override
	public void reset() {
		state = State.NORMAL;
		gunConfigs.clear();
		health = 0;
		ammo = 0;
		bulletCooldownTicks = 0;
		pendingByte = 0;
		currentBit = 0;
	}
}
