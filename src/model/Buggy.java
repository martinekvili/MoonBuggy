package model;

import top.Common;

public class Buggy implements ActiveObject {

	private Game game;

	private Ground ground;
	private AboveGround aboveGround;

	private int jumping;

	public Buggy(Game game, Ground ground, AboveGround aboveGround) {
		this.game = game;
		this.ground = ground;
		this.aboveGround = aboveGround;

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
		} else {
			if ((ground.isCollision(Common.placeOnGround) && ground
					.getMovedPercent() > 0.1)
					|| (ground.isCollision(Common.placeOnGround - 1) && ground
							.getMovedPercent() < 0.1)) {
				game.gameOver();
			}
		}

		if (aboveGround.isCollision(Common.placeOnGround)) {
			game.gameOver();
		}
	}

	public float getJumpPercentage() {
		return (float) jumping / Common.jumpTime;
	}

}
