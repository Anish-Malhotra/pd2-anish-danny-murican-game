package com.apcsz.anish_danny;

public class Player extends Ship {

	static Player player;
	
	private Player() {
		super(Constants.PLAYER_STARTING_HEALTH, Constants.PLAYER_STARTING_DAMAGE);
		super.xCoor = 0;
		super.yCoor = Constants.GRID_Y / -1.9;
	}
	
	public static Ship getPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}
	
	public void shoot() {
		// Shooting implementation with graphics goes here
		double angle = 90;
		double sliderResult = 10;
		double cannonballXSpd = Constants.MAX_CANNONBALL_SPEED * Math.cos(angle * Math.PI / 180);
		double cannonballYSpd = Constants.MAX_CANNONBALL_SPEED * Math.sin(angle * Math.PI / 180);
		double cannonballZSpd = sliderResult;
		Cannonball c = new Cannonball(this.getDamage(), this.getXCoor(), this.getYCoor(), cannonballXSpd, cannonballYSpd, cannonballZSpd);
	}

	public boolean move() {
		// Implementation with Key Listener goes here. If move, return true, else, false;
		return true;
	}
	
}
