
import java.awt.image.BufferedImage;

public class Animation {
	protected BufferedImage[] frames;
	private double frameDuration;
	
	public Animation(double frameDuration, BufferedImage... frames){
		this.frameDuration = frameDuration;
		this.frames = frames;
	}
	
	public BufferedImage getFrame(double stateTime){
		 int frameNumber = (int) (stateTime / frameDuration);
         frameNumber %= frames.length;
         return frames[frameNumber];
	}
}
