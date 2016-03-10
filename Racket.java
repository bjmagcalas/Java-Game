import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racket {
	
	int Y = 420;
	int WIDTH = 95;
	int HEIGHT = 10;
	int x = 0;
	int xa = 0;
	private Game game;

	public Racket(Game game) {
		this.game = game;
	}

	public void move() {
		//para d lumampas yung racket sa window
		//since di naman nagalaw ng x axis yung racket, naka initialize lang sila sa 0
		//incase na lumampas sa 0 yung value non at ng width ng window, di sila makakalagpas kasi babalik pa din sila sa 0 na location
		if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		//eto yung speed ng racket.
		//naka initialize sya sa o pag nirelease na yung arrow key dahil mag stop na sya
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			//eto naman kapag pinindot na yung left arrow key.
			//yung -8 yung bilis nya. 
			xa = -8;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 8;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		//para makuha yung top position ng rectangle para don na sya mismo magcollide
		return Y;
	}
}