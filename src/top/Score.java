package top;

public class Score {

	public String name;
	public int score;

	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public Score(byte[] name, byte[] score) {
		setName(name);
		setScore(score);
	}

	public byte[] getName() {
		return name.getBytes();
	}

	private void setName(byte[] name) {
		this.name = new String(name);
	}

	public byte[] getScore() {
		byte[] bytes = new byte[4];

		for (int i = 0; i < 4; i++) {
			bytes[i] = (byte) (score >> (i * 8));
		}

		return bytes;
	}

	private void setScore(byte[] score) {
		this.score = score[3] << 24 | (score[2] & 0xFF) << 16
				| (score[1] & 0xFF) << 8 | (score[0] & 0xFF);
	}

}
