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
	
	private JFrame frame;
	private BufferStrategy strat;
	private String message = "";
	private BufferedImage bg;
	protected static boolean leftPressed, rightPressed, upPressed, downPressed, spacePressed;
	protected static long firingInterval = 450;
	private long lastFire = 0;
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

	public void initializeEnemies() {
		int totalEnemies = Constants.LEVEL * 3;
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
	
	private void startGame() {
		Constants.ENEMIES.clear();
		initializeEnemies();
		leftPressed = rightPressed = upPressed = downPressed = spacePressed = false;
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
	
	public void shoot() {
		if(Game.spacePressed){
			if (System.currentTimeMillis() - lastFire < firingInterval) {
				return;
			}
			lastFire = System.currentTimeMillis();
			Missle c = new Missle(player.getDamage(), player.getXCoor(), player.getYCoor(), Constants.PLAYER_MISSLE_BASE_SPEED, Constants.PLAYER_MISSLE_IMAGE,true);
			Constants.PLAYER_MISSLES.add(c);
		}
	}
	
	public void collisionDetection(){
		Plane p = (Plane) player;
		for(int i=0; i<Constants.ENEMIES.size(); i++){
			Plane enemy = (Plane) Constants.ENEMIES.get(i);
			if (p.collidesWith(enemy)) {
				p.collidedWith(enemy);
				enemy.collidedWith(p);
			}
		}
		for (int i=0; i<Constants.ENEMY_MISSLES.size(); i++) {
			Missle c = Constants.ENEMY_MISSLES.get(i);
			if (p.collidesWith(c)) {
				p.collidedWith(c);
				c.collidedWith(p);
			}
		}
		for (int i=0; i<Constants.ENEMIES.size(); i++){
			for (int j=0; j<Constants.PLAYER_MISSLES.size(); j++) {
				Missle c = Constants.PLAYER_MISSLES.get(j);
				Plane enemy = (Plane) Constants.ENEMIES.get(i);
				if (enemy.collidesWith(c)) {
					enemy.collidedWith(c);
					c.collidedWith(enemy);
				}
			}
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
				Enemy e = Constants.ENEMIES.get(i);
				e.update(change);
				e.draw(gfx);
			}
			for (int i=0; i<Constants.PLAYER_MISSLES.size(); i++) {
				Missle c = Constants.PLAYER_MISSLES.get(i);
				c.update(change);
				c.draw(gfx);
			}
			for (int i=0; i<Constants.ENEMY_MISSLES.size(); i++) {
				Missle c = Constants.ENEMY_MISSLES.get(i);
				c.update(change);
				c.draw(gfx);
			}
			player.update(change);
			player.draw(gfx);
			
			if(spacePressed){
				shoot();
			}
			
			collisionDetection();
			
			if(player.getHp() == 0){
				System.exit(0);
			}
			
			gfx.dispose();
			strat.show();

			try { Thread.sleep(10); } catch (Exception e) {}
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
}
