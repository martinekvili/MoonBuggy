package model;

import top.Randomizer;

public class Ground implements ActiveObject {
	
	public static final int FLAT = 0;
	public static final int HOLE = 1;
	
	private PushableArray ground;
	
	private final int stepDivider = 20;
	private int counter;
	
	public Ground() {
		int[] initialGround = new int[20];
		for (int i = 0; i < initialGround.length; i++) {
			initialGround[i] = FLAT;
		}
		ground = new PushableArray(initialGround);
		
		counter = 0;
	}

	public void step() {
		counter++;
		if (counter == stepDivider) {
			counter = 0;
			
			int newElement = Randomizer.chanceByPercent(10) ? HOLE : FLAT;
			ground.push(newElement);
		}
	}

}
