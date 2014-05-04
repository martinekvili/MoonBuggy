package view.paintable;

import javax.microedition.lcdui.Graphics;

import top.Common;
import view.GameWindow;

/**
 * A megjelen�t�ket �sszefog� �szoszt�ly.
 */
public abstract class ViewBase {

	/**
	 * A j�t�k megjelen�t�je.
	 */
	protected GameWindow canvas;

	/**
	 * Az egys�g sz�less�g.
	 */
	protected int width;

	/**
	 * A kirajzol� f�ggv�ny.
	 * 
	 * @param g
	 *            - a Graphics objektum amire rajzolunk
	 */
	public abstract void paintMe(Graphics g);

	/**
	 * A j�t�k megjelen�t�j�t be�ll�t� f�ggv�ny.
	 * 
	 * @param gw
	 *            - a j�t�k megjelen�t�je
	 */
	public void setCanvas(GameWindow gw) {
		canvas = gw;

		width = canvas.getWidth() / (Common.arraySize - 1);
	}

}
