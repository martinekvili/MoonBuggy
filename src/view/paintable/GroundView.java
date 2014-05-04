package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.GroundLevel;

/**
 * A földfelszínt megjelenítõ osztály.
 */
public class GroundView extends ViewBase {

	/**
	 * A földfelszín.
	 */
	private GroundLevel ground;

	/**
	 * Konstruktor.
	 * 
	 * @param g - a földfelszín
	 */
	public GroundView(GroundLevel g) {
		ground = g;
	}

	public void paintMe(Graphics g) {
		g.setColor(255, 255, 255);

		int deltax = (int) (ground.getMovedPercent() * width);
		for (int i = 0; i < ground.size(); i++) {
			if (ground.get(i) == GroundLevel.FLAT) {
				g.fillRect(i * width - deltax, canvas.getHeight() - 20, width,
						20);
			} else {
				g.fillRect(i * width - deltax, canvas.getHeight() - 5, width, 5);
			}
		}
	}

}
