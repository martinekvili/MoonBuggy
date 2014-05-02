package view.paintable;

import javax.microedition.lcdui.Graphics;

import model.Buggy;
import top.Common;

public class BuggyView extends ViewBase {

	private final int length = 15;

	private Buggy buggy;

	public BuggyView(Buggy b) {
		buggy = b;
	}

	public void paintMe(Graphics g) {
		g.setColor(0, 255, 0);

		float percent = buggy.getJumpPercentage();
		if (percent > 0.5) {
			percent = 1 - percent;
		}

		int height = (int) (40 * percent);

		g.fillRect(Common.placeOnGround * width - length, canvas.getHeight()
				- 35 - height, length, 10);
	}

}
