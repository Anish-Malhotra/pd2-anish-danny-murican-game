package com.apcsz.anish_danny;

import java.awt.Graphics;

public abstract class Ship {

	private int maxHp, hp, damage;
	protected double xCor, yCor;
	protected double speed,ogSpeed;
	private Sprite sprite;
	
	public Ship(int maxHp, int damage, double xCor, double yCor, double speed, String ref) {
		this.sprite = ImageLoader.getImageLoader().getSprite2(ref);
		this.xCor = xCor;
		this.yCor = yCor;
		this.speed = speed;
		this.ogSpeed = speed;
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
		return xCor;
	}
	
	public double getYCor() {
		return yCor;
	}
	
	public void draw(Graphics g){
		sprite.draw(g,(int)xCor,(int)yCor);
	}
}
