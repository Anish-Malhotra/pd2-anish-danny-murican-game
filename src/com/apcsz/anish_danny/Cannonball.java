package com.apcsz.anish_danny;

public class Cannonball {
	
	private int damage;
	private double xCoor, yCoor, xSpd, ySpd;
	private double zCoor, zSpd;

	public Cannonball(int damage, double xCoor, double yCoor, double xSpd, double ySpd, double zSpd) {
		this.damage = damage;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.xSpd = xSpd / Constants.FPS;
		this.ySpd = ySpd / Constants.FPS;
		this.zCoor = 0;
		this.zSpd = zSpd / Constants.FPS;
		Constants.CANNONBALLS.add(this);
	}
	
	/* Return is so that you know if the cannonball moved successfully.
	 * If not, then it'll return false and you can destroy the object */
	public boolean move() {
		if (xCoor + xSpd > Constants.GRID_X / 2 || xCoor + xSpd < Constants.GRID_X || 
			yCoor + ySpd > Constants.GRID_Y / 2 || yCoor + ySpd < Constants.GRID_Y / -2 ||
			!canZMove()) {
			 return false;
		}
		else {
			xCoor += xSpd;
			yCoor += ySpd;
			zCoor += zSpd;
			zSpd -= 9.81 / Constants.FPS;
			return true;
		}
	}
	
	private boolean canZMove() {
		if (zCoor + zSpd < 0)
			return false;
		else
			return true;
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
