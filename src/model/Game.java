package model;

import java.util.Vector;

import top.Common;
import top.GameManager;
import view.GameWindow;
import view.paintable.AboveGroundView;
import view.paintable.BuggyView;
import view.paintable.BulletView;
import view.paintable.GroundView;
import view.paintable.PointView;

public class Game {
	
	public static final int STARTED = 0;
	public static final int RUNNING = 1;
	public static final int PAUSED = 2;
	public static final int OVER = 3;

	private Ground ground;
	private Buggy buggy;
	private AboveGround aboveGround;
	
	private Vector bullets;
	
	private GameManager manager;

	private GameWindow view;
	
	private int state;

	private int points;

	public Game(GameWindow gc, GameManager m) {
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
		
		state = STARTED;
	}
	
	public void setState(int newState) {
		state = newState;
	}

	public int getState() {
		return state;
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
		manager.gameOver();
	}
	
}
