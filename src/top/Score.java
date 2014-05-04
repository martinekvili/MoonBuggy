package top;

/**
 * A pontszámot tartalmazó osztály.
 */
public class Score {

	/**
	 * A név.
	 */
	public String name;

	/**
	 * Az elért pontszám.
	 */
	public int score;

	/**
	 * Konstruktor.
	 * 
	 * @param name
	 *            - név
	 * @param score
	 *            - pontszám
	 */
	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}

	/**
	 * Konstruktor, ami szerializált értéket olvas be.
	 * 
	 * @param name
	 *            - név
	 * @param score
	 *            - pontszám
	 */
	public Score(byte[] name, byte[] score) {
		setName(name);
		setScore(score);
	}

	/**
	 * A nevet szerializáló függvény.
	 * 
	 * @return a név byte[] - ben
	 */
	public byte[] getName() {
		return name.getBytes();
	}

	/**
	 * A szerializált nevet beolvasó függvény.
	 * 
	 * @param name
	 *            a név byte[] - ben
	 */
	private void setName(byte[] name) {
		this.name = new String(name);
	}

	/**
	 * A pontszámot szerializáló függvény.
	 * 
	 * @return a pontszám byte[] - ben
	 */
	public byte[] getScore() {
		byte[] bytes = new byte[4];

		for (int i = 0; i < 4; i++) {
			bytes[i] = (byte) (score >> (i * 8));
		}

		return bytes;
	}

	/**
	 * A szerializált pontszámot beolvasó függvény.
	 * 
	 * @param score
	 *            - a pontszám byte[] - ben
	 */
	private void setScore(byte[] score) {
		this.score = score[3] << 24 | (score[2] & 0xFF) << 16
				| (score[1] & 0xFF) << 8 | (score[0] & 0xFF);
	}

}
