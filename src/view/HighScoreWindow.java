package view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import top.MoonBuggy;
import top.Score;
import top.TopTenScore;

public class HighScoreWindow extends Form implements CommandListener {
	
	private Command back;
	
	private MoonBuggy midlet;
	
	public HighScoreWindow(MoonBuggy moonBuggy, TopTenScore topTen) {
		super("Top 10 Scores");
		
		midlet = moonBuggy;
		
		append("These are the highscores:\n");
		
		Score[] scores = topTen.getScores();
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] != null) {
				append((i + 1) + ": " + scores[i].name + " - " + scores[i].score + "\n");
			}
		}
		
		back = new Command("Back", Command.BACK, 0);
		addCommand(back);
		
		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable arg1) {
		if (c.equals(back)) {
			midlet.showMenu();
		}
	}

}
