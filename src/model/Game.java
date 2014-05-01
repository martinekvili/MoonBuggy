package model;

import java.util.Vector;

import top.Common;
import top.GameManager;
import view.AboveGroundView;
import view.BuggyView;
import view.BulletView;
import view.GameCanvas;
import view.GroundView;
import view.PointView;

public class Game {

	private Ground ground;
	private Buggy buggy;
	private AboveGround aboveGround;
	
	private Vector bullets;
	
	private GameManager manager;

	private GameCanvas view;
	
	private boolean over;

	private int points;

	public Game(GameCanvas gc, GameManager m) {
		ground = new Ground();
		aboveGround = new AboveGround();
		
		buggy = new Buggy(this, ground, aboveGround);
		
		bullets = new Vector();
		
		manager = m;

		view = gc;
		view.setGame(this);
		view.addView(new GroundView(ground));
		view.addView(new AboveGroundView(aboveGround));
		view.addView(new BuggyView(buggy));
		view.addView(new PointView(this));

		points = 0;
		
		over = false;
	}

	public boolean isOver() {
		return over;
	}

	public int getPoints() {
		return points;
	}
	
	public void setJump() {
		buggy.setJump();
	}
	
	public void addBullet() {
		Bullet newBullet = new Bullet(this, aboveGround, Common.placeOnGround, buggy.getJumpPercentage());
		
		bullets.addElement(newBullet);
		
		view.addView(new BulletView(newBullet));
	}	
	
	public void removeBullet(Bullet bullet) {
		bullets.removeElement(bullet);
	}

	public void step() {
		points++;

		ground.step();
		aboveGround.step();
		
		for (int i = 0; i < bullets.size(); i++) {
			((ActiveObject) bullets.elementAt(i)).step();
		}
		
		buggy.step();
	}

	
	public void gameOver() {
		over = true;
		manager.gameOver();
	}
	
}
