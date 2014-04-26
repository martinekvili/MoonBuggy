package model;

import top.Randomizer;

public class Ground implements ActiveObject {

	public static final int FLAT = 0;
	public static final int HOLE = 1;

	private static final int arraySize = 20;

	private PushableArray ground;

	private static final int stepDivider = 10;
	private int counter;

	private int beenHole;

	public Ground() {
		int[] initialGround = new int[arraySize];
		for (int i = 0; i < initialGround.length; i++) {
			initialGround[i] = FLAT;
		}
		ground = new PushableArray(initialGround);

		counter = 0;
		beenHole = 0;
	}

	public void step() {
		counter++;
		if (counter == stepDivider) {
			counter = 0;

			int newElement;

			if (beenHole == 0) {
				newElement = Randomizer.chanceByPercent(30) ? HOLE : FLAT;

				if (newElement == HOLE) {
					beenHole = 2;
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
		return (float) counter / stepDivider;
	}

}
