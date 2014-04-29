package top;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MoonBuggy extends MIDlet {

	protected void startApp() throws MIDletStateChangeException {
		startGame();
	}
	
	private void startGame() {
		GameManager gm = new GameManager(this);
		
		Display.getDisplay(this).setCurrent(gm.getView());
		
		gm.start();
	}
	
	public void endGame() {
		Form f = new Form("Game Over");
		
		Display.getDisplay(this).setCurrent(f);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

}
