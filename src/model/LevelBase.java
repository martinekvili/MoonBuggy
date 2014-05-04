package model;

import top.Common;

/**
 * A szinteket reprezentáló osztályok õsosztálya.
 */
public abstract class LevelBase implements IActive {

	/**
	 * A pályaelemeket tartalmazó adatszerkezet.
	 */
	protected PushableArray environment;

	/**
	 * Lépésszámláló.
	 */
	private int counter;

	/**
	 * Konstruktor, ami inicializálja a szintet.
	 */
	public LevelBase() {
		environment = new PushableArray(getInitialArray());

		counter = 0;
	}

	/**
	 * Az kezdeti helyzetet (végig üres, semmi akadály) felállító függvény.
	 * 
	 * @return az inicializáló tömb
	 */
	protected abstract int[] getInitialArray();

	/**
	 * Léptetõ függvény.
	 * 
	 * Amikor lejár a számláló, lépteti eggyel a szintet, egy új elemet is
	 * beillesztve ezáltal.
	 */
	public void step() {
		counter++;
		if (counter == Common.levelStepDivider) {
			counter = 0;

			environment.push(getNewElement());
		}
	}

	/**
	 * A függvény ami a következõ beillesztendõ elemet adja meg.
	 * 
	 * @return a beillesztendõ új elem
	 */
	protected abstract int getNewElement();

	/**
	 * Az ütközést ellenõrzõ függvény.
	 * 
	 * @param where
	 *            - hol nézzük az ütközést
	 * @return igaz, ha ütközés van
	 */
	protected abstract boolean isCollision(int where);

	/**
	 * A pálya egy elemét elkérõ függvény.
	 * 
	 * @param which
	 *            - melyik elem
	 * @return a pályaelem
	 */
	public int get(int which) {
		return environment.get(which);
	}

	/**
	 * A pálya méretét lekérdezõ függvény.
	 * 
	 * @return a pálya mérete
	 */
	public int size() {
		return environment.size();
	}

	/**
	 * A számláló állapotát lekérdezõ függvény.
	 * 
	 * @return a számláló aránya a lépésosztóhoz képest
	 */
	public float getMovedPercent() {
		return (float) counter / Common.levelStepDivider;
	}

}
