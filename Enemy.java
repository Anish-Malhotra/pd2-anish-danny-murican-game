
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Plane {

	Random r = new Random();
	int angle;
	double shootFreq;
	private Animation animation;
	private double stateTime;
	private boolean playerCollision = true;
	
	public Enemy(int maxHp, int damage, double xCor, double yCor, double speed,String ref) {
		super(maxHp, damage, xCor, yCor, speed, ref);
		Constants.ENEMIES.add(this);
		angle = 90;
		shootFreq = .75;
		animation = new Animation(500, ImageLoader.getImageLoader().getImage(Constants.FRAME_1),
									   ImageLoader.getImageLoader().getImage(Constants.FRAME_2),
									   ImageLoader.getImageLoader().getImage(Constants.FRAME_3),
									   ImageLoader.getImageLoader().getImage(Constants.FRAME_4),
									   ImageLoader.getImageLoader().getImage(Constants.FRAME_5));
	}

	public void update(long elapsedTime){
		stateTime += elapsedTime;
		this.xCoor -= 1;
		if (xCoor <= 0) {
			xCoor = Constants.GRID_X + sprite.getWidth();
		}
		this.yCoor += Constants.ENEMY_BASE_MOVE_SPEED * Math.sin(angle * Math.PI / 180);
		angle++;
	}
	
	public void shoot() {
		int check = r.nextInt(100);
		if (check < shootFreq) {
			new EnemyMissile(this.getDamage(), this.xCoor, this.yCoor+sprite.getHeight()/2);
		}
	}
	
	public boolean collide(Entity other) {
		if (canCollide(other)) {
			if (other instanceof PlayerMissile) {
				loseHp(other.getDamage());
				if (getHp() <= 0) {
					playerCollision = false;
					destroy();
				}
				other.destroy();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void destroy() {
		Constants.ENEMIES.remove(this);
		Player.getPlayer().gainExp((!playerCollision)? Constants.LEVEL*5 : Constants.LEVEL*3);
		for(BufferedImage frame:animation.frames){
			Game.getGame().getGraphics().drawImage(animation.getFrame(stateTime),(int)this.xCoor,(int)this.yCoor,null);
		}
	}
	
}
