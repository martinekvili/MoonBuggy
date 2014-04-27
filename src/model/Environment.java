package model;

import top.Common;

public abstract class Environment implements ActiveObject {
	
	private PushableArray environment;

	private int counter;

	public Environment() {
		environment = new PushableArray(getInitialArray());

		counter = 0;
	}
	
	protected abstract int[] getInitialArray();

	public void step() {
		counter++;
		if (counter == Common.stepDivider) {
			counter = 0;

			environment.push(getNewElement());
		}
	}
	
	protected abstract int getNewElement();

	public int get(int which) {
		return environment.get(which);
	}

	public int size() {
		return environment.size();
	}

	public float getMovedPercent() {
		return (float) counter / Common.stepDivider;
	}

}
