package com.apcsz.anish_danny;

import java.util.Random;

public class Enemy extends Plane {

	Random r = new Random();
	int a;
	
	public Enemy(int maxHp, int damage, double xCor, double yCor, double speed, String ref) {
		super(maxHp, damage, xCor, yCor, speed, ref);
		Constants.ENEMIES.add(this);
	}

	public void update(long elapsedTime){
		this.xCoor -= 1;
		if(xCoor <= 0){
			xCoor = Constants.GRID_X + 32;
		}
		this.yCoor += (Constants.ENEMY_BASE_MOVE_SPEED * Math.sin(a * Math.PI / 180));
		a++;
	}
	
	public void shoot() {
		//need to fix implementation for this
		Player target = Player.getPlayer();
		double distance = this.getYCor() - target.getYCor();
		double cannonballYSpd = Constants.ENEMY_CANNONBALL_BASE_SPEED;
		double time = distance / cannonballYSpd;
		Cannonball c = new Cannonball(this.getDamage(), this.getXCor(), this.getYCor(), 0, cannonballYSpd);
	}

}
