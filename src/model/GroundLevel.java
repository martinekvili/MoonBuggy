package model;

import top.Common;
import top.Randomizer;

/**
 * A f�ldszintet reprezent�l� oszt�ly.
 * 
 * Itt helyezkednek el a kr�terek.
 */
public class GroundLevel extends LevelBase {

	public static final int FLAT = 0;
	public static final int HOLE = 1;

	/**
	 * Jelzi, hogy volt-e a k�zelben kr�ter.
	 */
	private int beenHole;

	public GroundLevel() {
		beenHole = 0;
	}

	protected int[] getInitialArray() {
		int[] initialGround = new int[Common.arraySize];

		for (int i = 0; i < initialGround.length; i++) {
			initialGround[i] = FLAT;
		}

		return initialGround;
	}

	protected int getNewElement() {
		int newElement;

		if (beenHole == 0) {
			newElement = Randomizer.chanceByPercent(Common.holeProbability) ? HOLE
					: FLAT;

			if (newElement == HOLE) {
				beenHole = Common.holeDistance;
			}
		} else {
			newElement = FLAT;
			beenHole--;
		}

		return newElement;
	}

	protected boolean isCollision(int where) {
		return environment.get(where) == HOLE;
	}

}