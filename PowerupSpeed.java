import java.awt.Color;
import java.awt.Graphics2D;

public class PowerupSpeed extends Powerup {
	public PowerupSpeed(){
	}
	
	public void paint(Graphics2D g){
			g.setColor(Color.RED);
			g.fillOval(getX(), getY(), getDiameter(), getDiameter());
	}
}
