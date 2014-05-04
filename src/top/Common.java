package top;

/**
 * Az általános konstansokat tartalmazó osztály.
 */
public class Common {

	// LEVELBASE constants

	public static final int arraySize = 15;
	public static final int levelStepDivider = 5;

	// GROUND constants

	public static final int holeDistance = 3;
	public static final int holeProbability = 30;

	// ABOVEGROUND constants

	public static final int obstacleProbability = 20;

	// BUGGY constants

	public static final int jumpTime = 20;
	public static final int placeOnGround = 3;
	public static final double distancePercent = 0.1;

	// BULLET constants

	public static final int bulletStepDivider = 10;
	public static final int maxDistance = 6;

	// Application constants

	public static final int waitTime = 40;
	public static final int maxHighScoreNumber = 10;

	// The properties of the game -> to be serialized

	public static String lastName = "Anonymus";

	public static byte firstGame = 1;

	public static boolean isFirst() {
		return firstGame == 1;
	}

}
