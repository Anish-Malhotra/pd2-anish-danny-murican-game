package com.apcsz.anish_danny;

public class Player extends Ship {
	
	Game g = new Game();
	private static Player player = new Player();
	
	public Player() {
		super(Constants.PLAYER_STARTING_HEALTH, Constants.PLAYER_STARTING_DAMAGE,35.0,300.0,31.0,"resources/player.gif");
	}
	
	public void shoot() {
		// Shooting implementation with graphics goes here
		double angle = 90;
		double sliderResult = 10;
		double cannonballXSpd = Constants.MAX_CANNONBALL_SPEED * Math.cos(angle * Math.PI / 180);
		double cannonballYSpd = Constants.MAX_CANNONBALL_SPEED * Math.sin(angle * Math.PI / 180);
		double cannonballZSpd = sliderResult;
		Cannonball c = new Cannonball(this.getDamage(), this.getXCor(), this.getYCor(), cannonballXSpd, cannonballYSpd, cannonballZSpd);
	}

	public void setSpeed(double spd) {
		speed = spd;
	}
	
	public void move(long change) {
		
		//if we reached the top of the screen and are still trying to move
		if ((speed < 0) && (yCor < 20)) {
			return;
		}
		
		//if we reached the bottom of the screen and are still trying to move
		if ((speed > 0) && (yCor > 580)) {
			return;
		}
		
		yCor += (change * speed) / 1000;
		
	}

	public static Ship getPlayer() {
		return player;
	}
	
}
