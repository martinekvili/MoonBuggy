package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.GroundLevel;

/**
 * A f�ldfelsz�nt megjelen�t� oszt�ly.
 */
public class GroundView extends ViewBase {

	private final int flatHeight = 40;
	private final int holeHeight = 25;

	/**
	 * A f�ldfelsz�n.
	 */
	private GroundLevel ground;

	/**
	 * Konstruktor.
	 * 
	 * @param g
	 *            - a f�ldfelsz�n
	 */
	public GroundView(GroundLevel g) {
		ground = g;
	}

	public void paintMe(Graphics g) {
		g.setColor(255, 255, 255);

		int deltax = (int) (ground.getMovedPercent() * width);
		for (int i = 0; i < ground.size(); i++) {
			if (ground.get(i) == GroundLevel.FLAT) {
				g.fillRect(i * width - deltax, canvas.getHeight() - flatHeight,
						width, flatHeight);
			} else {
				g.fillRect(i * width - deltax, canvas.getHeight() - holeHeight,
						width, holeHeight);
			}
		}
	}

}
