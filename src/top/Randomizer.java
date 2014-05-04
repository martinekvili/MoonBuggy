package top;

import java.util.Random;

/**
 * A játékban a véletlenszerûséget megvalósító osztály.
 */
public class Randomizer {

	private static Random random = new Random();

	/**
	 * Adott valószínûséggel igaz értéket adó függvény.
	 * 
	 * @param percentage
	 *            - a valószínûség
	 * @return a megadott valószínûséggel igaz
	 */
	public static boolean chanceByPercent(int percentage) {
		return random.nextInt(100) < percentage;
	}

}
