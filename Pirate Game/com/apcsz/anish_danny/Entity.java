package com.apcsz.anish_danny;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {

	private int damage;
	protected double xCoor, yCoor, speed;
	protected BufferedImage sprite;
	protected Rectangle bounds;
	protected double oldXCoor, oldYCoor;
	
	public Entity(int damage, double xCoor, double yCoor, double speed, String imageRef) {
		this.damage = damage;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.speed = speed;
		this.sprite = ImageLoader.getImageLoader().getImage(imageRef);
		this.bounds = new Rectangle((int)this.xCoor, (int)this.yCoor, this.sprite.getWidth(), this.sprite.getHeight());
	}
	
	public double getXCoor() {
		return xCoor;
	}
	
	public double getYCoor() {
		return yCoor;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void draw(Graphics gfx) {
		gfx.drawImage(sprite, (int)xCoor, (int)yCoor, null);
	}
	
	abstract void update(long elapsedTime);
	abstract void destroy();
	
	public void updateBounds() {
		this.bounds.setBounds((int)xCoor, (int)yCoor, sprite.getWidth(), sprite.getHeight());
	}
	
	public boolean canCollide(Entity other) {
		return bounds.intersects(other.bounds);
	}
	
}
