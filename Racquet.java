import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private int x;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 60;
	private Game game;
	int y = 300 - 30;
	int ya = 0;
	private int up;
	private int down;
	
	//constructor of Racquet class
	public Racquet(Game game, int up, int down, int x) {
		this.game = game;
		this.up = up;
		this.down = down;
		this.x = x;
	}
	//moves racquet
	public void move() {
		if (y + ya > 0 && y + ya < game.getHeight() - HEIGHT)
			y = y + ya;
	}
	//makes a rectangle
	public void paint(Graphics2D g) {
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	//stops racquet from moving once key is released
	public void keyReleased(KeyEvent e) {
		ya = 0;
	}
	//moves racquet up or down depending on input wiht the speed of game
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == up)
			ya = -game.getSpeed();
		if (e.getKeyCode() == down)
			ya = game.getSpeed() + 1;
	}
	//gets bounds of racquet
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public int getX() {
		return x;
	}
}
