package model;

import java.util.Vector;

import top.Common;
import top.GameManager;
import view.GameWindow;
import view.paintable.ViewFactory;

/**
 * A j�t�kot reprezent�l� oszt�ly.
 */
public class Game {

	public static final int STARTED = 0;
	public static final int RUNNING = 1;
	public static final int PAUSED = 2;
	public static final int OVER = 3;

	/**
	 * A j�t�k �llapota.
	 */
	private int state;

	/**
	 * A holdj�r�.
	 */
	private Buggy buggy;

	/**
	 * A f�ldfelsz�n feletti ter�let.
	 */
	private AboveGroundLevel aboveGround;

	/**
	 * Az akt�v j�t�kbeli objektumokat tartalmaz� adatszerkezet.
	 */
	private Vector actives;

	private Vector removables;

	/**
	 * A j�t�kot menedzsel� oszt�ly.
	 */
	private GameManager manager;

	/**
	 * A j�t�kot megjelen�t� oszt�ly.
	 */
	private GameWindow view;

	/**
	 * A pontok sz�ma.
	 */
	private int points;

	/**
	 * A konstruktor.
	 * 
	 * @param gw
	 *            - a j�t�kot megjelen�t� oszt�ly
	 * @param m
	 *            - a j�t�kot menedzsel� oszt�ly
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
	 * Az �llapot be�ll�t�s�ra szolg�l� f�ggv�ny.
	 * 
	 * @param newState
	 *            - az �j �llapot
	 */
	public void setState(int newState) {
		state = newState;
	}

	/**
	 * Az �llapotot lek�rdez� f�ggv�ny.
	 * 
	 * @return az �llapot
	 */
	public int getState() {
		return state;
	}

	/**
	 * A pontok sz�m�t lek�rdez� f�ggv�ny.
	 * 
	 * @return a pontok sz�ma
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * A holdj�r� ugr�s�t elind�t� f�ggv�ny.
	 */
	public void setJump() {
		buggy.setJump();
	}

	/**
	 * Az �j l�ved�ket hozz�ad� f�ggv�ny.
	 */
	public void addBullet() {
		Bullet newBullet = new Bullet(this, aboveGround, Common.placeOnGround,
				buggy.getJumpPercentage());

		actives.addElement(newBullet);

		view.addView(ViewFactory.createView(newBullet));
	}

	/**
	 * Az elhaszn�lt l�ved�ket kit�rl� f�ggv�ny.
	 * 
	 * @param bullet
	 *            - az elhaszn�lt l�ved�k
	 */
	public void removeBullet(Bullet bullet) {
		removables.addElement(bullet);
	}

	/**
	 * A l�ptet� f�ggv�ny.
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
	 * A j�t�k v�g�n h�vand� f�ggv�ny.
	 */
	public void gameOver() {
		manager.gameOver();
	}

}
