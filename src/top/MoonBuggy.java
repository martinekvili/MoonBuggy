package top;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import model.Game;
import view.AboveGroundView;
import view.BuggyView;
import view.GameCanvas;
import view.GroundView;
import view.PointView;

public class MoonBuggy extends MIDlet {

	private GameCanvas gc;

	protected void startApp() throws MIDletStateChangeException {
		gc = new GameCanvas();
		Game g = new Game(gc);
		gc.setGame(g);

		GroundView gv = new GroundView(g.getGround());
		AboveGroundView av = new AboveGroundView(g.getAboveGround());
		BuggyView bv = new BuggyView(g.getBuggy());
		PointView pv = new PointView(g);

		gc.addView(gv);
		gc.addView(bv);
		gc.addView(pv);
		gc.addView(av);

		Display.getDisplay(this).setCurrent(gc);

		g.start();
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

}
