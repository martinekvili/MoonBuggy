package model;

import top.Common;

/**
 * A holdjárót reprezentáló osztály.
 * 
 * A lépésénél õ felelõs az ütközések vizsgálatáért.
 */
public class Buggy implements IActive {

	/**
	 * A játék, amiben a holdjáró szerepel.
	 */
	private Game game;

	/**
	 * A földszint, az ütközésvizsgálathoz.
	 */
	private GroundLevel ground;

	/**
	 * A földfelszín feletti terület, az ütközésvizsgálathoz.
	 */
	private AboveGroundLevel aboveGround;

	/**
	 * Az ugrásidõ számlálója.
	 */
	private int jumping;

	/**
	 * A konstruktor.
	 * 
	 * @param game
	 *            - a játék amiben a holdjáró szerepel
	 * @param ground
	 *            - a földszint
	 * @param aboveGround
	 *            - a földfelszín feletti terület
	 */
	public Buggy(Game game, GroundLevel ground, AboveGroundLevel aboveGround) {
		this.game = game;
		this.ground = ground;
		this.aboveGround = aboveGround;

		jumping = 0;
	}

	/**
	 * Az ugrást lekérdezõ függvény.
	 * 
	 * @return igaz, ha éppen ugrik
	 */
	public boolean isJumping() {
		return jumping != 0;
	}

	/**
	 * Az ugrást elindító függvény.
	 * 
	 * Nincs hatása, ha a holdjáró már éppen ugrás közben van.
	 */
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
					.getMovedPercent() > Common.distancePercent)
					|| (ground.isCollision(Common.placeOnGround - 1) && ground
							.getMovedPercent() < Common.distancePercent)) {
				game.gameOver();
			}
		}

		if (aboveGround.isCollision(Common.placeOnGround)) {
			game.gameOver();
		}
	}

	/**
	 * Az ugrás közben eltelt idõt visszaadó függvény.
	 * 
	 * @return az ugrás elõrehaladottságát adja meg a [0, 1[ intervallumon
	 */
	public float getJumpPercentage() {
		return (float) jumping / Common.jumpTime;
	}

}
