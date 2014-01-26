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
	public static int PLAYER_FIRING_INTERVAL = 450;
	public static double PLAYER_MISSILE_SPEED = 256;
	
	public static final int ENEMY_BASE_HEALTH = 1;
	public static final int ENEMY_BASE_DAMAGE = 1;
	public static final double ENEMY_BASE_MOVE_SPEED = 0.75;
	public static final double ENEMY_MISSILE_BASE_SPEED = -256;

	public static final String ENEMY_IMAGE = "/resources/enemy.png";
	public static final String PLAYER_IMAGE = "/resources/player.png";
	public static final String BG_IMAGE = "/resources/bg.png";
	public static final String PLAYER_MISSILE_IMAGE = "/resources/playershot.png";
	public static final String ENEMY_MISSILE_IMAGE = "/resources/enemyshot.png";
	
	public static ArrayList<Enemy> ENEMIES = new ArrayList<Enemy>();
	public static ArrayList<Missile> MISSILES = new ArrayList<Missile>();
	
}
