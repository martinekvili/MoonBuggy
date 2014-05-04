package top;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;

import view.HighScoreWindow;
import view.MenuWindow;
import view.TutorialVindow;

/**
 * Az alkalmazás MIDlet-e.
 */
public class MoonBuggy extends MIDlet {

	/**
	 * A dicsõséglista.
	 */
	private HighScores highscores;

	protected void startApp() throws MIDletStateChangeException {
		highscores = HighScores.load();

		initStates();

		showMenu();
	}

	/**
	 * A játékállapotot betöltõ függvény.
	 */
	private void initStates() {
		RecordStore states = null;

		try {
			states = RecordStore.openRecordStore("StatesStore", true);

			if (states.getNumRecords() < 2) {
				states.addRecord(Common.lastName.getBytes(), 0,
						Common.lastName.getBytes().length);

				byte[] tmp = new byte[1];
				tmp[0] = Common.firstGame;
				states.addRecord(tmp, 0, tmp.length);
			} else {
				Common.lastName = new String(states.getRecord(1));
				Common.firstGame = states.getRecord(2)[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (states != null) {
				try {
					states.closeRecordStore();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * A játékot indító függvény.
	 */
	public void startGame() {
		if (Common.isFirst()) {
			Common.firstGame = 0;

			Alert info = new Alert(
					"Info",
					"Because this is your first game, let me show you the tutorial!",
					null, AlertType.INFO);
			info.setTimeout(1000);

			Display.getDisplay(this).setCurrent(info, new TutorialVindow(this));
		} else {
			GameManager gm = new GameManager(this);

			Display.getDisplay(this).setCurrent(gm.getView());
		}
	}

	/**
	 * A játék végén lefutó függvény.
	 * 
	 * Hozzáadja a pontszámot a dicsõséglistához.
	 * 
	 * @param score
	 *            - az elért pontszám
	 */
	public void endGame(Score score) {
		highscores.addScore(score);

		showMenu();
	}

	/**
	 * A tutorialt kirakó függvény.
	 */
	public void showTutorial() {
		Common.firstGame = 0;

		Display.getDisplay(this).setCurrent(new TutorialVindow(this));
	}

	/**
	 * A dicsõséglistát kirakó függvény.
	 */
	public void showHighScores() {
		Display.getDisplay(this).setCurrent(
				new HighScoreWindow(this, highscores));
	}

	/**
	 * A menüt kirakó függvény.
	 */
	public void showMenu() {
		Display.getDisplay(this).setCurrent(new MenuWindow(this));
	}

	/**
	 * Az alkalmazást bezáró függvény.
	 */
	public void exit() {
		try {
			destroyApp(true);
		} catch (MIDletStateChangeException e) {
			e.printStackTrace();
		}
		notifyDestroyed();
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		highscores.save();

		RecordStore states = null;

		try {
			states = RecordStore.openRecordStore("StatesStore", false);

			states.setRecord(1, Common.lastName.getBytes(), 0,
					Common.lastName.getBytes().length);

			byte[] tmp = new byte[1];
			tmp[0] = Common.firstGame;
			states.setRecord(2, tmp, 0, tmp.length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (states != null) {
				try {
					states.closeRecordStore();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * A játékot reset-elõ függvény.
	 */
	public void resetGame() {
		highscores = new HighScores();

		Common.lastName = "Anonymus";
		Common.firstGame = 1;

		Alert confirm = new Alert("Success",
				"The game has been reset to defaults.", null,
				AlertType.CONFIRMATION);
		confirm.setTimeout(1000);
		Display.getDisplay(this).setCurrent(confirm, new MenuWindow(this));
	}

	protected void pauseApp() {
	}

}