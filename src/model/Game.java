package model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import top.Common;
import view.BulletView;
import view.GameCanvas;

public class Game {

	private Ground ground;
	private Buggy buggy;
	private AboveGround aboveGround;
	
	private Vector bullets;

	private Timer timer;

	private GameCanvas view;

	private int points;

	public Game(GameCanvas gc) {
		ground = new Ground();
		aboveGround = new AboveGround();
		
		buggy = new Buggy(this, ground, aboveGround);
		
		bullets = new Vector();

		view = gc;

		timer = new Timer();

		points = 0;
	}

	public Ground getGround() {
		return ground;
	}

	public Buggy getBuggy() {
		return buggy;
	}
	
	public AboveGround getAboveGround() {
		return aboveGround;
	}

	public int getPoints() {
		return points;
	}
	
	public void setJump() {
		buggy.setJump();
	}
	
	public void addBullet() {
		Bullet newBullet = new Bullet(this, aboveGround, Common.placeOnGround + 1, buggy.getJumpPercentage());
		
		bullets.addElement(newBullet);
		
		view.addView(new BulletView(newBullet));
	}	
	
	public void removeBullet(Bullet bullet) {
		bullets.removeElement(bullet);
	}

	private void step() {
		points++;

		ground.step();
		aboveGround.step();
		
		for (int i = 0; i < bullets.size(); i++) {
			((ActiveObject) bullets.elementAt(i)).step();
		}
		
		buggy.step();
	}

	public void start() {
		timer.schedule(new GameStepper(), 0, Common.waitTime);
	}

	public void stop() {
		timer.cancel();
	}
	
	public void gameOver() {
		stop();
	}

	private class GameStepper extends TimerTask {

		public void run() {
			step();
			view.repaint(0, view.getHeight() - 100, view.getHeight(), 100);
			view.repaint(0, 0, 100, 100);
		}

	}
	
}
