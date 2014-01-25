package com.apcsz.anish_danny;

public class Player extends Plane {
	
	private static Player player = new Player();
	
	public Player() {
		super(Constants.PLAYER_STARTING_HEALTH, Constants.PLAYER_STARTING_DAMAGE, 32, Constants.GRID_Y/2-16, Constants.PLAYER_STARTING_SPEED, Constants.PLAYER_IMAGE);
	}
	
	public void setSpeed(double spd) {
		speed = spd;
	}
		
	public void update(long timeElapsed) {
		if(Game.upPressed){
			if ((yCoor < 8)) 
				return;
			yCoor -= (timeElapsed * speed) / 1e9;
		}
		else if(Game.downPressed){
			if (yCoor > (Constants.GRID_Y-40))
				return;
			yCoor += (timeElapsed * speed) / 1e9;
		}
		else if(Game.leftPressed){
			if(xCoor < 8)
				return;
			xCoor -= (timeElapsed * speed) / 1e9;
		}
		else if(Game.rightPressed){
			if(xCoor > (Constants.GRID_X-40))
				return;
			xCoor += (timeElapsed * speed) / 1e9;			
		}
	}

	public static Player getPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}

	public void collidedWith(Plane other) {
		if (other instanceof Enemy){
			this.loseHp(other.getDamage());
		}
	}
	
	public void collidedWith(Cannonball other) {
		this.loseHp(other.getDamage());
	}
	
}
