package view;

import javax.microedition.lcdui.Graphics;

import model.Ground;

public class GroundView extends ViewObject {

	private Ground ground;

	public GroundView(Ground g) {
		ground = g;
	}

	public void paintMe(Graphics g) {
		g.setColor(255, 255, 255);

		int deltax = (int) (ground.getMovedPercent() * width);
		for (int i = 0; i < ground.size(); i++) {
			if (ground.get(i) == Ground.FLAT) {
				g.fillRect(i * width - deltax, canvas.getHeight() - 20, width,
						20);
			} else {
				g.fillRect(i * width - deltax, canvas.getHeight() - 5, width, 5);
			}
		}
	}

}
