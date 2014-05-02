package view.paintable;

import javax.microedition.lcdui.Graphics;

import top.Common;
import view.GameWindow;

public abstract class ViewBase {
	
	protected GameWindow canvas;
	
	protected int width;

	public abstract void paintMe(Graphics g);

	public void setCanvas(GameWindow gc) {
		canvas = gc;

		width = canvas.getWidth() / (Common.arraySize - 2);
	}

}
