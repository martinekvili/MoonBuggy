package top;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import model.Game;
import view.BuggyView;
import view.GameCanvas;
import view.GroundView;
import view.PointView;

public class MoonBuggy extends MIDlet {

	private GameCanvas gc;

	protected void startApp() throws MIDletStateChangeException {
		gc = new GameCanvas();
		Game g = new Game(gc);
		
		GroundView gv = new GroundView(g.getGround());
		BuggyView bv = new BuggyView(g.getBuggy());
		PointView pv = new PointView(g);

		gc.addView(gv);
		gc.addView(bv);
		gc.addView(pv);
		gc.setBuggy(g.getBuggy());

		Display.getDisplay(this).setCurrent(gc);

		g.start();
	}

	

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

}
