package com.apcsz.anish_danny;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Plane {

	private int maxHp, hp, damage;
	protected double xCoor,yCoor;
	protected double speed,ogSpeed;
	protected Rectangle bounds;
	protected BufferedImage sprite;
	
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
	
	abstract public void update(long elapsedTime);
	abstract void collidedWith(Plane other);
	abstract void collidedWith(Missle other);
	
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

	public double getXCoor() {
		return xCoor;
	}
	
	public double getYCoor() {
		return yCoor;
	}
	
	public void draw(Graphics g) {
		g.drawImage(sprite,(int)xCoor,(int)yCoor,null);
	}
	
	public boolean collidesWith(Plane other) {
		this.bounds.setBounds((int)xCoor,(int)yCoor,sprite.getWidth(),sprite.getHeight());
		other.bounds.setBounds((int)other.getXCoor(),(int)other.getYCoor(),other.sprite.getWidth(),other.sprite.getHeight());
		return bounds.intersects(other.bounds);
	}
	
	public boolean collidesWith(Missle other) {
		this.bounds.setBounds((int)xCoor,(int)yCoor,sprite.getWidth(),sprite.getHeight());
		other.bounds.setBounds((int)other.getXCoor(),(int)other.getYCoor(),other.sprite.getWidth(),other.sprite.getHeight());
		return bounds.intersects(other.bounds);
	}
}
