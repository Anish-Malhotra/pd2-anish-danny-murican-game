
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cloud {

	private BufferedImage sprite;
	private double xCor;
	
	public Cloud(String imageRef, double xCor){
		sprite = ImageLoader.getImageLoader().getImage(imageRef);
		this.xCor = xCor;
	}
	
	public void update(long elapsedTime){
		if (xCor >= Constants.GRID_X) {
			xCor = 0.0;
		}
		xCor += (Constants.CLOUD_SPEED * elapsedTime) / 1e3;
	}
	
	public void draw(Graphics g){
		g.drawImage(sprite,(int)xCor,60,null);
	}

}
