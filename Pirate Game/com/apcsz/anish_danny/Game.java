package com.apcsz.anish_danny;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends Canvas{
	
	private JFrame frame;
	private BufferStrategy strat;
	private boolean leftPressed, rightPressed, firePressed;
	private String message = "";
	private boolean waitingForKeyPress;
	private BufferedImage bg;
	Player player;
	
	public Game(){
		frame = new JFrame("MURICAN Game!");
		JPanel panel = (JPanel) frame.getContentPane();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setPreferredSize(new Dimension(Constants.GRID_X-10, Constants.GRID_Y-10));
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
		initializePlanes();
		leftPressed = false;
		rightPressed = false;
		firePressed = false;
		addKeyListener(new KeyListener());
		bg = ImageLoader.getImageLoader().getImage("/resources/bg.png");
		player = Player.getPlayer();
	}

	public void initializePlanes(){
		for (int i=0; i<Constants.LEVEL*3; i++) {
			int health = Constants.ENEMY_BASE_HEALTH * Constants.LEVEL;
			int damage = Constants.ENEMY_BASE_DAMAGE * Constants.LEVEL;
			int speed = Constants.ENEMY_BASE_MOVE_SPEED * Constants.LEVEL;
			double xCoor = 586.0;
			double yCoor = (480/((Constants.LEVEL * 3) + 2)) + ((480/((Constants.LEVEL * 3) + 2)) * i) - 16;
			Enemy enemy = new Enemy(health, damage, xCoor, yCoor, speed, "/resources/enemy.png");
			Constants.ENEMIES.add(enemy);
		}
	}
	
	private void startGame() {
		Constants.ENEMIES.clear();
		initializePlanes();
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
		long initLoop = System.nanoTime();
		long change;
		while (Constants.STILL_PLAYING) {
			change = System.nanoTime() - initLoop;
			initLoop = System.nanoTime();
			
			Graphics2D gfx = (Graphics2D) strat.getDrawGraphics();
			gfx.fillRect(0, 0, Constants.GRID_X, Constants.GRID_Y);
			gfx.drawImage(bg,0,0,null);
			for (int i=0; i<Constants.ENEMIES.size(); i++) {
				Enemy plane = Constants.ENEMIES.get(i);
				plane.update(change);
				plane.draw(gfx);
			}
			player.update(change);
			player.draw(gfx);
			
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
