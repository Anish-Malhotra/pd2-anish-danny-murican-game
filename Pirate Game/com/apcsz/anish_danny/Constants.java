package com.apcsz.anish_danny;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Constants {

	public static final int GRID_X = 640;
	public static final int GRID_Y = 480;
	public static boolean STILL_PLAYING = true;
	public static int LEVEL = 1;
	public static final int LEVEL_UP_HEALTH = 0;
	public static final int LEVEL_UP_DAMAGE = 0;
	public static final double LEVEL_UP_SPEED = 0;
	
	public static final int PLAYER_HEALTH = 1000000;
	public static final int PLAYER_DAMAGE = 1;
	public static final int PLAYER_SPEED = 156;
	public static int PLAYER_FIRING_INTERVAL = 450;
	public static double PLAYER_MISSILE_SPEED = 256;
	public static final int PLAYER_EXP = 0;
	public static final int PLAYER_RANK = 0; // Corresponds to the index in the array below
	public static Object RANKS[][] = {
		/* Title, Exp needed, Insignia image */
		{"Tries to fly", 0, "/resources/Ranks/01-Tries to fly.png"},
		{"Basic Airman", 100, "/resources/Ranks/02-Airman.png"},
		{"Airman First Class", 200, "/resources/Ranks/03-Airman First Class.png"},
		{"Senior Airman", 300, "/resources/Ranks/04-Senior Airman.png"},
		{"Staff Sergeant", 400, "/resources/Ranks/05-Staff Sergeant.png"},
		{"Technical Sergeant", 500, "/resources/Ranks/06-Technical Sergeant.png"},
		{"Master Sergeant", 600, "/resources/Ranks/07-Master Sergeant.png"},
		{"Senior Master Sergeant", 700, "/resources/Ranks/08-Senior Master Sergeant.png"},
		{"Chief Master Sergeant", 800, "/resources/Ranks/09-Chief Master Sergeant.png"},
		{"Command Chief Master Sergeant", 900, "/resources/Ranks/10-Command Chief Master Sergeant.png"},
		{"Chief Master Sergeant of the Air Force", 1000, "/resources/Ranks/11-Chief Master Sergeant of the Air Force.png"}
	};
	
	public static final int ENEMY_BASE_HEALTH = 1;
	public static final int ENEMY_BASE_DAMAGE = 1;
	public static final double ENEMY_BASE_MOVE_SPEED = .75;
	public static final double ENEMY_MISSILE_BASE_SPEED = -256;
	
	public static final int BOSS_BASE_HEALTH = 1;
	public static final int BOSS_BASE_DAMAGE = 1;
	public static final double BOSS_BASE_MOVE_SPEED = 0.01;
	public static final double BOSS_MISSILE_BASE_SPEED = -256;

	public static final String ENEMY_IMAGE = "/resources/Sprites/enemy.png";
	public static final String PLAYER_IMAGE = "/resources/Sprites/player.png";
	public static final String BOSS_IMAGE = "/resources/Sprites/boss.png";
	public static final String PLAYER_MISSILE_IMAGE = "/resources/Sprites/playershot.png";
	public static final String ENEMY_MISSILE_IMAGE = "/resources/Sprites/enemyshot.png";
	public static final String BOSS_MISSILE_IMAGE = "/resources/Sprites/bossshot.png";
	public static final String BG_IMAGE = "/resources/Backgrounds/bg2.png";
	public static final String CLOUD_IMAGE = "/resources/Sprites/cloud.png";
	
	public static final String FRAME_1 = "/resources/Animation/01.png";
	public static final String FRAME_2 = "/resources/Animation/02.png";
	public static final String FRAME_3 = "/resources/Animation/03.png";
	public static final String FRAME_4 = "/resources/Animation/04.png";
	public static final String FRAME_5 = "/resources/Animation/05.png";

	public static final double CLOUD_SPEED = 35.0;
	
	public static ArrayList<Enemy> ENEMIES = new ArrayList<Enemy>();
	public static ArrayList<Missile> MISSILES = new ArrayList<Missile>();
	public static ArrayList<Cloud> CLOUDS = new ArrayList<Cloud>();	

	static Player player = Player.getPlayer();
	public static final String[][] STATS = {
		{"Max HP: ", player.getMaxHp() + ""},
		{"Current HP: ", player.getHp() + ""},
		{"Damage: ", player.getDamage() + ""},
		{"Speed: ", player.speed + " mph"},
		{"X Coor: ", (int) player.getXCoor() + ""},
		{"Y Coor: ", (int) player.getYCoor() + ""},
		{"Enemies: ", ENEMIES.size() + ""},
		{"Exp: ", player.getExp() + ""},
		{"Rank: ", (String) RANKS[player.getRank()][0]},
	};
	public static ArrayList<JLabel> STATS_LABELS = new ArrayList<JLabel>();
	
}
