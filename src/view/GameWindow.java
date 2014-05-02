package view;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import model.Game;
import top.GameManager;
import view.paintable.ViewBase;

public class GameWindow extends Canvas {

	private Vector views;
	private Vector removable;

	private Game game;

	private GameManager manager;

	private Image screen;
	private Sprite rotater;

	public GameWindow(GameManager gameManager) {
		setFullScreenMode(true);

		views = new Vector();
		removable = new Vector();

		manager = gameManager;

		screen = Image.createImage(super.getHeight(), super.getWidth());

		rotater = new Sprite(screen);
		rotater.setTransform(Sprite.TRANS_ROT90);
		rotater.setPosition(0, 0);
	}

	public void setGame(Game g) {
		game = g;
	}

	public void addView(ViewBase newView) {
		views.addElement(newView);
		newView.setCanvas(this);
	}

	public void removeView(ViewBase view) {
		removable.addElement(view);
	}

	protected void paint(Graphics graphics) {
		Graphics g = screen.getGraphics();

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
		

		rotater.setImage(screen, getWidth(), getHeight());
		rotater.paint(graphics);
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

	public int getHeight() {
		return screen.getHeight();
	}

	public int getWidth() {
		return screen.getWidth();
	}

}