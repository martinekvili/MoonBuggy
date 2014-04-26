package model;

import java.util.Timer;
import java.util.TimerTask;

import top.Common;
import view.GameCanvas;

public class Game {
	
	private Ground ground;
	private Buggy buggy;
	
	private Timer timer;
	
	private GameCanvas view;
	
	private int points;
	
	public Game(GameCanvas gc) {
		ground = new Ground();
		buggy = new Buggy();
		
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
	
	public int getPoints() {
		return points;
	}
	
	private void step() {
		points++;
		
		ground.step();
		buggy.step();
		
		if (!buggy.isJumping() && ground.get(Common.placeOnGround) == Ground.HOLE) {
			stop();
		}
	}
	
	public void start() {
		timer.schedule(new GameStepper(), 0, Common.waitTime);
	}
	
	public void stop() {
		timer.cancel();
	}
	
	private class GameStepper extends TimerTask {

		public void run() {
			step();
			view.repaint(0, view.getHeight() - 100, view.getHeight(), 100);
			view.repaint(0, 0, 100, 100);
		}

	}
	
}
