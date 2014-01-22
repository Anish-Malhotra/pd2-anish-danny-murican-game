package com.apcsz.anish_danny;

import java.awt.Graphics;

public abstract class Plane {

	private int maxHp, hp, damage;
	protected double xCoor, yCoor;
	protected double speed,ogSpeed;
	private Sprite sprite;
	
	public Plane(int maxHp, int damage, double xCoor, double yCoor, double speed, String ref) {
		this.sprite = ImageLoader.getImageLoader().getSprite2(ref);
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.speed = speed;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.damage = damage;
	}
	
	abstract public void shoot();
	abstract public void move(long change);
	
	public void loseHp(int amount) {
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

	public double getXCor() {
		return xCoor;
	}
	
	public double getYCor() {
		return yCoor;
	}
	
	public void draw(Graphics g) {
		sprite.draw(g,(int)xCoor,(int)yCoor);
	}
}
