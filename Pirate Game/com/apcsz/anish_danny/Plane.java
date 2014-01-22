package com.apcsz.anish_danny;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Plane {

	private int maxHp, hp, damage;
	protected double xCoor, yCoor;
	protected double speed,ogSpeed;
	protected Rectangle bounds;
	private BufferedImage sprite;
	
	public Plane(int maxHp, int damage, double xCoor, double yCoor, double speed, String ref) {
		this.sprite = ImageLoader.getImageLoader().getImage(ref);
		bounds = new Rectangle((int)xCoor,(int)yCoor,sprite.getWidth(),sprite.getHeight());
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.speed = speed;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.damage = damage;
	}
	
	abstract public void shoot();
	abstract public void update(long elapsedTime);
	
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
		g.drawImage(sprite,(int)xCoor,(int)yCoor,null);
	}
}
