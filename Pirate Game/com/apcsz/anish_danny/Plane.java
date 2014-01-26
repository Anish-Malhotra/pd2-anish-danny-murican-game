package com.apcsz.anish_danny;

public abstract class Plane extends Entity {

	private int maxHp, hp;
	
	public Plane(int maxHp, int damage, double xCoor, double yCoor, double speed, String imageRef) {
		super(damage, xCoor, yCoor, speed, imageRef);
		this.maxHp = maxHp;
		this.hp = maxHp;
	}
		
	public void loseHp(int amount) {
		hp -= amount;
	}
	
	abstract void shoot();
	abstract boolean collide(Entity other);

	public int getMaxHp() {
		return maxHp;
	}
	
	public int getHp() {
		return hp;
	}
	
}
