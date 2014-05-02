package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.Game;

public class PointView extends ViewBase {

	private Game game;

	public PointView(Game g) {
		game = g;
	}

	public void paintMe(Graphics g) {
		g.setColor(255, 255, 0);
		g.drawString(Integer.toString(game.getPoints()), 20, 20, 0);
	}

}
