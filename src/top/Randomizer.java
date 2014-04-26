package top;

import java.util.Random;

public class Randomizer {

	private static Random random = new Random();

	public static boolean chanceByPercent(int percentage) {
		return random.nextInt(100) < percentage;
	}

}
