package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import entity.Skeleton;
import entity.Entity;
import entity.Ghost;
import tile.TileManager;
import object.SuperObject;
import java.util.Random;

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
	KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound soundEffect = new Sound();
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	//public Entity enemy[] = new Entity[20];
	public Ghost ghost = new Ghost(null);
	// OBJECT DISPLAY LIMIT (increase if necessary)
	public SuperObject obj[] = new SuperObject[10];
	
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int menuState = 2;
	public final int dialogueState = 3;
	public final int dialogueStateObject = 4;
	public final int battleState = 5;
	
	
	
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
		//  CHANGE ------------- playMusic(0);
		// DEFAULT STATE
		gameState = titleState;
		
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
		
		if(gameState == playState) {
			player.update();
		}
		if(gameState == menuState) {
			// later
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		/* -------------- DRAW ORDER HERE MATTERS -------------- */
		
		// TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2, keyH);
		}
		
		// OTHERS
		else {
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
		
		//UI
		ui.draw(g2, keyH);
		
		//g2.dispose();
		}
		
		
		
	}
	
	
	//CHECKS FOR RANDOM COMBAT ENCOUNTER ON CERTAIN TILES
	public void checkForEncounter(GamePanel gp) {
	    int playerCol = gp.player.worldX / gp.tileSize;
	    int playerRow = gp.player.worldY / gp.tileSize;
	    int tileNum = gp.tileM.mapTileNum[playerCol][playerRow];

	    if (gp.tileM.tile[tileNum].hasEncounters) {
	        Random rand = new Random();
	        int chance = rand.nextInt(100); // Random number between 0-99
	        if (chance < 10) { // 10% encounter rate
	            gp.gameState = gp.battleState;
	        }
	    }
	}
	

	
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		soundEffect.setFile(i);
		soundEffect.play();
	}
	
	
}
