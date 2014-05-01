package top;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MoonBuggy extends MIDlet implements CommandListener {
	
	private Form menu;
	
	private Command exit;
	private Command start;

	protected void startApp() throws MIDletStateChangeException {
		menu = new Form("Menu");
		
		exit = new Command("Exit", Command.EXIT, 0);
		menu.addCommand(exit);
		start = new Command("Start Game", Command.SCREEN, 0);
		menu.addCommand(start);
		
		menu.setCommandListener(this);
		
		Display.getDisplay(this).setCurrent(menu);
	}
	
	private void startGame() {
		GameManager gm = new GameManager(this);
		
		Display.getDisplay(this).setCurrent(gm.getView());
		
		gm.start();
	}
	
	public void endGame() {		
		Display.getDisplay(this).setCurrent(menu);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	public void commandAction(Command c, Displayable arg1) {
		if (c.equals(exit)) {
			try {
				destroyApp(true);
			} catch (MIDletStateChangeException e) {
				e.printStackTrace();
			}
			notifyDestroyed();
		} else if (c.equals(start)) {
			startGame();
		}
	}

}
