package top;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Display;

import model.Game;
import view.GameWindow;
import view.NameInputWindow;

/**
 * A j�t�kot menedzsel� oszt�ly.
 * 
 * V�gzi a l�ptet�st, a megjelen�t� friss�t�s�t, int�zi a sz�neteltet�st �s a
 * j�t�k v�g�t.
 */
public class GameManager {

	/**
	 * Az alkalmaz�s MIDlet-e.
	 */
	private MoonBuggy midlet;

	/**
	 * A j�t�k.
	 */
	private Game game;

	/**
	 * A megjelen�t�.
	 */
	private GameWindow view;

	/**
	 * Az id�z�t�.
	 */
	private Timer timer;

	/**
	 * A sz�netel�st jelz� flag.
	 */
	private boolean isRunning;

	/**
	 * Konstruktor.
	 * 
	 * @param m
	 *            - az alkalmaz�s MIDlet-e
	 */
	public GameManager(MoonBuggy m) {
		midlet = m;

		view = new GameWindow(this);

		game = new Game(view, this);

		isRunning = false;
	}

	/**
	 * A megjelen�t�t lek�r� f�ggv�ny.
	 * 
	 * @return a megjelen�t�
	 */
	public GameWindow getView() {
		return view;
	}

	/**
	 * Sz�neteltet�st v�gz� f�ggv�ny.
	 */
	public void togglePause() {
		if (isRunning) {
			stop();
		} else {
			start();
		}
	}

	/**
	 * J�t�kot ind�t� f�ggv�ny.
	 */
	public void start() {
		timer = new Timer();
		timer.schedule(new GameStepper(), 0, Common.waitTime);

		isRunning = true;
		game.setState(Game.RUNNING);
	}

	/**
	 * J�t�kot sz�neteltet� f�ggv�ny.
	 */
	private void stop() {
		timer.cancel();

		isRunning = false;
		game.setState(Game.PAUSED);
		view.repaint();
	}

	/**
	 * J�t�kot le�ll�t� f�ggv�ny.
	 */
	public void gameOver() {
		timer.cancel();

		game.setState(Game.OVER);
		view.repaint();
	}

	/**
	 * J�t�kb�l kil�p� f�ggv�ny.
	 * 
	 * Kirakja a n�vbek�r� ablakot.
	 */
	public void exit() {
		Display.getDisplay(midlet).setCurrent(
				new NameInputWindow(this, game.getPoints()));
	}

	/**
	 * A men�be visszat�r� f�ggv�ny.
	 * 
	 * @param name
	 *            - a j�t�kos neve
	 */
	public void setName(String name) {
		midlet.endGame(new Score(name, game.getPoints()));
	}

	/**
	 * A l�ptet�st int�z� TimerTask oszt�ly.
	 */
	private class GameStepper extends TimerTask {

		public void run() {
			game.step();

			view.repaint();
		}

	}

}
