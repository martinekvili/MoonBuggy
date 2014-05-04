package model;

import top.Common;

/**
 * A l�ved�ket reprezent�l� oszt�ly.
 */
public class Bullet implements IActive {

	/**
	 * L�p�ssz�ml�l�.
	 */
	private int counter;

	/**
	 * A l�ved�k helye.
	 */
	private int place;

	/**
	 * A l�ved�k magass�ga.
	 */
	private final float height;

	/**
	 * A l�ved�ket tartalmaz� j�t�k.
	 */
	private Game game;

	/**
	 * A f�ldfelsz�n feletti ter�let, az �tk�z�sdetekt�l�shoz.
	 */
	private AboveGroundLevel aboveGround;

	/**
	 * A l�tez�st jelz� flag.
	 */
	private boolean exists;

	/**
	 * A konstruktor.
	 * 
	 * @param game
	 *            - a l�ved�ket tartalmaz� j�t�k
	 * @param aboveGround
	 *            - a f�ldfelsz�n feletti ter�let
	 * @param place
	 *            - a l�ved�k kezdeti helye
	 * @param height
	 *            - a l�ved�k magass�ga
	 */
	public Bullet(Game game, AboveGroundLevel aboveGround, int place,
			float height) {
		this.game = game;
		this.aboveGround = aboveGround;

		this.place = place;
		this.height = height;

		exists = true;

		counter = 0;
	}

	/**
	 * A magass�got lek�rdez� f�ggv�ny.
	 * 
	 * @return a magass�g
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * A helyet lek�rdez� f�ggv�ny.
	 * 
	 * @return a hely
	 */
	public int getPlace() {
		return place;
	}

	/**
	 * A sz�ml�l� �llapot�t lek�rdez� f�ggv�ny.
	 * 
	 * @return a sz�ml�l� �s a l�p�soszt� h�nyadosa
	 */
	public float getMovedPercent() {
		return (float) counter / Common.bulletStepDivider;
	}

	public void step() {
		counter++;

		if (isCollision()) {
			return;
		}

		if (counter == Common.bulletStepDivider) {
			counter = 0;

			place++;

			if (place == (Common.placeOnGround + Common.maxDistance)) {
				game.removeBullet(this);
				exists = false;
				return;
			}

			isCollision();
		}

	}

	/**
	 * Az �tk�z�st detekt�l� f�ggv�ny.
	 * 
	 * @return igaz, ha t�rt�nt �tk�z�s
	 */
	private boolean isCollision() {
		if (aboveGround.isCollision(place)) {
			aboveGround.removeObstacle(place);

			game.removeBullet(this);
			exists = false;

			return true;
		}
		if (aboveGround.isCollision(place + 1)
				&& (aboveGround.getMovedPercent() + getMovedPercent() >= 1)) {
			aboveGround.removeObstacle(place + 1);

			game.removeBullet(this);
			exists = false;

			return true;
		}

		return false;
	}

	/**
	 * Lek�rdezi, hogy l�tezik-e m�g a l�ved�k.
	 * 
	 * @return igaz, ha l�tezik
	 */
	public boolean isExisting() {
		return exists;
	}

}
