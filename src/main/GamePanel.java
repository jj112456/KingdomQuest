package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import tile.TileManager;
import object.SuperObject;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	//Screen Settings
	final int originalTileSize = 32; // 32x32 titles
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 96x96 tile
	public final int maxScreenCol = 8;
	public final int maxScreenRow = 7;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 672 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 14;
	public final int maxWorldRow = 16;
	
	
	// FPS
	int FPS = 60;
	
	
	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound sound = new Sound();
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	Thread gameThread;
	
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	
	// OBJECT DISPLAY LIMIT (increase if necessary)
	public SuperObject obj[] = new SuperObject[10];
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		assetSetter.setObject();
		
		// plays the integer in the sound array
		playMusic(0);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (gameThread != null) {
			//System.out.println("The game loop is running");
			
			//long currentTime = System.nanoTime();
			//System.out.println("Current time is: " + currentTime);
			
			// 1. Update info like character positions
			update();
			// 2. Draw the screen with the updated information
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}	
	}
	
	
	public void update() {
		player.update();
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		// Draw order
		
		// draws tiles
		tileM.draw(g2);
		
		//draws world objects
		for(int i = 0; i < obj.length; i++) {
			// check array for objects
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// draws player
		player.draw(g2);
		
		//g2.dispose();
	}
	
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
	
}
