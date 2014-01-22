package com.apcsz.anish_danny;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends Canvas{
	
	private JFrame frame;
	private BufferStrategy strat;
	private boolean leftPressed, rightPressed, firePressed;
	private String message = "";
	private boolean waitingForKeyPress;
	
	public Game(){
		frame = new JFrame("Pirate Game!");
		JPanel panel = (JPanel) frame.getContentPane();
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		panel.setPreferredSize(new Dimension(Constants.GRID_X, Constants.GRID_Y));
		panel.setLayout(null);
		panel.add(this);
		setBounds(0,0,Constants.GRID_X,Constants.GRID_Y);
		panel.add(this);
		setIgnoreRepaint(true);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		this.createBufferStrategy(2);
		strat = getBufferStrategy();
		Constants.STILL_PLAYING = true;
		Constants.LEVEL = 1;
		initializeShips();
		leftPressed = false;
		rightPressed = false;
		firePressed = false;
		addKeyListener(new KeyListener());
	}

	public void initializeShips(){
		int totalEnemies = Constants.LEVEL*3;
		for (int i=0; i<totalEnemies; i++) {
			int health = Constants.ENEMY_BASE_HEALTH * Constants.LEVEL;
			int damage = Constants.ENEMY_BASE_DAMAGE * Constants.LEVEL;
			int speed = Constants.ENEMY_BASE_MOVE_SPEED * Constants.LEVEL;
			double xCoor = Constants.GRID_X / totalEnemies * i + (Constants.GRID_X / totalEnemies / 2) - 15;
			double yCoor = 50;
			Enemy enemy = new Enemy(health, damage, xCoor, yCoor, speed, "resources/enemy.png");
			Constants.ENEMIES.add(enemy);
		}
	}
	
	private void startGame() {
		Constants.ENEMIES.clear();
		initializeShips();
		leftPressed = false;
		rightPressed = false;
		firePressed = false;
	}
	
	public void notifyEnemyDestroyed(Enemy enemy){
		Constants.ENEMIES.remove(enemy);
		if (Constants.ENEMIES.size() == 0) {
			notifyClear();
		}
	}
	private void notifyClear() {
		if (Constants.LEVEL == 9) {
			message = "You beat the game! You have stopped the Japanese attack!";
		}
		else {
			message = "Wave " + Constants.LEVEL + " Completed.";
		}
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.runGameLoop();
	}

	private void runGameLoop() {
		long initLoop = System.currentTimeMillis();
		long change;
		while (Constants.STILL_PLAYING) {
			change = System.currentTimeMillis() - initLoop;
			initLoop = System.currentTimeMillis();
			
			Graphics2D gfx = (Graphics2D) strat.getDrawGraphics();
			//gfx.setColor(new Color(0,67,171));
			gfx.fillRect(0, 0, Constants.GRID_X, Constants.GRID_Y);
			for (int i=0; i<Constants.ENEMIES.size(); i++) {
				Enemy ship = Constants.ENEMIES.get(i);
				ship.draw(gfx);
			}
			
			gfx.dispose();
			strat.show();

			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}
	
	private class KeyListener extends KeyAdapter {
		
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (event.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = true;
			}
		} 
			
			
		public void keyReleased(KeyEvent e) {			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = false;
			}
		}

		public void keyTyped(KeyEvent e) {
			// If we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
			else if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
				// Pause
			}
		}
	}
}
