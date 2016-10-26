import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;
@SuppressWarnings("serial")
public class Game extends JPanel {
	public int p1score = 0;
	public int p2score = 0;
	private int hitCtr = 3;
	private int speed = 2;
	private int cd = 0;
	private String go;
	private String cdstr;
	
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this, KeyEvent.VK_W, KeyEvent.VK_S, 30);
	Racquet racquet2 = new Racquet(this, KeyEvent.VK_UP, KeyEvent.VK_DOWN, 730);
	
	public Game() {
		//this listens for key input and calls racquet if a button is pressed
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
				racquet2.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
				racquet2.keyPressed(e);
			}
		});
		setFocusable(true);
		
		//plays background music
		int n = (new Random()).nextInt(3) + 1;
		if(n == 1){//if I do this in Sound, the sound stops midway
			Sound.BACK1.loop();
		}
		else if(n == 2){
			Sound.BACK2.loop();
		}
		else if(n == 3){
			Sound.BACK3.loop();
		}
	}
	//called in main to move everything that moves
	private void move() {
		ball.move();
		racquet.move();
		racquet2.move();
	}
	
	//draws all sprites to be seen in canvas once paint and repaint are called
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.setBackground(new Color(50, 50, 50));
		g.setFont(new Font("Cambria", Font.BOLD, 15));
		g.setColor(Color.WHITE);
		g.drawString("Player 1 : " + Integer.toString(p1score), 10, 15);
		g.drawString("Player 2 : " + Integer.toString(p2score), this.getWidth() - 100, 15);
		g.setColor(new Color(80, 80, 80));
		g.drawLine(399, 0, 399, 600);
		g.setColor(Color.RED);
		g.setFont(new Font("Cambria", Font.BOLD, 200));
		g.drawString(cdstr, 350, 200);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Cambria", Font.BOLD, 200));
		g.drawString(go, 260, 200);
		g.setFont(new Font("Cambria", Font.BOLD, 300));
		g.setColor(new Color(80, 80, 80));
		g.drawString(Integer.toString(p1score), 150,400);
		g.drawString(Integer.toString(p2score), 458,400);
		g.setColor(Color.PINK);
		ball.paint(g2d);
		g.setColor(Color.BLUE);
		racquet.paint(g2d);
		racquet2.paint(g2d);
	}
	
	//resets position
	public void resetPos() {
		speed = 2;
		ball.reset();
		ball.x = (this.getWidth() / 2) - (ball.getDiameter() / 2);
		ball.y = (this.getHeight() / 2) - (ball.getDiameter() / 2);
		racquet.y = 300 - 30;
		racquet2.y = 300 - 30;
	}
	
	//scores for player 1 and checks for win
	public void p1Score() {
		Sound.SCORE.play();
		p1score++;
		if(p1score == 5){
			int choice = JOptionPane.showConfirmDialog(this, "Play again?", " Player 1 Wins!", JOptionPane.YES_NO_OPTION);
			if(choice == JOptionPane.YES_OPTION){
				resetPos();
				p1score = 0;
				p2score = 0;
			}
			else{
				System.exit(0);
			}
		}
		resetPos();
	}
	
	//scores player 2 and checks for win
	public void p2Score() {
		Sound.SCORE.play();
		p2score++;
		if(p2score == 5){
			int choice = JOptionPane.showConfirmDialog(this, "Play again?", " Player 2 Wins!", JOptionPane.YES_NO_OPTION);
			if(choice == JOptionPane.YES_OPTION){
				resetPos();
				p1score = 0;
				p2score = 0;
			}
			else{
				System.exit(0);
			}
		}
		resetPos();
	}
	
	//increases speed after every 3 returns
	public void addSpeed(){
		if(hitCtr == 0){
			speed++;
			hitCtr = 3;
		}
		else{
			hitCtr--;
		}
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public static void main(String[] args) throws InterruptedException {
		//adds a frame, sets size, makes visible, allows to close, and not resizable
		JFrame frame = new JFrame("PONG: Origins");
		Game game = new Game();
		game.cdstr = new String("");
		game.go = new String("");
		frame.add(game);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		int score1 = 0;
		int score2 = 0;
		//initial countdown
		game.cd = 3;
		while(game.cd > 0){
			game.cdstr = new String(Integer.toString(game.cd));
			game.repaint();
			game.cd--;
			Sound.COUNTDOWN.play();
			Thread.sleep(1000);//i'd have made another function class but Thread.sleep will error
		}
		game.cdstr = new String("");
		game.go = new String("GO!");
		game.repaint();
		Sound.GO.play();
		Thread.sleep(1000);
		game.go = new String("");
		//game proper
		while (true) {
			game.move();
			game.repaint();
			//Will check if game should countdown after score
			if(game.p1score > score1){
				game.cd = 3;
				while(game.cd > 0){
					game.cdstr = new String(Integer.toString(game.cd));
					game.repaint();
					game.cd--;
					Sound.COUNTDOWN.play();
					Thread.sleep(1000);//i'd have made another function class but Thread.sleep will error
				}
				game.cdstr = new String("");
				game.go = new String("GO!");
				game.repaint();
				Sound.GO.play();
				Thread.sleep(1000);
				game.go = new String("");
				score1++;
			}
			else if(game.p2score > score2){
				game.cd = 3;
				while(game.cd > 0){
					game.cdstr = new String(Integer.toString(game.cd));
					game.repaint();
					game.cd--;
					Sound.COUNTDOWN.play();
					Thread.sleep(1000);//i'd have made another function class but Thread.sleep will error
				}
				game.cdstr = new String("");
				game.go = new String("GO!");
				game.repaint();
				Sound.GO.play();
				Thread.sleep(1000);
				game.go = new String("");
				score2++;
			}
			else{
				Thread.sleep(10);
			}
		}
	}
}
