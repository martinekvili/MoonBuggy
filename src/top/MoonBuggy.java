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
 * Az alkalmaz�s MIDlet-e.
 */
public class MoonBuggy extends MIDlet {

	/**
	 * A dics�s�glista.
	 */
	private HighScores highscores;

	protected void startApp() throws MIDletStateChangeException {
		highscores = HighScores.load();

		initStates();

		showMenu();
	}

	/**
	 * A j�t�k�llapotot bet�lt� f�ggv�ny.
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
	 * A j�t�kot ind�t� f�ggv�ny.
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
	 * A j�t�k v�g�n lefut� f�ggv�ny.
	 * 
	 * Hozz�adja a pontsz�mot a dics�s�glist�hoz.
	 * 
	 * @param score
	 *            - az el�rt pontsz�m
	 */
	public void endGame(Score score) {
		highscores.addScore(score);

		showMenu();
	}

	/**
	 * A tutorialt kirak� f�ggv�ny.
	 */
	public void showTutorial() {
		Common.firstGame = 0;

		Display.getDisplay(this).setCurrent(new TutorialVindow(this));
	}

	/**
	 * A dics�s�glist�t kirak� f�ggv�ny.
	 */
	public void showHighScores() {
		Display.getDisplay(this).setCurrent(
				new HighScoreWindow(this, highscores));
	}

	/**
	 * A men�t kirak� f�ggv�ny.
	 */
	public void showMenu() {
		Display.getDisplay(this).setCurrent(new MenuWindow(this));
	}

	/**
	 * Az alkalmaz�st bez�r� f�ggv�ny.
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
	 * A j�t�kot reset-el� f�ggv�ny.
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