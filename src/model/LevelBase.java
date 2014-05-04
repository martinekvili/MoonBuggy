package model;

import top.Common;

/**
 * A szinteket reprezent�l� oszt�lyok �soszt�lya.
 */
public abstract class LevelBase implements IActive {

	/**
	 * A p�lyaelemeket tartalmaz� adatszerkezet.
	 */
	protected PushableArray environment;

	/**
	 * L�p�ssz�ml�l�.
	 */
	private int counter;

	/**
	 * Konstruktor, ami inicializ�lja a szintet.
	 */
	public LevelBase() {
		environment = new PushableArray(getInitialArray());

		counter = 0;
	}

	/**
	 * Az kezdeti helyzetet (v�gig �res, semmi akad�ly) fel�ll�t� f�ggv�ny.
	 * 
	 * @return az inicializ�l� t�mb
	 */
	protected abstract int[] getInitialArray();

	/**
	 * L�ptet� f�ggv�ny.
	 * 
	 * Amikor lej�r a sz�ml�l�, l�pteti eggyel a szintet, egy �j elemet is
	 * beillesztve ez�ltal.
	 */
	public void step() {
		counter++;
		if (counter == Common.levelStepDivider) {
			counter = 0;

			environment.push(getNewElement());
		}
	}

	/**
	 * A f�ggv�ny ami a k�vetkez� beillesztend� elemet adja meg.
	 * 
	 * @return a beillesztend� �j elem
	 */
	protected abstract int getNewElement();

	/**
	 * Az �tk�z�st ellen�rz� f�ggv�ny.
	 * 
	 * @param where
	 *            - hol n�zz�k az �tk�z�st
	 * @return igaz, ha �tk�z�s van
	 */
	protected abstract boolean isCollision(int where);

	/**
	 * A p�lya egy elem�t elk�r� f�ggv�ny.
	 * 
	 * @param which
	 *            - melyik elem
	 * @return a p�lyaelem
	 */
	public int get(int which) {
		return environment.get(which);
	}

	/**
	 * A p�lya m�ret�t lek�rdez� f�ggv�ny.
	 * 
	 * @return a p�lya m�rete
	 */
	public int size() {
		return environment.size();
	}

	/**
	 * A sz�ml�l� �llapot�t lek�rdez� f�ggv�ny.
	 * 
	 * @return a sz�ml�l� ar�nya a l�p�soszt�hoz k�pest
	 */
	public float getMovedPercent() {
		return (float) counter / Common.levelStepDivider;
	}

}
