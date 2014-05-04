package top;

import javax.microedition.rms.RecordStore;

/**
 * A dics�s�gt�bl�t reprezent�l� oszt�ly.
 */
public class HighScores {

	/**
	 * A dics�s�gt�bla.
	 */
	private Score[] topTen;

	/**
	 * �res dics�s�gt�bl�t l�trehoz� konstruktor.
	 */
	public HighScores() {
		topTen = new Score[Common.maxHighScoreNumber];
	}

	/**
	 * �j pontsz�mot beilleszt� f�ggv�ny.
	 * 
	 * Ha belef�r, beilleszti a sorba, ha nem, akkor nem.
	 * 
	 * @param newScore
	 *            - az �j pontsz�m
	 */
	public void addScore(Score newScore) {
		Score[] newTen = new Score[Common.maxHighScoreNumber];

		int i = 0;
		int j = 0;
		while (i < Common.maxHighScoreNumber && topTen[j] != null
				&& topTen[j].score > newScore.score) {
			newTen[i] = topTen[j];
			i++;
			j++;
		}

		if (i < Common.maxHighScoreNumber) {
			newTen[i] = newScore;
			i++;
		}

		while (i < Common.maxHighScoreNumber && topTen[j] != null) {
			newTen[i] = topTen[j];
			i++;
			j++;
		}

		topTen = newTen;
	}

	/**
	 * A pontsz�mokat lek�r� f�ggv�ny.
	 * 
	 * @return a pontsz�mokat tartalmaz� t�mb
	 */
	public Score[] getScores() {
		return topTen;
	}

	/**
	 * A pontsz�mokat elment� f�ggv�ny.
	 */
	public void save() {

		try {
			RecordStore.deleteRecordStore("ScoreStore");
		} catch (Exception e1) {
		}

		RecordStore scoreStore = null;
		try {
			scoreStore = RecordStore.openRecordStore("ScoreStore", true);

			for (int i = 0; i < Common.maxHighScoreNumber; i++) {
				if (topTen[i] != null) {
					byte[] name = topTen[i].getName();
					byte[] score = topTen[i].getScore();

					scoreStore.addRecord(name, 0, name.length);
					scoreStore.addRecord(score, 0, score.length);
				}
			}

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

	private HighScores(Score[] scores) {
		this.topTen = scores;
	}

	/**
	 * Az elmentett pontsz�mokat beolvas� f�ggv�ny.
	 * 
	 * @return a beolvasott pontsz�mokat tartalmaz� dics�s�gt�bla
	 */
	public static HighScores load() {
		RecordStore scoreStore = null;

		try {
			scoreStore = RecordStore.openRecordStore("ScoreStore", false);
		} catch (Exception e) {
		}

		if (scoreStore == null) {
			return new HighScores();
		}

		else {
			Score[] scores = new Score[10];

			try {
				for (int i = 1; i <= scoreStore.getNumRecords() / 2; i++) {
					byte[] name = scoreStore.getRecord(2 * i - 1);
					byte[] score = scoreStore.getRecord(2 * i);

					scores[i - 1] = new Score(name, score);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					scoreStore.closeRecordStore();
				} catch (Exception e) {
				}
			}

			return new HighScores(scores);
		}
	}
}
