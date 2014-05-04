package view.paintable;

import javax.microedition.lcdui.Graphics;

import top.Common;
import view.GameWindow;

/**
 * A megjelenítõket összefogó õszosztály.
 */
public abstract class ViewBase {

	/**
	 * A játék megjelenítõje.
	 */
	protected GameWindow canvas;

	/**
	 * Az egység szélesség.
	 */
	protected int width;

	/**
	 * A kirajzoló függvény.
	 * 
	 * @param g
	 *            - a Graphics objektum amire rajzolunk
	 */
	public abstract void paintMe(Graphics g);

	/**
	 * A játék megjelenítõjét beállítõ függvény.
	 * 
	 * @param gw
	 *            - a játék megjelenítõje
	 */
	public void setCanvas(GameWindow gw) {
		canvas = gw;

		width = canvas.getWidth() / (Common.arraySize - 1);
	}

}
