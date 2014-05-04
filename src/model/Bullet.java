package model;

import top.Common;

/**
 * A lövedéket reprezentáló osztály.
 */
public class Bullet implements IActive {

	/**
	 * Lépésszámláló.
	 */
	private int counter;

	/**
	 * A lövedék helye.
	 */
	private int place;

	/**
	 * A lövedék magassága.
	 */
	private final float height;

	/**
	 * A lövedéket tartalmazó játék.
	 */
	private Game game;

	/**
	 * A földfelszín feletti terület, az ütközésdetektáláshoz.
	 */
	private AboveGroundLevel aboveGround;

	/**
	 * A létezést jelzõ flag.
	 */
	private boolean exists;

	/**
	 * A konstruktor.
	 * 
	 * @param game
	 *            - a lövedéket tartalmazó játék
	 * @param aboveGround
	 *            - a földfelszín feletti terület
	 * @param place
	 *            - a lövedék kezdeti helye
	 * @param height
	 *            - a lövedék magassága
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
	 * A magasságot lekérdezõ függvény.
	 * 
	 * @return a magasság
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * A helyet lekérdezõ függvény.
	 * 
	 * @return a hely
	 */
	public int getPlace() {
		return place;
	}

	/**
	 * A számláló állapotát lekérdezõ függvény.
	 * 
	 * @return a számláló és a lépésosztó hányadosa
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
	 * Az ütközést detektáló függvény.
	 * 
	 * @return igaz, ha történt ütközés
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
	 * Lekérdezi, hogy létezik-e még a lövedék.
	 * 
	 * @return igaz, ha létezik
	 */
	public boolean isExisting() {
		return exists;
	}

}
