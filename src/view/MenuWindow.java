package view;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import top.MoonBuggy;

public class MenuWindow extends Canvas {

	private MoonBuggy midlet;

	private String[] menuItems;

	public MenuWindow(MoonBuggy m) {
		midlet = m;

		menuItems = new String[] { "Start Game", "Tutorial", "High Scores",
				"Reset Game", "Exit" };

		setFullScreenMode(true);
	}

	protected void paint(Graphics g) {
		g.setColor(0, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(255, 255, 255);
		Font f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD,
				Font.SIZE_LARGE);
		g.setFont(f);

		for (int i = 0; i < menuItems.length; i++) {
			g.drawString(menuItems[i], getWidth() / 2, getHeight()
					/ (menuItems.length + 1) * (i + 1), Graphics.BASELINE
					| Graphics.HCENTER);
		}
	}

	protected void pointerPressed(int x, int y) {
		int place = y * (menuItems.length + 1) * 2;
		place /= getHeight();

		switch (place) {
		case 1:
		case 2:
			midlet.startGame();
			break;

		case 3:
		case 4:
			midlet.showTutorial();
			break;

		case 5:
		case 6:
			midlet.showHighScores();
			break;

		case 7:
		case 8:
			Display.getDisplay(midlet).setCurrent(new AreYouSureWindow(midlet));
			break;

		case 9:
		case 10:
			midlet.exit();
			break;
		}
	}

}