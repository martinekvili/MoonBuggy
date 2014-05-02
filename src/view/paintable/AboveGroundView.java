package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.AboveGround;

public class AboveGroundView extends ViewBase {
	
	private AboveGround model;
	
	public AboveGroundView (AboveGround a) {
		model = a;
	}

	public void paintMe(Graphics g) {
		g.setColor(0, 255, 255);
		
		int deltax = (int) (model.getMovedPercent() * width);
		for (int i = 0; i < model.size(); i++) {
			if (model.get(i) == AboveGround.OBSTACLE) {
				g.fillRect(i * width - deltax, canvas.getHeight() - 50, width - 1,
						20);
			}
		}
	}

}
