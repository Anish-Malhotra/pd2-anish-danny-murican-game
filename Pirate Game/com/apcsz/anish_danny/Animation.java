package com.apcsz.anish_danny;

import java.awt.image.BufferedImage;

public class Animation {
	private BufferedImage[] frames;
	private double frameDuration;
	
	public Animation(double frameDuration, BufferedImage... frames){
		this.frameDuration = frameDuration;
		this.frames = frames;
	}
	
	public BufferedImage getFrame(double stateTime, int mode){
		 int frameNumber = (int) (stateTime / frameDuration);
         frameNumber %= frames.length;
         return frames[frameNumber];
	}
}
