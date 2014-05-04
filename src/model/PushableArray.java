package model;

/**
 * A pályaelemeket tartalmazó adatszerkezet.
 * 
 * Stackszerû mûködést valósít meg: push használható rajta, ekkor az alján lévõ
 * elem elveszik. Emellett tetszõlegesen címezhetõ.
 */
public class PushableArray {

	private int[] array;

	private int placeToInsert;

	/**
	 * Konstruktor.
	 * 
	 * @param initialArray
	 *            - a kezdeti állapot
	 */
	public PushableArray(int[] initialArray) {
		array = initialArray;
		placeToInsert = 0;
	}

	/**
	 * Hozzáad egy új elemet, a legrégebbi elveszik.
	 * 
	 * @param element
	 *            - az új elem
	 */
	public void push(int element) {
		array[placeToInsert] = element;

		placeToInsert++;
		placeToInsert %= array.length;
	}

	/**
	 * Lekérdez tetszõleges elemet.
	 * 
	 * @param which
	 *            - az elem helye
	 * @return az elem
	 */
	public int get(int which) {
		if (which < 0 || which >= array.length) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			which += placeToInsert;
			which %= array.length;

			return array[which];
		}
	}

	/**
	 * Beállít egy tetszõleges elemet.
	 * 
	 * @param which
	 *            - az elem helye
	 * @param value
	 *            - a beálltandó érték
	 */
	public void set(int which, int value) {
		if (which < 0 || which >= array.length) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			which += placeToInsert;
			which %= array.length;

			array[which] = value;
		}
	}

	/**
	 * A méretet lekérdezõ függvény.
	 * 
	 * @return a méret
	 */
	public int size() {
		return array.length;
	}

}
