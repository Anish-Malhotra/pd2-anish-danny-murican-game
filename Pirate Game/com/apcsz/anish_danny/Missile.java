package com.apcsz.anish_danny;


public abstract class Missile extends Entity {
	
	private int width, height;
	
	public Missile(int damage, double xCoor, double yCoor, double spd, String imageRef) {
		super(damage, xCoor, yCoor, spd, imageRef);
		width = sprite.getWidth();
		height = sprite.getHeight();
		Constants.MISSILES.add(this);
	}

	public void update(long elapsedTime) {
		if (xCoor > Constants.GRID_X+width || xCoor < -width) {
			destroy();
		}
		else {
			xCoor += (elapsedTime * speed) / 1e3;
		}
	}
	
	public void destroy() {
		Constants.MISSILES.remove(this);
	}
	
}
