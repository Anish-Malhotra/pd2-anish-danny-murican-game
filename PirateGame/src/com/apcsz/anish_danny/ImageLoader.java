package com.apcsz.anish_danny;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class ImageLoader {
	
	private HashMap<String,BufferedImage> storedImages = new HashMap<String,BufferedImage>();
	private static ImageLoader single = new ImageLoader();
	
	public static ImageLoader getImageLoader() {
		return single;
	}

	public BufferedImage getImage(String place) {
		BufferedImage image=null;
		if (storedImages.containsKey(place))
			return storedImages.get(place);
		else{
			try{
				URL location =  this.getClass().getClassLoader().getResource(place);
				if(location == null)
					System.exit(0);
				
				image = ImageIO.read(location);
			}
			catch(Exception e){
				System.out.println(e);
				System.exit(0);
			}

			storedImages.put(place,image);
			return storedImages.get(place);
		}
	}
	
	
}
