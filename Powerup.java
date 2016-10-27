import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public abstract class Powerup {
	private static final int DIAMETER = 30;
	private int x = 9999;
	private int y = 9999;
	private int ya = 1;
	private Boolean spawned = false;
	
	public Powerup(){
	}
	
	public void spawn(){
		this.x = (400 - 15);
		this.y = ((new Random()).nextInt(300) + 200);
		int direction = ((new Random())).nextInt(2) + 1;
		if(direction == 1){
			ya = 1;
		}
		else{
			ya = -1;
		}
		Sound.POWERUP.play();
		spawned = true;
	}
	
	public void move(){
		//reverses y direction once hits border
		if (y + ya < 0){
			ya = 1;
		}
		if (y + ya > 570 - DIAMETER){
			ya = -1;
		}
		y = y + ya;
	}
	
	public void despawn(){
		this.x = 9999;
		this.y = 9999;
		spawned = false;
	}
	
	//returns boundaries of ball
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	public int getDiameter(){
		return DIAMETER;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Boolean isSpawned(){
		return spawned;
	}
	
	public abstract void paint(Graphics2D g);
}
