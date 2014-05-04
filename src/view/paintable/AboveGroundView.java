package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.AboveGroundLevel;

/**
 * A f�ldfelsz�n feletti r�sz megjelen�t�je.
 */
public class AboveGroundView extends ViewBase {

	/**
	 * A f�ldfelsz�n feletti r�sz.
	 */
	private AboveGroundLevel model;

	/**
	 * Konstruktor.
	 * 
	 * @param a
	 *            - a f�ldfelsz�n feletti r�sz
	 */
	public AboveGroundView(AboveGroundLevel a) {
		model = a;
	}

	public void paintMe(Graphics g) {
		g.setColor(0, 255, 255);

		int deltax = (int) (model.getMovedPercent() * width);
		for (int i = 0; i < model.size(); i++) {
			if (model.get(i) == AboveGroundLevel.OBSTACLE) {
				g.fillRect(i * width - deltax, canvas.getHeight() - 50,
						width - 1, 20);
			}
		}
	}

}
