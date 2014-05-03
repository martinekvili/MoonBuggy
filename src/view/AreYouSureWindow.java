package view;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import top.MoonBuggy;

public class AreYouSureWindow extends Alert implements CommandListener {
	
	private Command ok;
	private Command back;
	
	private MoonBuggy midlet;
	
	public AreYouSureWindow(MoonBuggy m) {
		super("Attention", "Are you sure you want to reset the game?", null, AlertType.WARNING);
		
		midlet = m;
		
		ok = new Command("Yes", Command.OK, 0);
		addCommand(ok);
		back = new Command("No", Command.BACK, 0);
		addCommand(back);
		
		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable arg1) {
		if (c.equals(ok)) {
			midlet.resetGame();
		} else if (c.equals(back)) {
			midlet.showMenu();
		}
		
	}

}
