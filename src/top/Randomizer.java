package top;

import java.util.Random;

/**
 * A j�t�kban a v�letlenszer�s�get megval�s�t� oszt�ly.
 */
public class Randomizer {

	private static Random random = new Random();

	/**
	 * Adott val�sz�n�s�ggel igaz �rt�ket ad� f�ggv�ny.
	 * 
	 * @param percentage
	 *            - a val�sz�n�s�g
	 * @return a megadott val�sz�n�s�ggel igaz
	 */
	public static boolean chanceByPercent(int percentage) {
		return random.nextInt(100) < percentage;
	}

}
