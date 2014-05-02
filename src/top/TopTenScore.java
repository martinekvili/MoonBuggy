package top;

import javax.microedition.rms.RecordStore;

public class TopTenScore {

	private static final int maxSize = 10;

	private Score[] topTen;

	public TopTenScore() {
		topTen = new Score[maxSize];
	}

	public void addScore(Score newScore) {
		Score[] newTen = new Score[maxSize];

		int i = 0;
		int j = 0;
		while (i < maxSize && topTen[j] != null
				&& topTen[j].score > newScore.score) {
			newTen[i] = topTen[j];
			i++;
			j++;
		}

		if (i < maxSize) {
			newTen[i] = newScore;
			i++;
		}

		while (i < maxSize && topTen[j] != null) {
			newTen[i] = topTen[j];
			i++;
			j++;
		}

		topTen = newTen;
	}

	public Score[] getScores() {
		return topTen;
	}

	public void save() {

		try {
			RecordStore.deleteRecordStore("ScoreStore");
		} catch (Exception e1) {
		}

		RecordStore scoreStore = null;
		try {
			scoreStore = RecordStore.openRecordStore("ScoreStore", true);

			for (int i = 0; i < maxSize; i++) {
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

	private TopTenScore(Score[] scores) {
		this.topTen = scores;
	}

	public static TopTenScore load() {
		RecordStore scoreStore = null;

		try {
			scoreStore = RecordStore.openRecordStore("ScoreStore", false);
		} catch (Exception e) {
		}

		if (scoreStore == null) {
			return new TopTenScore();
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

			return new TopTenScore(scores);
		}
	}
}
