package view;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import top.MoonBuggy;

/**
 * A tutorialt megjelenítõ képernyõ.
 */
public class TutorialVindow extends LandscapeWindowBase {

	private static final int frameNumber = 8;

	private int whichFrame;

	private MoonBuggy midlet;

	public TutorialVindow(MoonBuggy m) {
		midlet = m;

		whichFrame = 0;
	}

	protected void draw(Graphics g) {
		g.setColor(0, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());

		try {
			g.drawImage(Image.createImage("/0" + whichFrame + ".png"),
					getWidth() / 2, getHeight() / 2, Graphics.HCENTER
							| Graphics.VCENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void pointerPressed(int x, int y) {
		whichFrame++;

		if (whichFrame < frameNumber) {
			repaint();
		} else {
			midlet.showMenu();
		}
	}

}
