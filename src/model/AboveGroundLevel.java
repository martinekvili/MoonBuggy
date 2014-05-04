package model;

import top.Common;
import top.Randomizer;

/**
 * A f�ldfelsz�n feletti tartom�nyt reprezent�l� oszt�ly.
 * 
 * Itt helyezkednek el az akad�lyok.
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
	 * A f�ggv�ny, ami elt�ntet egy akad�lyt a p�ly�r�l.
	 * 
	 * @param place
	 *            - az elt�ntetend� akad�ly helye
	 */
	public void removeObstacle(int place) {
		environment.set(place, NOTHING);
	}

}
