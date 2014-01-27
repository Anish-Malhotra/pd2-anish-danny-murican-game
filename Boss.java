
public class Boss extends Enemy{
	
	public Boss(int maxHp, int damage, double xCor, double yCor, double speed) {
		super(maxHp, damage, xCor, yCor, speed, Constants.BOSS_IMAGE);
		super.shootFreq = 1;
	}
	
	public void update(long elapsedTime){
		this.xCoor -= 3;
		if (xCoor <= 0) {
			xCoor = Constants.GRID_X + sprite.getWidth();
		}
		this.yCoor += Constants.BOSS_BASE_MOVE_SPEED * Math.sin(angle * Math.PI / 180);
		angle++;
		System.out.println(xCoor);
	}
	
	public void shoot() {
		int check = r.nextInt(100);
		if (check < shootFreq) {
			new BossMissile(this.getDamage(), this.xCoor, this.yCoor+sprite.getHeight()/2-40);
			new BossMissile(this.getDamage(), this.xCoor, this.yCoor+sprite.getHeight()/2+8);
		}
	}
}
