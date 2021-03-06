package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.Bullet;

/**
 * A l�ved�ket megjelen�t� oszt�ly.
 */
public class BulletView extends ViewBase {

	private final int length = 5;

	/**
	 * A l�ved�k.
	 */
	private Bullet bullet;

	/**
	 * Konstruktor.
	 * 
	 * @param b
	 *            - a l�ved�k
	 */
	public BulletView(Bullet b) {
		bullet = b;
	}

	public void paintMe(Graphics g) {
		if (!bullet.isExisting()) {
			canvas.removeView(this);
			return;
		}

		g.setColor(255, 0, 0);

		float percent = bullet.getHeight();
		if (percent > 0.5) {
			percent = 1 - percent;
		}

		int height = (int) (40 * percent) - 5;

		int x = bullet.getPlace() * width;
		float deltax = width * bullet.getMovedPercent();
		x += (int) deltax;

		int y = canvas.getHeight() - 55 - height;

		g.drawLine(x - length, y, x, y);
	}

}
