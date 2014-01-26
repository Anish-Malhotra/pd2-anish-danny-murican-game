package com.apcsz.anish_danny;

import java.util.Random;

public class Enemy extends Plane {

	Random r = new Random();
	int angle, shootFreq;
	
	public Enemy(int maxHp, int damage, double xCor, double yCor, double speed) {
		super(maxHp, damage, xCor, yCor, speed, Constants.ENEMY_IMAGE);
		Constants.ENEMIES.add(this);
		angle = 90;
		shootFreq = 30;
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
		if (check < shootFreq) {
			EnemyMissile m = new EnemyMissile(this.getDamage(), this.xCoor, this.yCoor);
			Constants.MISSILES.add(m);
		}
	}
	
	public boolean collide(Entity other) {
		if (canCollide(other)) {
			if (other instanceof PlayerMissile) {
				loseHp(other.getDamage());
				if (getHp() <= 0) {
					destroy();
				}
				other.destroy();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void destroy() {
		Constants.ENEMIES.remove(this);
		// Implement player rewards
	}
	
}
