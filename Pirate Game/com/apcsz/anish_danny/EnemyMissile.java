package com.apcsz.anish_danny;

public class EnemyMissile extends Missile {

	public EnemyMissile(int damage, double xCoor, double yCoor) {
		super(damage, xCoor, yCoor, Constants.ENEMY_MISSILE_BASE_SPEED, Constants.ENEMY_MISSILE_IMAGE);
	}
	
}
