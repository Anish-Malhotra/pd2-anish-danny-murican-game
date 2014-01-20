package com.apcsz.anish_danny;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;



public class ImageLoader {
	
	private HashMap<String,Sprite> storedstoredSprites = new HashMap<String,Sprite>();
	private static ImageLoader single = new ImageLoader();
	
	public static ImageLoader getImageLoader() {
		return single;
	}

	public Sprite getSprite2(String ref) {
		if (storedstoredSprites.get(ref) != null) {
			return (Sprite) storedstoredSprites.get(ref);
		}
		
		BufferedImage sourceImage = null;
		
		try {
			URL url = this.getClass().getClassLoader().getResource(ref);
			
			if (url == null) {
				System.out.println("can't find file");
				System.exit(0);
			}

			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
		}

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(),Transparency.BITMASK);

		image.getGraphics().drawImage(sourceImage,0,0,null);

		Sprite sprite = new Sprite(image);
		storedstoredSprites.put(ref,sprite);
		
		return sprite;
	}
	
}
