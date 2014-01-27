package com.apcsz.anish_danny;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class Game extends Canvas{
	
	public static Game g;
	protected JFrame gameFrame;
	protected JFrame statsFrame;
	private BufferStrategy strat;
	private String message = "";
	private BufferedImage bg;
	protected boolean leftPressed, rightPressed, upPressed, downPressed, spacePressed;
	private KeyListener k = new KeyListener();
	Player player;
	
	public static void main(String[] args) {
		getGame().runGameLoop();
	}
	
	public Game(){
		gameFrame = new JFrame("MURICAN Game!");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel gamePanel = (JPanel) gameFrame.getContentPane();
		gamePanel.setPreferredSize(new Dimension(Constants.GRID_X-10, Constants.GRID_Y-10));
		gamePanel.setLayout(null);
		gamePanel.add(this);
		setBounds(0,0,Constants.GRID_X,Constants.GRID_Y);
		setIgnoreRepaint(true);
		gameFrame.pack();
		gameFrame.setResizable(false);
		this.createBufferStrategy(2);
		strat = getBufferStrategy();
		
		initializeEnemies();
		reset();
		addKeyListener(k);
		bg = ImageLoader.getImageLoader().getImage(Constants.BG_IMAGE);
		player = Player.getPlayer();
		
		statsFrame = new JFrame(Player.getPlayer().playerName);
		statsFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel statsPanel = (JPanel) statsFrame.getContentPane();
		SpringLayout layout = new SpringLayout();
		statsPanel.setLayout(layout);
		for (int i=0; i<Constants.STATS.length; i++) {
			statsPanel.add(new JLabel((String) Constants.STATS[i][0], JLabel.TRAILING));
			JLabel value;
			if (i == Constants.STATS.length-1) {
				value = new JLabel((String) Constants.STATS[i][1]);
			}
			else {
				value = new JLabel(Constants.STATS[i][1]);
			}
			statsPanel.add(value);
			Constants.STATS_LABELS.add(value);
		}
		SpringUtilities.makeCompactGrid(statsPanel,
                Constants.STATS.length, 2, //rows, cols
                20, 6,        //initX, initY
                20, 6);       //xPad, yPad
		JLabel img = new JLabel(new ImageIcon(ImageLoader.getImageLoader().getImage((String) Constants.RANKS[player.getRank()][2])));
		Constants.STATS_LABELS.add(img);
		layout.putConstraint(SpringLayout.NORTH, img, -20, SpringLayout.SOUTH, statsFrame);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, img, (statsFrame.getWidth()-img.getWidth())/2, SpringLayout.HORIZONTAL_CENTER, statsFrame);
		statsPanel.add(img);
		statsFrame.pack();
		statsFrame.setBounds(Constants.GRID_X+10, 0, statsFrame.getWidth(), statsFrame.getHeight()+img.getHeight()+20);
		
		statsFrame.setVisible(true);
		gameFrame.setVisible(true);
		Constants.CLOUDS.add(new Cloud(Constants.CLOUD_IMAGE,100));
		Constants.CLOUDS.add(new Cloud(Constants.CLOUD_IMAGE,300));
		Constants.CLOUDS.add(new Cloud(Constants.CLOUD_IMAGE,500));
	}
	
	public static Game getGame() {
		if (g == null) {
			g = new Game();
		}
		return g;
	}

	public void initializeEnemies() {
		Constants.ENEMIES.clear();
		int totalEnemies = Constants.LEVEL * 3;
		for (int i=0; i<totalEnemies; i++) {
			int health = Constants.ENEMY_BASE_HEALTH * Constants.LEVEL;
			int damage = Constants.ENEMY_BASE_DAMAGE * Constants.LEVEL;
			double speed = Constants.ENEMY_BASE_MOVE_SPEED * Constants.LEVEL;
			double xCoor = Constants.GRID_X - 50;
			double yCoor = Constants.GRID_Y / totalEnemies / 2 + Constants.GRID_Y / totalEnemies * i - 20;
			Enemy enemy = new Enemy(health, damage, xCoor, yCoor, speed);
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
	
	private void updateStats() {
		if (Constants.STATS.length != 9) {System.out.println("Please update the stats!");} // For debugging purposes
		Constants.STATS[0][1] = player.getMaxHp() + "";
		Constants.STATS[1][1] = player.getHp() + "";
		Constants.STATS[2][1] = player.getDamage() + "";
		Constants.STATS[3][1] = player.speed + " mph";
		Constants.STATS[4][1] = (int) player.getXCoor() + "";
		Constants.STATS[5][1] = (int) player.getYCoor() + "";
		Constants.STATS[6][1] = Constants.ENEMIES.size() + "";
		Constants.STATS[7][1] = Constants.player.getExp() + "";
		Constants.STATS[8][1] = (String) Constants.RANKS[player.getRank()][0];
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
			
			player.updateBounds();
			player.update(change);
			player.shoot();
			player.draw(gfx);
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
				e.shoot();
				for (int j=0; j<Constants.MISSILES.size(); j++) {
					Missile m = Constants.MISSILES.get(j);
					e.collide(m);
				}
				e.update(change);
				e.draw(gfx);
			}
			for (int i=0; i<Constants.CLOUDS.size(); i++) {
				Cloud c = Constants.CLOUDS.get(i);
				c.update(change);
				c.draw(gfx);
			}
			
			gfx.dispose();
			strat.show();
			
			updateStats();
			for (int i=0; i<Constants.STATS.length; i++) {
					Constants.STATS_LABELS.get(i).setText((String) Constants.STATS[i][1]);
			}
			if (player.toRankUp) {
				BufferedImage img = ImageLoader.getImageLoader().getImage((String) Constants.RANKS[player.getRank()][2]);
				Constants.STATS_LABELS.get(Constants.STATS_LABELS.size()-1).setIcon(new ImageIcon(img));
				statsFrame.pack();
				statsFrame.setBounds(Constants.GRID_X+10, 0, statsFrame.getWidth(), statsFrame.getHeight()+img.getHeight()+20);
				removeKeyListener(k);
				reset();
				player.upgradeStats();
				addKeyListener(k);
			}

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
	
	protected void reset() {
		leftPressed = rightPressed = upPressed = downPressed = spacePressed = false;
	}
	
	public String toString() {
		System.out.println("l" + leftPressed);
		System.out.println("r" + rightPressed);
		System.out.println("u" + upPressed);
		System.out.println("d" + downPressed);
		return "--";
	}
}
