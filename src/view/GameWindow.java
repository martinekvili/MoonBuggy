package view;

import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import model.Game;
import top.GameManager;
import view.paintable.ViewBase;

/**
 * A játék megjelenítéséért felelõs képernyõ.
 */
public class GameWindow extends LandscapeWindowBase {

	/**
	 * A megjelenítendõ objektumok.
	 */
	private Vector views;

	private Vector removable;

	/**
	 * A játék.
	 */
	private Game game;

	/**
	 * A játék menedzsere.
	 */
	private GameManager manager;

	/**
	 * Konstruktor.
	 * 
	 * @param gameManager
	 *            - a játékot menedzselõ osztály
	 */
	public GameWindow(GameManager gameManager) {
		views = new Vector();
		removable = new Vector();

		manager = gameManager;
	}

	/**
	 * A játékot beállító függvény.
	 * 
	 * @param g
	 *            - a játék
	 */
	public void setGame(Game g) {
		game = g;
	}

	/**
	 * Egy új megjelenítendõ objektumot hozzáadó függvény.
	 * 
	 * @param newView
	 *            - az új megjelenítendõ objektum
	 */
	public void addView(ViewBase newView) {
		views.addElement(newView);
		newView.setCanvas(this);
	}

	/**
	 * Egy megjelenítendõ objektumot kitörlõ függvény.
	 * 
	 * @param view
	 *            - a kitörlendõ objektum
	 */
	public void removeView(ViewBase view) {
		removable.addElement(view);
	}

	protected void draw(Graphics g) {
		g.setColor(0, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(255, 255, 255);
		g.fillRect(getWidth() - 20, 10, 5, 10);
		g.fillRect(getWidth() - 13, 10, 5, 10);

		for (int i = 0; i < views.size(); i++) {
			ViewBase view = (ViewBase) views.elementAt(i);
			view.paintMe(g);
		}

		for (int i = 0; i < removable.size(); i++) {
			views.removeElement(removable.elementAt(i));
		}
		removable.removeAllElements();

		int gameState = game.getState();
		if (gameState != Game.RUNNING) {
			String string;

			switch (game.getState()) {

			case Game.OVER:
				string = new String("Game Over");
				break;

			case Game.STARTED:
				string = new String("Touch to start!");
				break;

			case Game.PAUSED:
				string = new String("Game Paused");
				break;

			default:
				string = new String("Something's seriously wrong :/");
				break;

			}

			g.setColor(255, 255, 255);
			Font f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD,
					Font.SIZE_LARGE);
			g.setFont(f);
			g.drawString(string, getWidth() / 2, getHeight() / 2,
					Graphics.BASELINE | Graphics.HCENTER);
		}
	}

	protected void pointerPressed(int x, int y) {
		switch (game.getState()) {
		case Game.OVER:
			manager.exit();
			break;

		case Game.PAUSED:
			manager.togglePause();
			break;

		case Game.STARTED:
			manager.start();
			break;

		case Game.RUNNING:
			if (x > getHeight() - 50 && y > getWidth() - 50) {
				manager.togglePause();
			} else if (x < getHeight() / 2 && y < getWidth() / 3) {
				game.setJump();
			} else if (x < getHeight() / 2 && y > 2 * getWidth() / 3) {
				game.addBullet();
			}
			break;
		}
	}

}
