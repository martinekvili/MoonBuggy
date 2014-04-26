package top;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import model.Game;
import view.BuggyView;
import view.GameCanvas;
import view.GroundView;

public class MoonBuggy extends MIDlet {

	private GameCanvas gc;

	protected void startApp() throws MIDletStateChangeException {
		gc = new GameCanvas();
		Game g = new Game(gc);
		
		GroundView gv = new GroundView(g.getGround());
		BuggyView bv = new BuggyView(g.getBuggy());

		gc.addView(gv);
		gc.addView(bv);
		gc.setBuggy(g.getBuggy());

		Display.getDisplay(this).setCurrent(gc);

		g.start();
	}

	

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

}
