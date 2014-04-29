package view;

import javax.microedition.lcdui.Graphics;

import top.Common;

public abstract class ViewObject {
	
	protected GameCanvas canvas;
	
	protected int width;

	public abstract void paintMe(Graphics g);

	public void setCanvas(GameCanvas gc) {
		canvas = gc;

		width = canvas.getWidth() / (Common.arraySize - 2);
	}

}
