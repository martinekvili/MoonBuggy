package model;

import top.Common;
import top.Randomizer;

public class AboveGround extends Environment {
	
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
		return Randomizer.chanceByPercent(20) ? OBSTACLE : NOTHING;
	}
	

}
