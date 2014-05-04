package view.paintable;

import model.AboveGroundLevel;
import model.Buggy;
import model.Bullet;
import model.Game;
import model.GroundLevel;

/**
 * A megfelel� megjelen�t�ket el��ll�t� oszt�ly.
 */
public class ViewFactory {

	public static ViewBase createView(Buggy b) {
		return new BuggyView(b);
	}

	public static ViewBase createView(GroundLevel g) {
		return new GroundView(g);
	}

	public static ViewBase createView(AboveGroundLevel a) {
		return new AboveGroundView(a);
	}

	public static ViewBase createView(Bullet b) {
		return new BulletView(b);
	}

	public static ViewBase createView(Game g) {
		return new PointView(g);
	}

}
