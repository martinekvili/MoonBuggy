package top;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;

import view.HighScoreWindow;
import view.MenuWindow;

public class MoonBuggy extends MIDlet {

	private TopTenScore highscores;

	protected void startApp() throws MIDletStateChangeException {
		RecordStore scoreStore = null;
		try {
			scoreStore = RecordStore.openRecordStore("ScoreStore", false);
		} catch (Exception e) {
		}
		
		if (scoreStore == null) {
			highscores = new TopTenScore();
		}
		else {
			System.out.println("siker");
			highscores = TopTenScore.load(scoreStore);
			try {
				scoreStore.closeRecordStore();
			} catch (Exception e) {
			}
		}

		showMenu();
	}

	public void startGame() {
		GameManager gm = new GameManager(this);

		Display.getDisplay(this).setCurrent(gm.getView());
	}

	public void endGame(Score score) {
		highscores.addScore(score);
		
		Score temp = new Score(score.getName(), score.getScore());
		System.out.println(temp.name);
		System.out.println(temp.score);

		showMenu();
	}

	public void showHighScores() {
		Display.getDisplay(this).setCurrent(
				new HighScoreWindow(this, highscores));
	}

	public void showMenu() {
		Display.getDisplay(this).setCurrent(new MenuWindow(this));
	}
	
	public void exit() {
		try {
			destroyApp(true);
		} catch (MIDletStateChangeException e) {
			e.printStackTrace();
		}
		notifyDestroyed();
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		try {
			RecordStore.deleteRecordStore("ScoreStore");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		RecordStore scoreStore = null;
		try {
			scoreStore = RecordStore.openRecordStore("ScoreStore", true);
			highscores.save(scoreStore);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scoreStore != null) {
				try {
					scoreStore.closeRecordStore();
				} catch (Exception e) {
				}
			}	
		}
	}
	
	public void clearHighscores() {
		highscores = new TopTenScore();
	}

	protected void pauseApp() {
	}

}
