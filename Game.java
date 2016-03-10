import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel {

	Ball ball = new Ball(this);
	Racket racket = new Racket(this);
	
	//para to sa speed ng bola na ginamit dn para sa variable ng score
	int ballSpeed = 1;
	public int GameState = 0;
	//0 idle
	//1 play
	// 2 game o
	 
	public Game() {
		addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				//nasa racket.java yung magdedetect ng pinindot na arrow keys 
				//so para ma-apply dito sa game yung pinindot na key don sa kabilang class, kelangan nya tong function na to
				racket.keyReleased(e);
			}			
			public void keyPressed(KeyEvent e) {
				//same here
				racket.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	private void move() {
		if(GameState==1)
		ball.move();
		racket.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//sa kabilang class naka initialize yung paint component ng ball at racket
			//kaya tong .paint(g2d) na to e para mapaint ng class na to yung dalawang yon
			ball.paint(g2d);
			racket.paint(g2d);
		
			g2d.setColor(Color.GRAY);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString(String.valueOf(getScore()), 10, 30);
		
		//Start screen
        if(GameState == 0){
        	g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            
         Font fnt = new Font("Verdana", Font.BOLD, 20);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Mini Tennis", getWidth() / 2 -65, getHeight() / 2 - 10);
            
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Press spacebar to start.", getWidth() / 2 -130, getHeight() / 2 + 20);
            gameState();
        }

        if(GameState == 2) {
       	   //Dim screen
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, getWidth(), getHeight());

            //ADd game over message
        Font fnt = new Font("Verdana", Font.BOLD, 20);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", getWidth() / 2 - 60, getHeight() / 2-20);    
           
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Your Score is " + getScore(), getWidth() / 2 - 83, getHeight() / 2 + 15);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Press space bar to restart", getWidth() / 2 -155, getHeight() / 2 + 65);
        }
        
		
	}
	
	void gameState(){
		//since tinawag yung gameState function sa paint component dito sya dederetso
		if(GameState==0){
			this.addKeyListener(new KeyListener(){
		
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}			
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
					if(key==32)
					GameState=1;
			}		
			});
		}
	}
	
	public void gameOver() {
		GameState=2;
		this.addKeyListener(new KeyListener(){
		
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}			
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
					if(key==32)
					eklat();
			}
		});
	}	
		void eklat(){
			//para to mareset lahat. speed at location ng bola
		ball.x=0;
		ball.y=0;
		ball.ya=1;
		ball.xa=1;
		ballSpeed=1;
		GameState=1;
		//move();	
		}
	
	public int getScore() {
		//kaya sya ballSpeed -1 kasi naka initialize na yung speed ng bola sa 1 so pag di nilagyan ng -1 magsisimula sa 1 yung score
		//isa pa, nag iincrement kasi ng speed yung bola ng 1 pixel so bawat collide ng bola sa racket balSpeed++ yon. nasa ball.java yung condtion na yon
		return ballSpeed - 1;
	}
	
	//throws InterruptedException. para to sa thread. di sya gagana kapag wala yon
	//dito na sa main nagrerepaint unlike sa gawa ni sir, sa game constructor sya nagrerepaint
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(370, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(16);
		}
	}
}