package com.apcsz.anish_danny;

public class Player extends Plane {
	
	private static Player player = new Player();
	private long lastFire = 0;
	private int exp, rank;
	
	public Player() {
		super(Constants.PLAYER_HEALTH, Constants.PLAYER_DAMAGE, 32, Constants.GRID_Y/2-16, Constants.PLAYER_SPEED, Constants.PLAYER_IMAGE);
		this.exp = Constants.PLAYER_EXP;
		this.rank = Constants.PLAYER_RANK;
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
		if (Game.getGame().downPressed && !(yCoor > Constants.GRID_Y-sprite.getHeight())) {
			yCoor += (timeElapsed * speed) / 1e3;
		}
		if (Game.getGame().leftPressed && !(xCoor <= 0)) {
			xCoor -= (timeElapsed * speed) / 1e3;
		}
		if (Game.getGame().rightPressed && !(xCoor > Constants.GRID_X-sprite.getWidth())) {
			xCoor += (timeElapsed * speed) / 1e3;
		}
		if (getHp() <= 0) {
			destroy();
		}
	}

	public void shoot() {
		if (Game.getGame().spacePressed && !(System.currentTimeMillis() - lastFire < Constants.PLAYER_FIRING_INTERVAL)) {
			lastFire = System.currentTimeMillis();
			PlayerMissile m = new PlayerMissile(this.getDamage(), this.getXCoor(), this.getYCoor()+sprite.getHeight()/2);
		}
	}

	public boolean collide(Entity other) {
		if (canCollide(other)) {
			// Enemy kamikaze pilot dies on crashing into player
			if (other instanceof Enemy) {
				this.loseHp(((Enemy) other).getHp());
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
	
	public int getExp() {
		return exp;
	}
	
	public int getRank() {
		return rank;
	}
	
}
