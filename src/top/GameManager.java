package top;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Display;

import model.Game;
import view.GameWindow;
import view.NameInputWindow;

/**
 * A játékot menedzselõ osztály.
 * 
 * Végzi a léptetést, a megjelenítõ frissítését, intézi a szüneteltetést és a
 * játék végét.
 */
public class GameManager {

	/**
	 * Az alkalmazás MIDlet-e.
	 */
	private MoonBuggy midlet;

	/**
	 * A játék.
	 */
	private Game game;

	/**
	 * A megjelenítõ.
	 */
	private GameWindow view;

	/**
	 * Az idõzítõ.
	 */
	private Timer timer;

	/**
	 * A szünetelést jelzõ flag.
	 */
	private boolean isRunning;

	/**
	 * Konstruktor.
	 * 
	 * @param m
	 *            - az alkalmazás MIDlet-e
	 */
	public GameManager(MoonBuggy m) {
		midlet = m;

		view = new GameWindow(this);

		game = new Game(view, this);

		isRunning = false;
	}

	/**
	 * A megjelenítõt lekérõ függvény.
	 * 
	 * @return a megjelenítõ
	 */
	public GameWindow getView() {
		return view;
	}

	/**
	 * Szüneteltetést végzõ függvény.
	 */
	public void togglePause() {
		if (isRunning) {
			stop();
		} else {
			start();
		}
	}

	/**
	 * Játékot indító függvény.
	 */
	public void start() {
		timer = new Timer();
		timer.schedule(new GameStepper(), 0, Common.waitTime);

		isRunning = true;
		game.setState(Game.RUNNING);
	}

	/**
	 * Játékot szüneteltetõ függvény.
	 */
	private void stop() {
		timer.cancel();

		isRunning = false;
		game.setState(Game.PAUSED);
		view.repaint();
	}

	/**
	 * Játékot leállító függvény.
	 */
	public void gameOver() {
		timer.cancel();

		game.setState(Game.OVER);
		view.repaint();
	}

	/**
	 * Játékból kilépõ függvény.
	 * 
	 * Kirakja a névbekérõ ablakot.
	 */
	public void exit() {
		Display.getDisplay(midlet).setCurrent(
				new NameInputWindow(this, game.getPoints()));
	}

	/**
	 * A menübe visszatérõ függvény.
	 * 
	 * @param name
	 *            - a játékos neve
	 */
	public void setName(String name) {
		midlet.endGame(new Score(name, game.getPoints()));
	}

	/**
	 * A léptetést intézõ TimerTask osztály.
	 */
	private class GameStepper extends TimerTask {

		public void run() {
			game.step();

			view.repaint();
		}

	}

}
