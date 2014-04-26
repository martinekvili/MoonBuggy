package model;

import top.Common;
import top.Randomizer;

public class Ground implements ActiveObject {

	public static final int FLAT = 0;
	public static final int HOLE = 1;

	private PushableArray ground;

	private int counter;

	private int beenHole;

	public Ground() {
		int[] initialGround = new int[Common.arraySize];
		for (int i = 0; i < initialGround.length; i++) {
			initialGround[i] = FLAT;
		}
		ground = new PushableArray(initialGround);

		counter = 0;
		beenHole = 0;
	}

	public void step() {
		counter++;
		if (counter == Common.stepDivider) {
			counter = 0;

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

			ground.push(newElement);
		}
	}

	public int get(int which) {
		return ground.get(which);
	}

	public int size() {
		return ground.size();
	}

	public float getMovedPercent() {
		return (float) counter / Common.stepDivider;
	}

}
