package com.apcsz.anish_danny;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Player extends Plane {
	
	private static Player player;
	protected String playerName;
	private static JFrame playerFrame;
	private long lastFire = 0;
	private int exp, rank;
	protected boolean toRankUp = false;
	
	public Player(String playerName) {
		super(Constants.PLAYER_HEALTH, Constants.PLAYER_DAMAGE, 32, Constants.GRID_Y/2-16, Constants.PLAYER_SPEED, Constants.PLAYER_IMAGE);
		this.exp = Constants.PLAYER_EXP;
		this.rank = Constants.PLAYER_RANK;
		this.playerName = (playerName == null) ? "Mr.Zamansky" : playerName;
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
		if (Game.getGame().upPressed && !(yCoor <= 0)) {
			yCoor -= (timeElapsed * speed) / 1e3;
		}
		if (Game.getGame().downPressed && !(yCoor > Constants.GRID_Y-sprite.getHeight())) {
			yCoor += (timeElapsed * speed) / 1e3;
		}
		if (Game.getGame().leftPressed && !(xCoor <= 0)) {
			xCoor -= (timeElapsed * speed) / 1e3;
		}
		if (Game.getGame().rightPressed && !(xCoor > Constants.GRID_X-sprite.getWidth())) {
			xCoor += (timeElapsed * speed) / 1e3;
		}
		if (getHp() <= 0) {
			destroy();
		}
		rankUp();
	}

	public void shoot() {
		if (Game.getGame().spacePressed && !(System.currentTimeMillis() - lastFire < Constants.PLAYER_FIRING_INTERVAL)) {
			lastFire = System.currentTimeMillis();
			PlayerMissile m = new PlayerMissile(this.getDamage(), this.getXCoor(), this.getYCoor()+sprite.getHeight()/2);
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
		if (choice == 0) {
			speed += Constants.LEVEL_UP_SPEED;
		}
		else if (choice == 1) {
			increaseDamage(Constants.LEVEL_UP_DAMAGE);
		}
		else {
			increaseMaxHp(Constants.LEVEL_UP_HEALTH);
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
			else if (other instanceof EnemyMissile) {
				this.loseHp(other.getDamage());
				other.destroy();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void destroy() {
		System.out.println("You have died!");
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
