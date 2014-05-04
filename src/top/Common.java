package top;

public class Common {

	// GROUND constants

	public static final int arraySize = 15;
	public static final int stepDivider = 5;
	public static final int holeDistance = 3;
	public static final int holeProbability = 30;

	// BUGGY constants

	public static final int jumpTime = 20;
	public static final int placeOnGround = 3;

	// GAME constants

	public static final int waitTime = 40;
	
	// The properties of the game -> to be serialized
	
	public static String lastName = "Anonymus";
	
	public static byte firstGame = 1;
	
	public static boolean isFirst() {
		return firstGame == 1;
	}

}
