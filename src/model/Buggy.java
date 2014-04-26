package model;

import top.Common;

public class Buggy implements ActiveObject {

	private int jumping;

	public Buggy() {
		jumping = 0;
	}

	public boolean isJumping() {
		return jumping != 0;
	}

	public void setJump() {
		if (jumping == 0) {
			jumping = Common.jumpTime;
		}
	}

	public void step() {
		if (jumping > 0) {
			jumping--;
		}
	}

	public float getJumpPercentage() {
		return (float) jumping / Common.jumpTime;
	}

}
