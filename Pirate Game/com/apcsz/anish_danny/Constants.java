package com.apcsz.anish_danny;

import java.util.ArrayList;

public class Constants {

	public static final int GRID_X = 640;
	public static final int GRID_Y = 480;
	public static boolean STILL_PLAYING = true;
	public static int LEVEL = 1;
	
	public static final int PLAYER_STARTING_HEALTH = 100;
	public static final int PLAYER_STARTING_DAMAGE = 1;
	public static final int PLAYER_STARTING_SPEED = 156;
	public static double MAX_CANNONBALL_SPEED = 10;
	
	public static final int ENEMY_BASE_HEALTH = 1;
	public static final int ENEMY_BASE_DAMAGE = 1;
	public static final double ENEMY_BASE_MOVE_SPEED = 0.75;
	public static final double ENEMY_CANNONBALL_BASE_SPEED = -256;
	public static final double PLAYER_CANNONBALL_BASE_SPEED = 256;
	
	public static final String ENEMY_IMAGE = "/resources/enemy.png";
	public static final String PLAYER_IMAGE = "/resources/player.png";
	public static final String BG_IMAGE = "/resources/bg.png";
	public static final String PLAYER_CANNONBALL_IMAGE = "/resources/playershot.png";
	public static final String ENEMY_CANNONBALL_IMAGE = "/resources/enemyshot.png";
	
	public static ArrayList<Enemy> ENEMIES = new ArrayList<Enemy>();
	public static ArrayList<Cannonball> PLAYER_CANNONBALLS = new ArrayList<Cannonball>();
	public static ArrayList<Cannonball> ENEMY_CANNONBALLS = new ArrayList<Cannonball>();
	
}
