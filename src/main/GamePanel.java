package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Player;
import entity.Skeleton;
import entity.Entity;
import entity.Ghost;
import entity.Zombie;
import entity.Slime;
import entity.SkeletonBoss;
import entity.GhostBoss;
import tile.TileManager;
import object.SuperObject;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	//Screen Settings
	final int originalTileSize = 32; // 32x32 titles
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 96x96 tile
	// use 8 by 7 for now, 8 by 6 later
	public final int maxScreenCol = 8;  
	public final int maxScreenRow = 7;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 672 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50; //14
	public final int maxWorldRow = 50; //16
	
	
	// FPS
	int FPS = 60;
	
	
	// SYSTEM
	public TileManager tileM = new TileManager(this);
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
	
	public Zombie zombie = new Zombie(null);
	
	public SkeletonBoss skeletonBoss = new SkeletonBoss(null);
	
	public Slime slime = new Slime(null);
	
	public GhostBoss ghostBoss = new GhostBoss(null);
	
	public Skeleton skeleton = new Skeleton(null);
	
	
	// OBJECT DISPLAY LIMIT (increase if necessary)
	public SuperObject obj[] = new SuperObject[10];
	
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int menuState = 2;
	public final int dialogueState = 3;
	public final int dialogueStateObject = 4;
	public final int gameOverState = 5;
	
	public final int battleStateZombie = 6;
	public final int battleStateSlime = 7;
	public final int battleStateGhost = 8;
	public final int battleStateSkeleton = 9;
	
	public final int battleStateSkeletonBoss = 10;
	public final int battleStateGhostBoss = 11;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		assetSetter.setObject();
		
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
	    // TEMP
	    
	    /*
	    if (gp.tileM.tile[tileNum].hasEncountersGrass) {
	        Random rand = new Random();
	        int chance = rand.nextInt(100); // Random number between 0-99
	        if (chance < 3) { // 3% encounter rate
	        	
	        	gp.gameState = gp.battleStateZombie;
	        	gp.gameState = gp.battleStateSlime;
	        	
	        }
	    }
	    */
	    
	    
	    if (gp.tileM.tile[tileNum].hasEncountersGrass) {
	        Random rand = new Random();
	        int chance = rand.nextInt(100); // 0–99

	        if (chance < 3) { // 3% encounter rate
	            if (rand.nextBoolean()) {
	                gp.gameState = gp.battleStateZombie;
	            } else {
	                gp.gameState = gp.battleStateSlime;
	            }
	        }
	    }
	    
	    if (gp.tileM.tile[tileNum].hasEncountersWood) {
	        Random rand = new Random();
	        int chance = rand.nextInt(100); // 0–99

	        if (chance < 3) { // 3% encounter rate
	            if (rand.nextBoolean()) {
	                gp.gameState = gp.battleStateGhost;
	            } else {
	                gp.gameState = gp.battleStateSkeleton;
	            }
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
