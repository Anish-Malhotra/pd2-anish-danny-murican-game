package com.apcsz.anish_danny;

import java.util.ArrayList;

public class Constants {

	public static final int GRID_X = 101;
	public static final int GRID_Y = 101;
	public static final int FPS = 30;
	
	public static final int PLAYER_STARTING_HEALTH = 10;
	public static final int PLAYER_STARTING_DAMAGE = 1;
	public static double MAX_CANNONBALL_SPEED = 10;
	
	public static final int ENEMY_BASE_HEALTH = 1;
	public static final int ENEMY_BASE_DAMAGE = 1;
	public static final int ENEMY_BASE_MOVE_SPEED = 30;
	public static final double ENEMY_CANNONBALL_BASE_SPEED = 1;
	
	public static ArrayList<Enemy> ENEMIES = new ArrayList<Enemy>();
	public static ArrayList<Cannonball> CANNONBALLS = new ArrayList<Cannonball>();
	
}
