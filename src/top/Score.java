package top;

/**
 * A pontsz�mot tartalmaz� oszt�ly.
 */
public class Score {

	/**
	 * A n�v.
	 */
	public String name;

	/**
	 * Az el�rt pontsz�m.
	 */
	public int score;

	/**
	 * Konstruktor.
	 * 
	 * @param name
	 *            - n�v
	 * @param score
	 *            - pontsz�m
	 */
	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}

	/**
	 * Konstruktor, ami szerializ�lt �rt�ket olvas be.
	 * 
	 * @param name
	 *            - n�v
	 * @param score
	 *            - pontsz�m
	 */
	public Score(byte[] name, byte[] score) {
		setName(name);
		setScore(score);
	}

	/**
	 * A nevet szerializ�l� f�ggv�ny.
	 * 
	 * @return a n�v byte[] - ben
	 */
	public byte[] getName() {
		return name.getBytes();
	}

	/**
	 * A szerializ�lt nevet beolvas� f�ggv�ny.
	 * 
	 * @param name
	 *            a n�v byte[] - ben
	 */
	private void setName(byte[] name) {
		this.name = new String(name);
	}

	/**
	 * A pontsz�mot szerializ�l� f�ggv�ny.
	 * 
	 * @return a pontsz�m byte[] - ben
	 */
	public byte[] getScore() {
		byte[] bytes = new byte[4];

		for (int i = 0; i < 4; i++) {
			bytes[i] = (byte) (score >> (i * 8));
		}

		return bytes;
	}

	/**
	 * A szerializ�lt pontsz�mot beolvas� f�ggv�ny.
	 * 
	 * @param score
	 *            - a pontsz�m byte[] - ben
	 */
	private void setScore(byte[] score) {
		this.score = score[3] << 24 | (score[2] & 0xFF) << 16
				| (score[1] & 0xFF) << 8 | (score[0] & 0xFF);
	}

}
