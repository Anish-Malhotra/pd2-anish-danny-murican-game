package com.apcsz.anish_danny;

import java.util.Random;

public class Enemy extends Plane {

	Random r = new Random();
	
	public Enemy(int maxHp, int damage, double xCor, double yCor, double speed, String ref) {
		super(maxHp, damage, xCor, yCor, speed, ref);
		Constants.ENEMIES.add(this);
	}

	public void shoot() {
		Plane target = Player.getPlayer();
		double distance = this.getYCor() - target.getYCor();
		double cannonballYSpd = Constants.ENEMY_CANNONBALL_BASE_SPEED;
		double time = distance / cannonballYSpd;
		Cannonball c = new Cannonball(this.getDamage(), this.getXCor(), this.getYCor(), 0, cannonballYSpd);
	}

	public void move(long change) {
		// Have to implement checking so that the enemy ship does not go out of bounds
		//int angle = r.nextInt(360);
		//this.xCor += Constants.ENEMY_BASE_MOVE_SPEED * Math.cos(angle * Math.PI / 180);
		//this.yCor += Constants.ENEMY_BASE_MOVE_SPEED * Math.sin(angle * Math.PI / 180);
		
		//will implement this soon
	}

}
