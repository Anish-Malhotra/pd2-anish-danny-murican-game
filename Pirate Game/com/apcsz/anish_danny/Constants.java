package com.apcsz.anish_danny;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Constants {

	public static final int GRID_X = 640;
	public static final int GRID_Y = 480;
	public static boolean STILL_PLAYING = true;
	public static int LEVEL = 1;
	
	public static final int PLAYER_HEALTH = 1000000;
	public static final int PLAYER_DAMAGE = 1;
	public static final int PLAYER_SPEED = 156;
	public static int PLAYER_FIRING_INTERVAL = 450;
	public static double PLAYER_MISSILE_SPEED = 256;
	public static final int PLAYER_EXP = 0;
	public static final int PLAYER_RANK = 2; // Corresponds to the index in the array below
	public static Object RANKS[][] = {
		/* Title, Exp needed, Insignia image */
		{"Tries to fly", 0, "/resources/01-Tries to fly.png"},
		{"Airman", 100, "/resources/02-Airman.png"},
		{"Airman First Class", 200, "/resources/03-Airman First Class.png"},
		{"Senior Airman", 300, "/resources/04-Senior Airman.png"},
		{"Staff Sergeant", 400, "/resources/05-Staff Sergeant.png"},
		{"Technical Sergeant", 500, "/resources/06-Technical Sergeant.png"},
		{"Master Sergeant", 600, "/resources/07-Master Sergeant.png"},
		{"Senior Master Sergeant", 700, "/resources/08-Senior Master Sergeant.png"},
		{"Chief Master Sergeant", 800, "/resources/09-Chief Master Sergeant.png"},
		{"Command Chief Master Sergeant", 900, "/resources/10-Command Chief Master Sergeant.png"},
		{"Chief Master Sergeant of the Air Force", 1000, "/resources/11-Chief Master Sergeant of the Air Force.png"}
	};
	
	public static final int ENEMY_BASE_HEALTH = 1;
	public static final int ENEMY_BASE_DAMAGE = 1;
	public static final double ENEMY_BASE_MOVE_SPEED = .75;
	public static final double ENEMY_MISSILE_BASE_SPEED = -256;

	public static final String ENEMY_IMAGE = "/resources/enemy.png";
	public static final String PLAYER_IMAGE = "/resources/player.png";
	public static final String BG_IMAGE = "/resources/bg2.png";
	public static final String PLAYER_MISSILE_IMAGE = "/resources/playershot.png";
	public static final String ENEMY_MISSILE_IMAGE = "/resources/enemyshot.png";
	public static final String CLOUD_IMAGE = "/resources/cloud.png";

	public static final double CLOUD_SPEED = 35.0;
	
	public static ArrayList<Enemy> ENEMIES = new ArrayList<Enemy>();
	public static ArrayList<Missile> MISSILES = new ArrayList<Missile>();
	public static ArrayList<Cloud> CLOUDS = new ArrayList<Cloud>();	

	static Player player = Player.getPlayer();
	public static final Object[][] STATS = {
		{"Max HP: ", player.getMaxHp()},
		{"Current HP: ", player.getHp()},
		{"Damage: ", player.getDamage()},
		{"X Coor: ", (int) player.getXCoor()},
		{"Y Coor: ", (int) player.getYCoor()},
		{"Enemies: ", ENEMIES.size()},
		{"Exp: ", player.getExp()},
		{"Rank: ", RANKS[player.getRank()][0]},
	};
	public static ArrayList<JLabel> STATS_LABELS = new ArrayList<JLabel>();
	
}
