package com.apcsz.anish_danny;

public class Player extends Plane {
	
	Game g = new Game();
	private static Player player = new Player();
	
	public Player() {
		super(Constants.PLAYER_STARTING_HEALTH, Constants.PLAYER_STARTING_DAMAGE, Constants.GRID_X/2, 30, Constants.PLAYER_STARTING_SPEED, Constants.PLAYER_IMAGE);
	}
	
	public void shoot() {
		// Shooting implementation with graphics goes here
		double angle = 90;
		double sliderResult = 10;
		double cannonballXSpd = Constants.MAX_CANNONBALL_SPEED * Math.cos(angle * Math.PI / 180);
		double cannonballYSpd = Constants.MAX_CANNONBALL_SPEED * Math.sin(angle * Math.PI / 180);
		Cannonball c = new Cannonball(this.getDamage(), this.getXCor(), this.getYCor(), cannonballXSpd, cannonballYSpd);
	}

	public void setSpeed(double spd) {
		speed = spd;
	}
	
	public void move(long change) {
		
		//if we reached the top of the screen and are still trying to move
		if ((speed < 0) && (yCoor < 20)) {
			return;
		}
		
		//if we reached the bottom of the screen and are still trying to move
		if ((speed > 0) && (yCoor > 580)) {
			return;
		}
		
		yCoor += (change * speed) / 1000;
		
	}

	public static Plane getPlayer() {
		return player;
	}
	
}
