package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.Game;

/**
 * A pontszámot megjelenítõ osztály.
 */
public class PointView extends ViewBase {

	/**
	 * A játék.
	 */
	private Game game;

	/**
	 * Konstruktor.
	 * 
	 * @param g - a játék
	 */
	public PointView(Game g) {
		game = g;
	}

	public void paintMe(Graphics g) {
		g.setColor(255, 255, 0);
		g.drawString(Integer.toString(game.getPoints()), 20, 20, 0);
	}

}
