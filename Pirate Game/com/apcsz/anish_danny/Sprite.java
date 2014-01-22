package com.apcsz.anish_danny;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {

	private BufferedImage image;
	
	public Sprite(BufferedImage image) {
		this.image = image;
	}
	
	public int getWidth() {
		return image.getWidth(null);
	}


	public int getHeight() {
		return image.getHeight(null);
	}
	
	public void draw(Graphics g,int x,int y) {
		g.drawImage(image,x,y,null);
	}

}

/*
	Ship target = Player.getPlayer();
+    double distance = this.getYCor() - target.getYCor();
+    double cannonballYSpd = Constants.ENEMY_CANNONBALL_BASE_SPEED;
+    double time = distance / cannonballYSpd;
+    double requiredZSpd = 9.8 * time / 2;
*/