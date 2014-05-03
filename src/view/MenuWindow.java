package view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import top.MoonBuggy;

public class MenuWindow extends Form implements CommandListener {
	
	private Command exit;
	private Command start;
	private Command tutorial;
	private Command highScores;
	private Command resetGame;
	
	private MoonBuggy midlet;
	
	public MenuWindow(MoonBuggy moonBuggy) {
		super("Menu");
		
		midlet = moonBuggy;
		
		exit = new Command("Exit", Command.EXIT, 0);
		addCommand(exit);
		start = new Command("Start Game", Command.SCREEN, 0);
		addCommand(start);
		highScores = new Command("Highscores", Command.SCREEN, 1);
		addCommand(highScores);
		resetGame = new Command("Reset Game", Command.SCREEN, 3);
		addCommand(resetGame);
		tutorial = new Command("Tutorial", Command.SCREEN, 2);
		addCommand(tutorial);

		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable arg1) {
		if (c.equals(exit)) {
			midlet.exit();
		} else if (c.equals(start)) {
			midlet.startGame();
		} else if (c.equals(highScores)) {
			midlet.showHighScores();
		} else if (c.equals(resetGame)) {
			Display.getDisplay(midlet).setCurrent(new AreYouSureWindow(midlet));
		} else if (c.equals(tutorial)) {
			midlet.showTutorial();
		}
	}

}
