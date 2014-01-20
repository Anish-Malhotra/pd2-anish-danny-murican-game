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
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends Canvas{
	
	private JFrame frame;
	private BufferStrategy strat;
	private boolean stillPlaying;
	private boolean upPressed,downPressed,firePressed;
	private int enemyCount;
	private ArrayList<Ship> ships,removeShips;
	private int level;
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
		
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
		panel.add(this);
		setBounds(0,0,800,600);
		panel.add(this);
		setIgnoreRepaint(true);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		this.createBufferStrategy(2);
		strat = getBufferStrategy();
		stillPlaying = true;
		ships = new ArrayList<Ship>();
		removeShips = new ArrayList<Ship>();
		enemyCount = 0;
		level = 0;
		initializeShips();
		upPressed = false;
		downPressed = false;
		firePressed = false;
		addKeyListener(new KeyListener());
	}

	public void initializeShips(){
		ships.add(Player.getPlayer());
		for(int i=0;i<12;i++){
			Ship enemy = new Enemy(Constants.ENEMY_BASE_HEALTH, Constants.ENEMY_BASE_DAMAGE, 750.0, (50.0+(i*50)), Constants.ENEMY_BASE_MOVE_SPEED, "resources/enemy.gif");
			ships.add(enemy);
			enemyCount++;
		}
	}
	
	private void startGame() {
		ships.clear();
		initializeShips();

		upPressed = false;
		downPressed = false;
		firePressed = false;
	}
	
	public void notifyEnemyDestroyed(Ship enemy){
		enemyCount--;
		removeShips.add(enemy);
		if(enemyCount==0){
			notifyClear();
		}
	}
	private void notifyClear() {
		if(level==9){
			message = "You beat the game! The Japanese have stopped their attack!";
		}
		else{
			message = "Wave " + level + " Completed.";
		}
		
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.runGameLoop();
	}

	private void runGameLoop() {
		long initLoop = System.currentTimeMillis();
		long change;
		while(stillPlaying){
			change = System.currentTimeMillis() - initLoop;
			initLoop = System.currentTimeMillis();
					
			Graphics2D gfx = (Graphics2D) strat.getDrawGraphics();
			gfx.setColor(new Color(0,67,171));
			gfx.fillRect(0,0,800,600);
			for (int i=0;i<ships.size();i++) {
				Ship ship = (Ship) ships.get(i);
				
				ship.draw(gfx);
			}
			gfx.dispose();
			strat.show();

			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}
	
	private class KeyListener extends KeyAdapter {
		
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (event.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = true;
			}
		} 
			
			
		public void keyReleased(KeyEvent e) {			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = false;
			}
		}

		public void keyTyped(KeyEvent e) {
			// if we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
}
