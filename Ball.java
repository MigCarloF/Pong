import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
public class Ball {
	private static final int DIAMETER = 30;
	private Game game;
	int x = 400 - 15;
	int y = 300 - 15;
	int xa = 2;
	int ya = 2;
	int yastore = -5;
	private Boolean speedActive = false;
	Graphics2D g;
	
	public Ball(Game game) {
		this.game = game;
	}
	
	Powerup pu = new PowerupSpeed();
	
	public int getDiameter() {
		return DIAMETER;
	}
	
	void move() {
		if(pu.isSpawned() == true){
			pu.move();
		}
		//calls p2score once ball reaches leftmost of window
		if (x + xa < 0){
			game.p2Score();
			pu.despawn();
			speedActive = false;
		}
		//calls p1score once ball reaches rightmost of window
		if (x + xa > game.getWidth() - DIAMETER){
			game.p1Score();
			pu.despawn();
			speedActive = false;
		}
		//reverses y direction once ball hits border
		if (y + ya < 0){
			Sound.wallimpact().play();
			ya = game.getSpeed();
		}
		if (y + ya > game.getHeight() - DIAMETER){
			Sound.wallimpact().play();
			ya = -game.getSpeed();
		}
		if(collisionPowerup() == true){
			ya = 0;
			speedActive = true;
			if(xa < 0){
				xa = -game.getSpeed() - 2;
			}
			else{
				xa = game.getSpeed() + 2;
			}
			Sound.POWERGET.play();
			pu.despawn();
		}
		//reverses x direction once ball hits a racquet
		if (collisionRacquet()){
			Sound.impact().play();
			//stores y acceleration to reset y after powerup shot is blocked
			if(speedActive == false){
				yastore = ya;
			}
			else{
				ya = yastore;
				xa = game.getSpeed();
				speedActive = false;
			}
			game.addSpeed();
			xa = game.getSpeed();
			x = game.racquet.getX() + DIAMETER / 3;
			if(pu.isSpawned() == false && (new Random()).nextInt(100) + 1 < 31){
				pu.spawn();
			}
		}
		
		else if (collisionRacquet2()){
			Sound.impact().play();
			if(speedActive == false){
				yastore = ya;
			}
			else{
				ya = yastore;
				xa = xa / game.getSpeed();
				speedActive = false;
			}
			game.addSpeed();
			xa = -game.getSpeed();
			x = game.racquet2.getX() - DIAMETER - (DIAMETER / 4);
			if(pu.isSpawned() == false && (new Random()).nextInt(100) + 1 < 31){
				pu.spawn();
			}
		}
		x = x + xa;
		y = y + ya;
	}
	
	//cleans up above if statements
	private boolean collisionRacquet() {
		return game.racquet.getBounds().intersects(getBounds());
	}
	
	private boolean collisionRacquet2() {
		return game.racquet2.getBounds().intersects(getBounds());
	}

	private boolean collisionPowerup() {
		return pu.getBounds().intersects(getBounds());
	}
	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
		if(pu.isSpawned() == true){
			pu.paint(g);
		}
	}
	
	//returns boundaries of ball
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	//resets position
	public void reset() {
		xa = game.getSpeed();
		ya = game.getSpeed();
		
	}
}
