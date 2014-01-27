package com.apcsz.anish_danny;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Constants {

	public static final int GRID_X = 640;
	public static final int GRID_Y = 480;
	public static boolean STILL_PLAYING = true;
	public static int LEVEL = 1;
	public static final int LEVEL_UP_HEALTH = 50;
	public static final int LEVEL_UP_DAMAGE = 2;
	public static final int LEVEL_UP_FIRE_INTERVAL = 10;
	public static final double LEVEL_UP_SPEED = 5;
	
	public static final int PLAYER_HEALTH = 200;
	public static final int PLAYER_DAMAGE = 8;
	public static final int PLAYER_SPEED = 150;
	public static int PLAYER_FIRING_INTERVAL = 450;
	public static double PLAYER_MISSILE_SPEED = 256;
	public static final int PLAYER_EXP = 0;
	public static final int PLAYER_RANK = 0; // Corresponds to the index in the array below
	public static Object RANKS[][] = {
		/* Title, Exp needed, Insignia image */
		{"Tries to fly", 0, "/resources/Ranks/01-Tries to fly.png"},
		{"Basic Airman", 25, "/resources/Ranks/02-Airman.png"},
		{"Airman First Class", 75, "/resources/Ranks/03-Airman First Class.png"},
		{"Senior Airman", 150, "/resources/Ranks/04-Senior Airman.png"},
		{"Staff Sergeant", 300, "/resources/Ranks/05-Staff Sergeant.png"},
		{"Technical Sergeant", 500, "/resources/Ranks/06-Technical Sergeant.png"},
		{"Master Sergeant", 800, "/resources/Ranks/07-Master Sergeant.png"},
		{"Senior Master Sergeant", 1200, "/resources/Ranks/08-Senior Master Sergeant.png"},
		{"Chief Master Sergeant", 1700, "/resources/Ranks/09-Chief Master Sergeant.png"},
		{"Command Chief Master Sergeant", 2300, "/resources/Ranks/10-Command Chief Master Sergeant.png"},
		{"Chief Master Sergeant of the Air Force", 3000, "/resources/Ranks/11-Chief Master Sergeant of the Air Force.png"}
	};
	
	public static final int ENEMY_BASE_HEALTH = 25;
	public static final int ENEMY_BASE_DAMAGE = 1;
	public static final double ENEMY_BASE_MOVE_SPEED = .75;
	public static final double ENEMY_MISSILE_BASE_SPEED = -256;
	
	public static final int BOSS_LEVEL = 10;
	public static final int BOSS_BASE_HEALTH = 3000;
	public static final int BOSS_BASE_DAMAGE = 25;
	public static final double BOSS_BASE_MOVE_SPEED = 1.5;

	public static final String ENEMY_IMAGE = "/resources/Sprites/enemy.png";
	public static final String PLAYER_IMAGE = "/resources/Sprites/player.png";
	public static final String BOSS_IMAGE = "/resources/Sprites/boss.png";
	public static final String PLAYER_MISSILE_IMAGE = "/resources/Sprites/playershot.png";
	public static final String ENEMY_MISSILE_IMAGE = "/resources/Sprites/enemyshot.png";
	public static final String BOSS_MISSILE_IMAGE = "/resources/Sprites/bossshot.png";
	public static final String BG_IMAGE = "/resources/Backgrounds/bg2.png";
	public static final String CLOUD_IMAGE = "/resources/Sprites/cloud.png";
	public static final String[] OBAMA_PICS = {
		"/resources/Obama/Confused Obama.jpg",
		"/resources/Obama/Talkative Obama.jpeg",
		"/resources/Obama/Pointing-at-you Obama.jpeg",
		"/resources/Obama/Serious Obama.jpg",
		"/resources/Obama/Yeah Obama.jpg",
		"/resources/Obama/Disappointed Obama.jpg",
		"/resources/Obama/What-to-do Obama.jpg",
		"/resources/Obama/Uh Oh Obama.jpg",
		"/resources/Obama/Look Up Obama.jpg",
		"/resources/Obama/Hold Up Obama.jpg",
		"/resources/Obama/Good Job Obama.jpg",
	};
	
	public static final String FRAME_1 = "/resources/Animation/explosion1.png";
	public static final String FRAME_2 = "/resources/Animation/explosion2.png";
	public static final String FRAME_3 = "/resources/Animation/explosion3.png";
	public static final String FRAME_4 = "/resources/Animation/explosion4.png";
	public static final String FRAME_5 = "/resources/Animation/explosion5.png";

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
		{"Level: ", LEVEL + ""},
		{"Enemies Left: ", ENEMIES.size() + ""},
		{"Exp: ", player.getExp() + ""},
		{"Rank: ", (String) RANKS[player.getRank()][0]},
	};
	public static ArrayList<JLabel> STATS_LABELS = new ArrayList<JLabel>();
	
}
