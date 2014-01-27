package com.apcsz.anish_danny;

public class Boss extends Enemy{
	public Boss(int maxHp, int damage, double xCor, double yCor, double speed,String ref) {
		super(maxHp, damage, xCor, yCor, speed, Constants.BOSS_IMAGE);
	}
	
	public void update(long elapsedTime){
		this.xCoor -= 3;
		if (xCoor <= 0) {
			xCoor = Constants.GRID_X + 32;
		}
		this.yCoor += Constants.BOSS_BASE_MOVE_SPEED * Math.sin(angle * Math.PI / 180);
		angle++;
	}
	
	public void shoot() {
		int check = r.nextInt(100);
		if (check < shootFreq) {
			EnemyMissile m = new EnemyMissile(this.getDamage(), this.xCoor, this.yCoor+sprite.getHeight()/2,Constants.BOSS_IMAGE);
		}
	}
}
