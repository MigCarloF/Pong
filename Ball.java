import java.awt.Graphics2D;
import java.awt.Rectangle;
public class Ball {
	private static final int DIAMETER = 30;
	private Game game;
	int x = 400 - 15;
	int y = 300 - 15;
	int xa = 2;
	int ya = 2;

	public Ball(Game game) {
		this.game= game;
	}
	public int getDiameter() {
		return DIAMETER;
	}
	
	void move() {
		//calls p2score once ball reaches leftmost of window
		if (x + xa < 0){
			game.p2Score();
		}
		//calls p1score once ball reaches rightmost of window
		if (x + xa > game.getWidth() - DIAMETER){
			game.p1Score();
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
		//reverses x direction once ball hits a racquet
		if (collisionRacquet()){
			Sound.impact().play();
			game.addSpeed();
			xa = game.getSpeed();
			x = game.racquet.getX() + DIAMETER / 4;
		}
		else if (collisionRacquet2()){
			Sound.impact().play();
			game.addSpeed();
			xa = -game.getSpeed();
			x = game.racquet2.getX() - DIAMETER - (DIAMETER / 4);
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

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
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
