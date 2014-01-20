package com.apcsz.anish_danny;

public abstract class Ship {

	private int maxHp, hp, damage;
	protected double xCoor, yCoor;
	
	public Ship(int maxHp, int damage) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.damage = damage;
	}
	
	abstract public void shoot();
	abstract public boolean move();
	
	protected void loseHp(int amount) {
		hp -= amount;
	}

	public int getMaxHp() {
		return maxHp;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getDamage() {
		return damage;
	}

	public double getXCoor() {
		return xCoor;
	}
	
	public double getYCoor() {
		return yCoor;
	}
}
