package com.apcsz.anish_danny;

public class Player extends Plane {
	
	private static Player player = new Player();
	private long lastFire = 0;
	
	public Player() {
		super(Constants.PLAYER_STARTING_HEALTH, Constants.PLAYER_STARTING_DAMAGE, 32, Constants.GRID_Y/2-16, Constants.PLAYER_STARTING_SPEED, Constants.PLAYER_IMAGE);
	}
	
	public static Player getPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}
		
	public void update(long timeElapsed) {
		//System.out.println(Game.getGame());
		if (Game.getGame().upPressed && !(yCoor <= 0)) {
			yCoor -= (timeElapsed * speed) / 1e3;
		}
		if (Game.getGame().downPressed && !(yCoor > Constants.GRID_Y-40)) {
			yCoor += (timeElapsed * speed) / 1e3;
		}
		if (Game.getGame().leftPressed && !(xCoor <= 0)) {
			xCoor -= (timeElapsed * speed) / 1e3;
		}
		if (Game.getGame().rightPressed && !(xCoor > Constants.GRID_X-40)) {
			xCoor += (timeElapsed * speed) / 1e3;
		}
	}

	public void shoot() {
		if (Game.getGame().spacePressed && !(System.currentTimeMillis() - lastFire < Constants.PLAYER_FIRING_INTERVAL)) {
			lastFire = System.currentTimeMillis();
			PlayerMissile m = new PlayerMissile(this.getDamage(), this.getXCoor(), this.getYCoor());
			Constants.MISSILES.add(m);
		}
	}

	public boolean collide(Entity other) {
		if (canCollide(other)) {
			// Enemy kamikaze pilot dies on crashing into player
			if (other instanceof Enemy) {
				this.loseHp(other.getDamage());
				other.destroy();
			}
			else if (other instanceof EnemyMissile) {
				this.loseHp(other.getDamage());
				other.destroy();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void destroy() {
		System.out.println("You have died!");
		System.exit(0);
	}
	
}
