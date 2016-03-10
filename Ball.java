import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	int DIAMETER = 30;
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private Game game;
	int Gamestate=0;

	//parang ano to, para maimplement yung game. or ma inherit nitong file na to yung game.java. para connected sila
	public Ball(Game game) {
		this.game= game;
	}

	void move() {
		boolean changeDirection = true;
		if (x + xa < 0)
			xa = game.ballSpeed;
		if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.ballSpeed;
		if (y + ya < 0)
			ya = game.ballSpeed;
		if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		if (collision()){
			ya = -game.ballSpeed;
			y = game.racket.getTopY() - DIAMETER;
			game.ballSpeed++;
		}else
			changeDirection = false;
		x = x + xa;
		y = y + ya;
	}		

	private boolean collision() {
		//para malaman kung nag collide na yung bola sa rectangle
		return game.racket.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		//30 by 30 yung bola. kaya ako nag declare sa taas e dahil para kapag binago yung diameter ng bola e isang baguhan nalang
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}