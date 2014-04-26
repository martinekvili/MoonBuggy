package top;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import model.Ground;
import view.GameCanvas;
import view.GroundView;

public class MoonBuggy extends MIDlet {

	private Ground g;
	private GameCanvas gc;

	protected void startApp() throws MIDletStateChangeException {
		g = new Ground();
		gc = new GameCanvas();
		GroundView gv = new GroundView(g);

		gc.addView(gv);

		Display.getDisplay(this).setCurrent(gc);

		Timer t = new Timer();
		t.schedule(new MyTimerTask(), 0, 50);
	}

	private class MyTimerTask extends TimerTask {

		public void run() {
			g.step();
			gc.repaint();
		}

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

}
