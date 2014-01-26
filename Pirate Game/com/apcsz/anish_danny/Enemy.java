package com.apcsz.anish_danny;

import java.util.Random;

public class Enemy extends Plane {

	Random r = new Random();
	int angle;
	
	public Enemy(int maxHp, int damage, double xCor, double yCor, double speed) {
		super(maxHp, damage, xCor, yCor, speed, Constants.ENEMY_IMAGE);
		Constants.ENEMIES.add(this);
		angle = 90;
	}

	public void update(long elapsedTime){
		this.xCoor -= 1;
		if (xCoor <= 0) {
			xCoor = Constants.GRID_X + 32;
		}
		this.yCoor += (Constants.ENEMY_BASE_MOVE_SPEED * Math.sin(angle * Math.PI / 180));
		angle++;
	}
	
	public void shoot(){
		int check = r.nextInt(100);
		if(check < 30){
			Missle c = new Missle(this.getDamage(), this.xCoor, this.yCoor, this.speed, Constants.ENEMY_MISSLE_IMAGE, false);
			Constants.ENEMY_MISSLES.add(c);
		}
	}
	
	public void collidedWith(Plane other) {
		//kamikaze pilot dies if he crashes into the player
		Constants.ENEMIES.remove(this);
	}
	
	public void collidedWith(Missle other) {
		this.loseHp(other.getDamage());
	}
	
}
