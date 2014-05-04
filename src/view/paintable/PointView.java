package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.Game;

/**
 * A pontsz�mot megjelen�t� oszt�ly.
 */
public class PointView extends ViewBase {

	/**
	 * A j�t�k.
	 */
	private Game game;

	/**
	 * Konstruktor.
	 * 
	 * @param g - a j�t�k
	 */
	public PointView(Game g) {
		game = g;
	}

	public void paintMe(Graphics g) {
		g.setColor(255, 255, 0);
		g.drawString(Integer.toString(game.getPoints()), 20, 20, 0);
	}

}
