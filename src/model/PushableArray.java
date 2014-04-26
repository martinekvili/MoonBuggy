package model;

public class PushableArray {

	private int[] array;

	private int placeToInsert;

	public PushableArray(int[] initialArray) {
		array = initialArray;
		placeToInsert = 0;
	}

	public void push(int element) {
		array[placeToInsert] = element;

		placeToInsert++;
		placeToInsert %= array.length;
	}

	public int get(int which) {
		if (which < 0 || which >= array.length) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			which += placeToInsert;
			which %= array.length;

			return array[which];
		}
	}

	public int size() {
		return array.length;
	}

}
