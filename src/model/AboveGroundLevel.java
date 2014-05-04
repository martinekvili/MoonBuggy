package model;

import top.Common;
import top.Randomizer;

/**
 * A földfelszín feletti tartományt reprezentáló osztály.
 * 
 * Itt helyezkednek el az akadályok.
 */
public class AboveGroundLevel extends LevelBase {

	public static final int NOTHING = 0;
	public static final int OBSTACLE = 1;

	protected int[] getInitialArray() {
		int[] initialAbove = new int[Common.arraySize];

		for (int i = 0; i < initialAbove.length; i++) {
			initialAbove[i] = NOTHING;
		}

		return initialAbove;
	}

	protected int getNewElement() {
		return Randomizer.chanceByPercent(Common.obstacleProbability) ? OBSTACLE
				: NOTHING;
	}

	protected boolean isCollision(int where) {
		return environment.get(where) != NOTHING;
	}

	/**
	 * A függvény, ami eltüntet egy akadályt a pályáról.
	 * 
	 * @param place
	 *            - az eltüntetendõ akadály helye
	 */
	public void removeObstacle(int place) {
		environment.set(place, NOTHING);
	}

}
