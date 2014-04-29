package model;

import top.Common;

public class Bullet implements ActiveObject {
	
	private static final int stepDivider = 10;
	private static final int maxDistance = 6;
	
	private int counter;
	
	private int place;
	private float height;
	
	private Game game;
	private AboveGround aboveGround;
	
	private boolean exists;
	
	public Bullet(Game game, AboveGround aboveGround, int place, float height) {
		this.game = game;
		this.aboveGround = aboveGround;
		
		this.place = place;
		this.height = height;
		
		exists = true;
		
		counter = 0;
	}
	
	public float getHeight() {
		return height;
	}
	
	public int getPlace() {
		return place;
	}
	
	public float getMovedPercent() {
		return (float) counter / stepDivider;
	}

	public void step() {
		counter++;
		
		if (isCollision()) {
			return;
		}
		
		if (counter == stepDivider) {
			counter = 0;
			
			place++;
			
			if (place == ( Common.placeOnGround + maxDistance) ) {
				game.removeBullet(this);
				exists = false;
				return;
			}
			
			isCollision();
		}

	}

	private boolean isCollision() {
		if (aboveGround.isCollision(place)) {
			aboveGround.removeObstacle(place);
			
			game.removeBullet(this);
			exists = false;
			
			return true;
		}
		
		return false;
	}
	
	public boolean isExisting() {
		return  exists;
	}

}
