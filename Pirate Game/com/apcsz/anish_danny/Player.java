package com.apcsz.anish_danny;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Player extends Plane {
	
	private static Player player;
	protected String playerName;
	protected static JFrame playerFrame;
	private long lastFire = 0;
	private int exp, rank;
	protected boolean toRankUp = false;
	
	public Player(String playerName) {
		super(Constants.PLAYER_HEALTH, Constants.PLAYER_DAMAGE, 32, Constants.GRID_Y/2-16, Constants.PLAYER_SPEED, Constants.PLAYER_IMAGE);
		this.exp = Constants.PLAYER_EXP;
		this.rank = Constants.PLAYER_RANK;
		this.playerName = (playerName == null || playerName.equals("")) ? "Mr.Zamansky" : playerName;
	}
	
	public static Player getPlayer() {
		if (player == null) {
			playerFrame = new JFrame("Player Prompt");
			playerFrame.setLocationRelativeTo(null);
			playerFrame.setVisible(false);
			String name = (String) JOptionPane.showInputDialog(playerFrame, "Sir, what is your name?", "Welcome!", JOptionPane.PLAIN_MESSAGE, null, null, null);
			player = new Player(name);
		}
		return player;
	}
		
	public void update(long timeElapsed) {
		//System.out.println(Game.getGame());
		if (Game.getGame().upPressed) {
			if (!(yCoor <= 0)) {
				yCoor -= (timeElapsed * speed) / 1e3;
			}
			else {
				yCoor = 0;
			}
		}
		if (Game.getGame().downPressed) {
			if (!(yCoor > Constants.GRID_Y-sprite.getHeight())) {
				yCoor += (timeElapsed * speed) / 1e3;
			}
			else {
				yCoor = Constants.GRID_Y-sprite.getHeight();
			}
		}
		if (Game.getGame().leftPressed) {
			if (!(xCoor <= 0)) {
				xCoor -= (timeElapsed * speed) / 1e3;
			}
			else {
				xCoor = 0;
			}
		}
		if (Game.getGame().rightPressed) {
			if (!(xCoor > Constants.GRID_X-sprite.getWidth())) {
				xCoor += (timeElapsed * speed) / 1e3;
			}
			else {
				xCoor = Constants.GRID_X-sprite.getWidth();
			}
		}
		if (getHp() <= 0) {
			destroy();
		}
		rankUp();
	}

	public void shoot() {
		if (Game.getGame().spacePressed && !(System.currentTimeMillis() - lastFire < Constants.PLAYER_FIRING_INTERVAL)) {
			lastFire = System.currentTimeMillis();
			new PlayerMissile(this.getDamage(), this.getXCoor(), this.getYCoor()+sprite.getHeight()/2);
		}
	}
	
	private void rankUp() {
		if (this.rank < Constants.RANKS.length-1 && exp >= (Integer) Constants.RANKS[this.rank+1][1]) {
			this.rank++;
			toRankUp = true;
		}
	}
	
	public void upgradeStats() {
		ImageIcon obama = new ImageIcon(ImageLoader.getImageLoader().getImage("/resources/Obama/Barack Obama.jpg"));
		String message = "Congratulations " + playerName + ".\nYou are now a " + Constants.RANKS[getRank()][0] + ".";
		JOptionPane.showMessageDialog(Game.getGame().gameFrame, message, "Congratulations!", JOptionPane.INFORMATION_MESSAGE, obama);
		ImageIcon plane = new ImageIcon(ImageLoader.getImageLoader().getImage(Constants.PLAYER_IMAGE));
		Object[] upgradeOptions = {"Engine (Speed)", "Guns (Damage)", "Hull (Health)"};
		int choice = JOptionPane.showOptionDialog(playerFrame, "What would you like to upgrade?", "Upgrade your plane", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, plane, upgradeOptions, null);
		int multiplier = ((int) Math.sqrt(10 - Constants.LEVEL) + rank);
		if (choice == 0) {
			speed += Constants.LEVEL_UP_SPEED * multiplier;
		}
		else if (choice == 1) {
			increaseDamage(Constants.LEVEL_UP_DAMAGE * multiplier);
			Constants.PLAYER_FIRING_INTERVAL += Constants.LEVEL_UP_FIRE_INTERVAL;
		}
		else {
			increaseMaxHp(Constants.LEVEL_UP_HEALTH * multiplier);
		}
		toRankUp = false;
	}

	public boolean collide(Entity other) {
		if (canCollide(other)) {
			// Enemy kamikaze pilot dies on crashing into player
			if (other instanceof Enemy) {
				this.loseHp(((Enemy) other).getHp());
				other.destroy();
			}
			else if (other instanceof EnemyMissile || other instanceof BossMissile) {
				this.loseHp(other.getDamage());
				if (!(this.getHp() <= 0)) {
					other.destroy();
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void destroy() {
		JOptionPane.showMessageDialog(playerFrame, "What a shame! You have died! We think it's time to let someone\nmore qualified to battle the Japanese.", "The End?", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public void gainExp(int amount) {
		exp += amount;
	}
	
	public int getExp() {
		return exp;
	}
	
	public int getRank() {
		return rank;
	}
	
}
