import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tabuleiro {

	public static final int WIDTH = 8, HEIGHT = 8;
	public static int[][] TABULEIRO;
	
	public static int DOCE0 = 0, DOCE1 = 1, DOCE2 = 2, DOCE3 = 3, DOCE4=4, DOCE5 = 5, DOCE6 = 6;
	
	public BufferedImage DOCE0SPRITE = Tabuleiro.getSprite(455,332,160,160);
	public BufferedImage DOCE1SPRITE = Tabuleiro.getSprite(624,335,160,160);
	public BufferedImage DOCE2SPRITE = Tabuleiro.getSprite(602,839,160,160);
	public BufferedImage DOCE3SPRITE = Tabuleiro.getSprite(768,839,160,160);
	public BufferedImage DOCE4SPRITE = Tabuleiro.getSprite(434,1067,160,160);
	public BufferedImage DOCE5SPRITE = Tabuleiro.getSprite(604,1345,160,160);
	public BufferedImage DOCE6SPRITE = Tabuleiro.getSprite(1417,966,160,160);
	
	public static BufferedImage spritesheet;
	
	public Tabuleiro() {
		TABULEIRO = new int[WIDTH][HEIGHT];
		
		for(int x = 0; x< WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				TABULEIRO[x][y] = new Random().nextInt(7);
			}
		}
	}
	
	public static BufferedImage getSprite(int x,int y, int width, int height) {
		if(spritesheet == null) {
			try {
				spritesheet = ImageIO.read(Tabuleiro.class.getResource("/spritesheet.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return spritesheet.getSubimage(x,y,width,height);
	}
	
	public void update() {
		ArrayList<Candy> combos = new ArrayList<Candy>(); 
		for(int yy = 0; yy < HEIGHT; yy++){
			if(combos.size() >= 3) {
				//tenho um combo
				for(int i = 0; i< combos.size(); i++) {
					int xtemp = combos.get(i).x;
					int ytemp = combos.get(i).y;
					TABULEIRO[xtemp][ytemp] = new Random().nextInt(3);
				}
				combos.clear();
				//System.out.println("pontos++");
				Game.points++;
				Game.frame.setTitle("Candy Crush | Pontos: "+Game.points);
				return;
			}
			combos.clear();
			for(int xx = 0; xx<WIDTH;xx++) {
				int cor = TABULEIRO[xx][yy];
				if(combos.size() >= 3) {
					//tenho um combo
					for(int i = 0; i< combos.size(); i++) {
						int xtemp = combos.get(i).x;
						int ytemp = combos.get(i).y;
						TABULEIRO[xtemp][ytemp] = new Random().nextInt(3);
					}
					combos.clear();
					//System.out.println("pontos++");
					Game.points++;
					Game.frame.setTitle("Candy Crush | Pontos: "+Game.points);
					return;
				}
				if(combos.size() == 0) {
					combos.add(new Candy(xx,yy,cor));
				}else if(combos.size() > 0) {
					if(combos.get(combos.size() - 1).CANDY_TYPE == cor) {
						combos.add(new Candy(xx,yy,cor));
					}else {
						combos.clear();
						combos.add(new Candy(xx,yy,cor));
					}
				}
			}
		}
		
		combos = new ArrayList<Candy>(); 
		for(int xx = 0; xx < WIDTH; xx++){
			if(combos.size() >= 3) {
				//tenho um combo
				for(int i = 0; i< combos.size(); i++) {
					int xtemp = combos.get(i).x;
					int ytemp = combos.get(i).y;
					TABULEIRO[xtemp][ytemp] = new Random().nextInt(3);
				}
				combos.clear();
				//System.out.println("pontos++");
				Game.points++;
				Game.frame.setTitle("Candy Crush | Pontos: "+Game.points);
				return;
			}
			combos.clear();
			for(int yy = 0; yy<HEIGHT;yy++) {
				int cor = TABULEIRO[xx][yy];
				if(combos.size() >= 3) {
					//tenho um combo
					for(int i = 0; i< combos.size(); i++) {
						int xtemp = combos.get(i).x;
						int ytemp = combos.get(i).y;
						TABULEIRO[xtemp][ytemp] = new Random().nextInt(3);
					}
					combos.clear();
					//System.out.println("pontos++");
					Game.points++;
					Game.frame.setTitle("Candy Crush | Pontos: "+ Game.points);
					return;
				}
				if(combos.size() == 0) {
					combos.add(new Candy(xx,yy,cor));
				}else if(combos.size() > 0) {
					if(combos.get(combos.size() - 1).CANDY_TYPE == cor) {
						combos.add(new Candy(xx,yy,cor));
					}else {
						combos.clear();
						combos.add(new Candy(xx,yy,cor));
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int x = 0; x< WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				g.setColor(Color.white);
				g.drawRect(x*Game.TILE, y*Game.TILE, Game.TILE, Game.TILE);
				
				int doce = TABULEIRO[x][y];
				if(doce == DOCE0) {
					//g.setColor(Color.red);
					//g.fillRect(x*Game.TILE + 12, y*Game.TILE + 12, 24, 24);
					g.drawImage(DOCE0SPRITE, x*Game.TILE+12, y*Game.TILE+12,25,25, null);
				}
				if(doce == DOCE1) {
					//g.setColor(Color.green);
					//g.fillRect(x*Game.TILE + 12, y*Game.TILE + 12, 24, 24);	
					g.drawImage(DOCE1SPRITE, x*Game.TILE+12, y*Game.TILE+12,25,25, null);
				}
				if(doce == DOCE2) {
					//g.setColor(Color.yellow);
					//g.fillRect(x*Game.TILE + 12, y*Game.TILE + 12, 24, 24);
					g.drawImage(DOCE2SPRITE, x*Game.TILE+12, y*Game.TILE+12,25,25, null);
				}if(doce == DOCE3) {
					//g.setColor(Color.yellow);
					//g.fillRect(x*Game.TILE + 12, y*Game.TILE + 12, 24, 24);
					g.drawImage(DOCE3SPRITE, x*Game.TILE+12, y*Game.TILE+12,25,25, null);
				}if(doce == DOCE4) {
					//g.setColor(Color.yellow);
					//g.fillRect(x*Game.TILE + 12, y*Game.TILE + 12, 24, 24);
					g.drawImage(DOCE4SPRITE, x*Game.TILE+12, y*Game.TILE+12,25,25, null);
				}if(doce == DOCE5) {
					//g.setColor(Color.yellow);
					//g.fillRect(x*Game.TILE + 12, y*Game.TILE + 12, 24, 24);
					g.drawImage(DOCE5SPRITE, x*Game.TILE+12, y*Game.TILE+12,25,25, null);
				}if(doce == DOCE6) {
					//g.setColor(Color.yellow);
					//g.fillRect(x*Game.TILE + 12, y*Game.TILE + 12, 24, 24);
					g.drawImage(DOCE6SPRITE, x*Game.TILE+12, y*Game.TILE+12,25,25, null);
				}
				
				if(Game.selected) {
					int posx = Game.previousx/Game.TILE;
					int posy = Game.previousy/Game.TILE;
					g.setColor(Color.cyan);
					g.drawRect(posx*Game.TILE, posy*Game.TILE, Game.TILE, Game.TILE);
				}
			}
		}
	}
}
