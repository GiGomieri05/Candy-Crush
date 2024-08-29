import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,MouseMotionListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final int TILE = 48;
	public static final int WIDTH = TILE*8, HEIGHT = TILE*8;
	public static final int SCALE = 2;
	
	public static final int FPS = 100/60;
	
	public Tabuleiro tabuleiro;
	
	public static int points = 0;
	
	public static JFrame frame;
	
	public BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public static boolean selected = false;
	public static int previousx = 0, previousy = 0;
	public static int nextx = -1, nexty = -1;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		//SET OBJECTS
		tabuleiro = new Tabuleiro();
	}
	
	public static void main(String[] args) {
		Game.frame = new JFrame("Candy Crush");
		Game game = new Game();
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void update() {
		
		tabuleiro.update();
		
		if(Game.selected && (Game.nextx != -1 && Game.nexty != -1)) {
			int posx1 = Game.previousx/48;
			int posy1 = Game.previousy/48;
			
			int pos2x = Game.nextx/48;
			int pos2y = Game.nexty/48;
			
			if((pos2x == posx1 + 1 || pos2x == posx1 - 1) &&
			pos2y == posy1 || pos2y == posy1 -1 ||  pos2y == posy1 +1 ) {
					
				 // EVITAR MOVIMENTOS NA DIAGONAL
				if((pos2x >= posx1 + 1 || pos2x <= posx1 - 1) &&
						(pos2y >= posy1 + 1 || pos2y <= posy1 - 1)	) {
					
					//System.out.println("nao pode mover");
					return;
				}
					
				int val1 = Tabuleiro.TABULEIRO[pos2x][pos2y];
				int val2 = Tabuleiro.TABULEIRO[posx1][posy1];
				Tabuleiro.TABULEIRO[posx1][posy1] = val1;
				Tabuleiro.TABULEIRO[pos2x][pos2y] = val2;
				Game.nextx = -1;
				Game.nexty = -1;
				Game.selected = false;
				//System.out.println("moveu");
			}else {
				//System.out.println("nao pode mover");
			}
		}else {		
			//System.out.println("nao pode mover");
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		
		//
		g.setColor(new Color(162,255,155));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		tabuleiro.render(g);
		//
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE, null);
		
		bs.show();
	}
	
	@Override
	public void run() {
		while(true) {
			update();
			render();
			try {
				Thread.sleep(FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(Game.selected == false) {
			Game.selected = true;
			Game.previousx = e.getX()/SCALE;
			Game.previousy = e.getY()/SCALE;
		}else {
			Game.nextx = e.getX()/SCALE;
			Game.nexty = e.getY()/SCALE;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
