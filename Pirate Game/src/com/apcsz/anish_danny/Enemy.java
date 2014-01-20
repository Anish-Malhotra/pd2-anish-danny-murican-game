package com.apcsz.anish_danny;

import java.util.Random;

public class Enemy extends Ship {

	private static int enemyHealth = Constants.ENEMY_BASE_HEALTH * Constants.ENEMY_DIFFICULTY;
	private static int enemyDamage = Constants.ENEMY_BASE_DAMAGE * Constants.ENEMY_DIFFICULTY;
	Random r = new Random();
	
	public Enemy() {
		super(enemyHealth, enemyDamage);
		Constants.ENEMIES.add(this);
	}

	public void shoot() {
		Ship target = Player.getPlayer();
		double distance = this.getYCoor() - target.getYCoor();
		double cannonballYSpd = Constants.ENEMY_CANNONBALL_BASE_SPEED * Constants.ENEMY_DIFFICULTY;
		double time = distance / cannonballYSpd;
		double requiredZSpd = 9.8 * time / 2;
		Cannonball c = new Cannonball(this.getDamage(), this.getXCoor(), this.getYCoor(), 0, cannonballYSpd, requiredZSpd);
	}

	public boolean move() {
		// Have to implement checking so that the enemy ship does not go out of bounds
		int angle = r.nextInt(360);
		this.xCoor += Constants.ENEMY_BASE_MOVE_SPEED * Math.cos(angle * Math.PI / 180);
		this.yCoor += Constants.ENEMY_BASE_MOVE_SPEED * Math.sin(angle * Math.PI / 180);
		return true;
	}

}
