package model;

public class Bullet implements ActiveObject {
	
	private static final int stepDivider = 10;
	private int counter;
	
	private int place;
	private float height;
	
	public Bullet(int place, float height) {
		this.place = place;
		this.height = height;
		
		counter = 0;
	}

	public void step() {
		counter++;
		if (counter == stepDivider) {
			counter = 0;
			
			// TODO
		}

	}

}
