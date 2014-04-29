package top;

import java.util.Timer;
import java.util.TimerTask;

import model.Game;
import view.GameCanvas;

public class GameManager {
	
	private MoonBuggy midlet;
	
	private Game game;
	
	private GameCanvas view;
	
	private Timer timer;
	
	private boolean isRunning;
	
	public GameManager(MoonBuggy m) {
		midlet = m;
		
		view = new GameCanvas(this);
		
		game = new Game(view, this);
		
		isRunning = false;
	}
	
	public GameCanvas getView() {
		return view;
	}
	
	public void togglePause() {
		if (isRunning) {
			stop();
		}
		else {
			start();
		}
	}

	public void start() {
		timer = new Timer();
		timer.schedule(new GameStepper(), 0, Common.waitTime);
		
		isRunning = true;
	}
	
	public void stop() {
		timer.cancel();
		
		isRunning = false;
	}
	
	public void gameOver() {
		stop();
		
		midlet.endGame();
	}
	
	private class GameStepper extends TimerTask {

		public void run() {
			game.step();
			
			view.repaint(0, view.getHeight() - 100, view.getHeight(), 100);
			view.repaint(0, 0, 100, 100);
		}

	}

}
