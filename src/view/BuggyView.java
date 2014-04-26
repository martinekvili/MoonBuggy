package view;

import javax.microedition.lcdui.Graphics;

import top.Common;

import model.Buggy;

public class BuggyView implements ViewObject {
	
	private GameCanvas canvas;
	
	private int width;

	private Buggy buggy;
	
	public BuggyView(Buggy b) {
		buggy = b;
	}

	public void paintMe(Graphics g) {
		g.setColor(0, 255, 0);
		
		int height = (int) ( 20 * buggy.getJumpPercentage());
		
		g.fillRect(( Common.placeOnGround - 1 ) * width, canvas.getHeight() - 30 - height, width, 10);
	}

	public void setCanvas(GameCanvas gc) {
		canvas = gc;
		
		width = canvas.getWidth() / (Common.arraySize - 2);
	}

}