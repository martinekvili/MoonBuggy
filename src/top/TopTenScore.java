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

	public void save(RecordStore store) {
		try {
			System.out.println(store.getNumRecords());
			for (int i = 1; i <= store.getNumRecords(); i++) {
				store.deleteRecord(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < maxSize; i++) {
			if (topTen[i] != null) {
				byte[] name = topTen[i].getName();
				byte[] score = topTen[i].getScore();

				try {
					System.out.println(store.addRecord(name, 0, name.length));
					System.out.println(store.addRecord(score, 0, score.length));

					System.out.println(store.getNumRecords());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private TopTenScore(Score[] scores) {
		this.topTen = scores;
	}

	public static TopTenScore load(RecordStore store) {
		Score[] scores = new Score[10];

		try {
			System.out.println(store.getNumRecords());
			for (int i = 1; i <= store.getNumRecords() / 2; i++) {
				System.out.println(2 * i);
				byte[] name = store.getRecord(2 * i - 1);
				byte[] score = store.getRecord(2 * i);

				scores[i - 1] = new Score(name, score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new TopTenScore(scores);
	}
}
