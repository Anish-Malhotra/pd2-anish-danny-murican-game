package com.apcsz.anish_danny;

public class Cannonball {
	
	private int damage;
	private double xCoor, yCoor, xSpd, ySpd;

	public Cannonball(int damage, double xCoor, double yCoor, double xSpd, double ySpd) {
		this.damage = damage;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.xSpd = xSpd;
		this.ySpd = ySpd;
		Constants.CANNONBALLS.add(this);
	}
	
	/* Return is so that you know if the cannonball moved successfully.
	 * If not, then it'll return false and you can destroy the object */
	public boolean move() {
		if (xCoor + xSpd > Constants.GRID_X / 2 || xCoor + xSpd < Constants.GRID_X || 
			yCoor + ySpd > Constants.GRID_Y / 2 || yCoor + ySpd < Constants.GRID_Y / -2) {
			 return false;
		}
		else {
			xCoor += xSpd;
			yCoor += ySpd;
			return true;
		}
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
