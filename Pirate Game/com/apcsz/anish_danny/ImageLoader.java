package com.apcsz.anish_danny;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;



public class ImageLoader {
	
	private HashMap<String,BufferedImage> storedstoredSprites = new HashMap<String,BufferedImage>();
	private static ImageLoader single = new ImageLoader();
	
	public static ImageLoader getImageLoader() {
		return single;
	}

	public BufferedImage getImage(String ref) {
		if (storedstoredSprites.get(ref) != null) {
			return storedstoredSprites.get(ref);
		}
		
		BufferedImage sourceImage = null;
		
		try {
			sourceImage = ImageIO.read(Game.class.getResourceAsStream(ref));
		} catch (IOException e) {
			System.out.println("Can't find file");
			System.exit(0);
		}
		
		storedstoredSprites.put(ref,sourceImage);
		
		return sourceImage;
	}
	
}
