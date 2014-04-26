package view;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class GameCanvas extends Canvas {

	private Vector views;

	public GameCanvas() {
		views = new Vector();
	}

	public void addView(ViewObject newView) {
		views.addElement(newView);
		newView.setCanvas(this);
	}

	protected void paint(Graphics g) {
		g.setColor(0, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < views.size(); i++) {
			ViewObject view = (ViewObject) views.elementAt(i);
			view.paintMe(g);
		}
	}

}
