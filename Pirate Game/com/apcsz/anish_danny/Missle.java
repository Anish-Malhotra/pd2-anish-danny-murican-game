package com.apcsz.anish_danny;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Missle {
	
	private int damage;
	private double xCoor, yCoor, speed;
	private boolean playerShot;
	protected BufferedImage sprite;
	protected Rectangle bounds;

	public Missle(int damage, double xCoor, double yCoor, double xSpd, String ref, boolean playerShot) {
		this.sprite = ImageLoader.getImageLoader().getImage(ref);
		bounds = new Rectangle((int)xCoor,(int)yCoor,sprite.getWidth(),sprite.getHeight());
		this.damage = damage;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.speed = xSpd;
		this.playerShot = playerShot;
	}
	
	/* Return is so that you know if the cannonball moved successfully.
	 * If not, then it'll return false and you can destroy the object */
	public void update(long elapsedTime) {
		if(xCoor > (Constants.GRID_X+30))
			Constants.PLAYER_MISSLES.remove(this);
		else if(xCoor < -30)
			Constants.ENEMY_MISSLES.remove(this);
		else
			xCoor += (elapsedTime * speed) / 1e9;			
	}

	public void draw(Graphics g) {
		g.drawImage(sprite,(int)xCoor,(int)yCoor,null);
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
	
	public boolean collidesWith(Plane other) {
		this.bounds.setBounds((int)xCoor,(int)yCoor,sprite.getWidth(),sprite.getHeight());
		other.bounds.setBounds((int)other.getXCoor(),(int)other.getYCoor(),other.sprite.getWidth(),other.sprite.getHeight());
		return bounds.intersects(other.bounds);
	}
	
	public void collidedWith(Plane other) {
		if(playerShot){
			Constants.PLAYER_MISSLES.remove(this);
		}
		else{
			Constants.ENEMY_MISSLES.remove(this);
		}
	}
	
}
