package model;

import top.Common;

/**
 * A holdj�r�t reprezent�l� oszt�ly.
 * 
 * A l�p�s�n�l � felel�s az �tk�z�sek vizsg�lat��rt.
 */
public class Buggy implements IActive {

	/**
	 * A j�t�k, amiben a holdj�r� szerepel.
	 */
	private Game game;

	/**
	 * A f�ldszint, az �tk�z�svizsg�lathoz.
	 */
	private GroundLevel ground;

	/**
	 * A f�ldfelsz�n feletti ter�let, az �tk�z�svizsg�lathoz.
	 */
	private AboveGroundLevel aboveGround;

	/**
	 * Az ugr�sid� sz�ml�l�ja.
	 */
	private int jumping;

	/**
	 * A konstruktor.
	 * 
	 * @param game
	 *            - a j�t�k amiben a holdj�r� szerepel
	 * @param ground
	 *            - a f�ldszint
	 * @param aboveGround
	 *            - a f�ldfelsz�n feletti ter�let
	 */
	public Buggy(Game game, GroundLevel ground, AboveGroundLevel aboveGround) {
		this.game = game;
		this.ground = ground;
		this.aboveGround = aboveGround;

		jumping = 0;
	}

	/**
	 * Az ugr�st lek�rdez� f�ggv�ny.
	 * 
	 * @return igaz, ha �ppen ugrik
	 */
	public boolean isJumping() {
		return jumping != 0;
	}

	/**
	 * Az ugr�st elind�t� f�ggv�ny.
	 * 
	 * Nincs hat�sa, ha a holdj�r� m�r �ppen ugr�s k�zben van.
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
	 * Az ugr�s k�zben eltelt id�t visszaad� f�ggv�ny.
	 * 
	 * @return az ugr�s el�rehaladotts�g�t adja meg a [0, 1[ intervallumon
	 */
	public float getJumpPercentage() {
		return (float) jumping / Common.jumpTime;
	}

}
