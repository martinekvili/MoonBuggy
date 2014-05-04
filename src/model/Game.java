package model;

import java.util.Vector;

import top.Common;
import top.GameManager;
import view.GameWindow;
import view.paintable.ViewFactory;

/**
 * A játékot reprezentáló osztály.
 */
public class Game {

	public static final int STARTED = 0;
	public static final int RUNNING = 1;
	public static final int PAUSED = 2;
	public static final int OVER = 3;

	/**
	 * A játék állapota.
	 */
	private int state;

	/**
	 * A holdjáró.
	 */
	private Buggy buggy;

	/**
	 * A földfelszín feletti terület.
	 */
	private AboveGroundLevel aboveGround;

	/**
	 * Az aktív játékbeli objektumokat tartalmazó adatszerkezet.
	 */
	private Vector actives;

	private Vector removables;

	/**
	 * A játékot menedzselõ osztály.
	 */
	private GameManager manager;

	/**
	 * A játékot megjelenító osztály.
	 */
	private GameWindow view;

	/**
	 * A pontok száma.
	 */
	private int points;

	/**
	 * A konstruktor.
	 * 
	 * @param gw
	 *            - a játékot megjelenítõ osztály
	 * @param m
	 *            - a játékot menedzselõ osztály
	 */
	public Game(GameWindow gw, GameManager m) {
		manager = m;

		actives = new Vector();
		removables = new Vector();

		GroundLevel ground = new GroundLevel();
		actives.addElement(ground);

		aboveGround = new AboveGroundLevel();
		actives.addElement(aboveGround);

		buggy = new Buggy(this, ground, aboveGround);
		actives.addElement(buggy);

		view = gw;
		view.setGame(this);
		view.addView(ViewFactory.createView(ground));
		view.addView(ViewFactory.createView(aboveGround));
		view.addView(ViewFactory.createView(buggy));
		view.addView(ViewFactory.createView(this));

		points = 0;

		state = STARTED;
	}

	/**
	 * Az állapot beállítására szolgáló függvény.
	 * 
	 * @param newState
	 *            - az új állapot
	 */
	public void setState(int newState) {
		state = newState;
	}

	/**
	 * Az állapotot lekérdezõ függvény.
	 * 
	 * @return az állapot
	 */
	public int getState() {
		return state;
	}

	/**
	 * A pontok számát lekérdezõ függvény.
	 * 
	 * @return a pontok száma
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * A holdjáró ugrását elindító függvény.
	 */
	public void setJump() {
		buggy.setJump();
	}

	/**
	 * Az új lövedéket hozzáadó függvény.
	 */
	public void addBullet() {
		Bullet newBullet = new Bullet(this, aboveGround, Common.placeOnGround,
				buggy.getJumpPercentage());

		actives.addElement(newBullet);

		view.addView(ViewFactory.createView(newBullet));
	}

	/**
	 * Az elhasznált lövedéket kitörlõ függvény.
	 * 
	 * @param bullet
	 *            - az elhasznált lövedék
	 */
	public void removeBullet(Bullet bullet) {
		removables.addElement(bullet);
	}

	/**
	 * A léptetõ függvény.
	 */
	public void step() {
		points++;

		for (int i = 0; i < actives.size(); i++) {
			((IActive) actives.elementAt(i)).step();
		}

		for (int i = 0; i < removables.size(); i++) {
			actives.removeElement(removables.elementAt(i));
		}
		removables.removeAllElements();
	}

	/**
	 * A játék végén hívandó függvény.
	 */
	public void gameOver() {
		manager.gameOver();
	}

}
