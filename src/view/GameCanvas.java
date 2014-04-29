package view;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import top.GameManager;

import model.Game;

public class GameCanvas extends Canvas {

	private Vector views;

	private Game game;
	
	private GameManager manager;

	public GameCanvas(GameManager gameManager) {
		views = new Vector();
		
		manager = gameManager;
	}

	public void setGame(Game g) {
		game = g;
	}

	public void addView(ViewObject newView) {
		views.addElement(newView);
		newView.setCanvas(this);
	}
	
	public void removeView(ViewObject view) {
		views.removeElement(view);
	}

	protected void paint(Graphics g) {
		g.setColor(0, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(255, 255, 255);
		g.fillRect(getWidth() - 20, 10, 5, 10);
		g.fillRect(getWidth() - 13, 10, 5, 10);

		for (int i = views.size() - 1; i >= 0; i--) {
			ViewObject view = (ViewObject) views.elementAt(i);
			view.paintMe(g);
		}
	}

	protected void pointerPressed(int x, int y) {
		if (x > getWidth() - 50 && y < 50) {
			manager.togglePause();
		}
		else {
			game.setJump();
			
			game.addBullet();
		}
	}

}
