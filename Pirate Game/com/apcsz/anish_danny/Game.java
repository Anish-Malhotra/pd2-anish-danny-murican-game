package com.apcsz.anish_danny;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends Canvas{
	
	public static Game g;
	private JFrame frame;
	private BufferStrategy strat;
	private String message = "";
	private BufferedImage bg;
	protected boolean leftPressed, rightPressed, upPressed, downPressed, spacePressed;
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
		initializeEnemies();
		leftPressed = rightPressed = upPressed = downPressed = spacePressed = false;
		addKeyListener(new KeyListener());
		bg = ImageLoader.getImageLoader().getImage(Constants.BG_IMAGE);
		player = Player.getPlayer();
	}
	
	public static Game getGame() {
		if (g == null) {
			g = new Game();
		}
		return g;
	}

	public void initializeEnemies() {
		Constants.ENEMIES.clear();
		int totalEnemies = Constants.LEVEL * 1;
		for (int i=0; i<totalEnemies; i++) {
			int health = Constants.ENEMY_BASE_HEALTH * Constants.LEVEL;
			int damage = Constants.ENEMY_BASE_DAMAGE * Constants.LEVEL;
			double speed = Constants.ENEMY_BASE_MOVE_SPEED * Constants.LEVEL;
			double xCoor = Constants.GRID_X - 50;
			double yCoor = Constants.GRID_Y / totalEnemies / 2 + Constants.GRID_Y / totalEnemies * i - 20;
			Enemy enemy = new Enemy(health, damage, xCoor, yCoor, speed);
			Constants.ENEMIES.add(enemy);
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
	
	public void collisionDetection() {
		for (int i=0; i<Constants.ENEMIES.size(); i++) {
			Enemy e = Constants.ENEMIES.get(i);
			for (int j=0; j<Constants.MISSILES.size(); j++) {
				e.collide(Constants.MISSILES.get(j));
			}
		}
	}
	
	public static void main(String[] args) {
		getGame().runGameLoop();
	}

	private void runGameLoop() {
		long initLoop = System.currentTimeMillis();
		long change;
		while (Constants.STILL_PLAYING) {
			change = System.currentTimeMillis() - initLoop;
			initLoop = System.currentTimeMillis();
			
			Graphics2D gfx = (Graphics2D) strat.getDrawGraphics();
			gfx.fillRect(0, 0, Constants.GRID_X, Constants.GRID_Y);
			gfx.drawImage(bg,0,0,null);
			
			for (int i=0; i<Constants.MISSILES.size(); i++) {
				Missile m = Constants.MISSILES.get(i);
				m.updateBounds();
				player.collide(m);
				m.update(change);
				m.draw(gfx);
			}
			for (int i=0; i<Constants.ENEMIES.size(); i++) {
				Enemy e = Constants.ENEMIES.get(i);
				e.updateBounds();
				player.collide(e);
				for (int j=0; j<Constants.MISSILES.size(); j++) {
					Missile m = Constants.MISSILES.get(j);
					e.collide(m);
				}
				e.update(change);
				e.draw(gfx);
			}
			player.update(change);
			player.shoot();
			player.draw(gfx);
			
			collisionDetection();
			
			if (player.getHp() == 0){
				player.destroy();
			}
			
			gfx.dispose();
			strat.show();

			try { Thread.sleep(100); } catch (Exception e) {}
		}
	}

	private class KeyListener extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				spacePressed = true;
			}
		} 
			
		public void keyReleased(KeyEvent e) {			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				spacePressed = false;
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
	
	public String toString() {
		System.out.println("l" + leftPressed);
		System.out.println("r" + rightPressed);
		System.out.println("u" + upPressed);
		System.out.println("d" + downPressed);
		return "--";
	}
}
