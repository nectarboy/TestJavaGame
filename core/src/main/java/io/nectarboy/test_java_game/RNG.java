package io.nectarboy.test_java_game;

public class RNG {
	static int randomState;
	
	static public void seed(int seed) {
		randomState = seed;
	}
	
	static private int calcRandom() {
		randomState = (0xffffffff & (randomState * 1664525 + 1013904223)) >>> 0;
        return randomState;
    }
	
	static public int getRandomInt(int min, int max) {
		int maxOffset = max - min + 1;
		return min + ((calcRandom() % maxOffset) % maxOffset);
	}
	
	static public float randomFloat() {
		int range = calcRandom();
		if (range < 0)
			return -(float)range / (float)0x7fffffff * 0.5f;
		else
			return (float)range / (float)0x7fffffff * 0.5f + 0.5f;
	}
}
