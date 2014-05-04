package model;

/**
 * A p�lyaelemeket tartalmaz� adatszerkezet.
 * 
 * Stackszer� m�k�d�st val�s�t meg: push haszn�lhat� rajta, ekkor az alj�n l�v�
 * elem elveszik. Emellett tetsz�legesen c�mezhet�.
 */
public class PushableArray {

	private int[] array;

	private int placeToInsert;

	/**
	 * Konstruktor.
	 * 
	 * @param initialArray
	 *            - a kezdeti �llapot
	 */
	public PushableArray(int[] initialArray) {
		array = initialArray;
		placeToInsert = 0;
	}

	/**
	 * Hozz�ad egy �j elemet, a legr�gebbi elveszik.
	 * 
	 * @param element
	 *            - az �j elem
	 */
	public void push(int element) {
		array[placeToInsert] = element;

		placeToInsert++;
		placeToInsert %= array.length;
	}

	/**
	 * Lek�rdez tetsz�leges elemet.
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
	 * Be�ll�t egy tetsz�leges elemet.
	 * 
	 * @param which
	 *            - az elem helye
	 * @param value
	 *            - a be�lltand� �rt�k
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
	 * A m�retet lek�rdez� f�ggv�ny.
	 * 
	 * @return a m�ret
	 */
	public int size() {
		return array.length;
	}

}
