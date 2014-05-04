package view;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * A fektetett m�dba kirajzol� k�perny�k �szoszt�lya.
 */
public abstract class LandscapeWindowBase extends Canvas {

	/**
	 * A k�p amire rajzolunk.
	 */
	private Image screen;

	/**
	 * A forgat� oszt�ly.
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
	 * Itt t�rt�nik a rajzol�s val�j�ban.
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
