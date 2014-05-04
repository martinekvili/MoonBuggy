package view;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * A fektetett módba kirajzoló képernyõk õszosztálya.
 */
public abstract class LandscapeWindowBase extends Canvas {

	/**
	 * A kép amire rajzolunk.
	 */
	private Image screen;

	/**
	 * A forgató osztály.
	 */
	private Sprite rotater;

	/**
	 * Konstruktor.
	 */
	public LandscapeWindowBase() {
		setFullScreenMode(true);

		screen = Image.createImage(super.getHeight(), super.getWidth());

		rotater = new Sprite(screen);
		rotater.setTransform(Sprite.TRANS_ROT90);
		rotater.setPosition(0, 0);
	}

	protected final void paint(Graphics graphics) {
		Graphics g = screen.getGraphics();

		draw(g);

		rotater.setImage(screen, getWidth(), getHeight());
		rotater.paint(graphics);
	}

	/**
	 * Itt történik a rajzolás valójában.
	 * 
	 * @param g
	 *            - a Graphics objektum amire rajzolunk
	 */
	protected abstract void draw(Graphics g);

	public int getHeight() {
		return screen.getHeight();
	}

	public int getWidth() {
		return screen.getWidth();
	}

}
