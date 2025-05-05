package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font FF6Font;
	
	BufferedImage buttonImage;
	public boolean messageOn = false;
	public String message = "";
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	public String currentText = "";
	
	
	
	// new stuff
	public String battleMessage = "";
	
	public boolean messageActive = false;
	private int messageCounter = 0;
	private int messageDuration = 120; // 2 seconds at 60 FPS

	
	public int commandNum = 0;
	
	private BufferedImage cursorImage;

	private BufferedImage playerBattleImage;
	private BufferedImage enemyBattleImage;
	
	private BufferedImage titleBackground;

	
	
	// Create Player and enemy here?
	
	public void setEnemyBattleImage(BufferedImage image) {
	    this.enemyBattleImage = image;
	}

	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		
		InputStream is = getClass().getResourceAsStream("/font/final_fantasy_36_font.ttf");
		try {
			FF6Font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*---------- IMPLEMENT CONTROL SCREEN WITH BUTTON IMAGES ---------- */
		
		
		try {
		    InputStream is2 = getClass().getResourceAsStream("/ui/cursor.png");
		    cursorImage = ImageIO.read(is2);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	    try {
	        InputStream is3 = getClass().getResourceAsStream("/player/player_down_1.png");
	        playerBattleImage = ImageIO.read(is3);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    /*
	    try {
	        InputStream is4 = getClass().getResourceAsStream("/backgrounds/castleBackground.png");
	        playerBattleImage = ImageIO.read(is4);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    */
	    
	    try {
	        titleBackground = ImageIO.read(getClass().getResourceAsStream("/backgrounds/castleBackground.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    
	}


	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	
	
	public void draw(Graphics2D g2, KeyHandler keyH) {
		
		this.g2 = g2;	
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
			
		}
		// PLAY STATE
		if (gp.gameState == gp.playState) {
			//add music
			//gp.playMusic(0);
		}
		// PAUSE STATE
		if(gp.gameState == gp.menuState) {
			drawMenuScreen();
		}
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		if(gp.gameState == gp.dialogueStateObject) {
			drawObjectScreen();
		}
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		// HELP STATE
		if(gp.gameState == gp.helpState) {
			drawHelpScreen();
		}
		
		
		
		// BASIC ENEMY BATTLES ------------------------------------------------
		if(gp.gameState == gp.battleStateZombie) {
			BufferedImage someEnemyImage = gp.zombie.getZombieImage();
			gp.ui.setEnemyBattleImage(someEnemyImage);
			drawBattleScreen();
			
		}
		if(gp.gameState == gp.battleStateGhost) {
			BufferedImage someEnemyImage = gp.ghost.getGhostImage();
			gp.ui.setEnemyBattleImage(someEnemyImage);
			drawBattleScreen();
			
		}
		if(gp.gameState == gp.battleStateSlime) {
			BufferedImage someEnemyImage = gp.slime.getSlimeImage();
			gp.ui.setEnemyBattleImage(someEnemyImage);
			drawBattleScreen();
			
		}
		if(gp.gameState == gp.battleStateSkeleton) {
			BufferedImage someEnemyImage = gp.skeleton.getSkeletonImage();
			gp.ui.setEnemyBattleImage(someEnemyImage);
			drawBattleScreen();
			
		}
		// BOSS BATTLES -------------------------------------------------------
		if(gp.gameState == gp.battleStateSkeletonBoss) {
			BufferedImage someEnemyImage = gp.skeletonBoss.getSkeletonBossImage();
			gp.ui.setEnemyBattleImage(someEnemyImage);
			drawBattleScreen();
			
		}
		if(gp.gameState == gp.battleStateGhostBoss) {
			BufferedImage someEnemyImage = gp.ghostBoss.getGhostBossImage();
			gp.ui.setEnemyBattleImage(someEnemyImage);
			drawBattleScreen();
			
		}
		
		
		
		g2.setFont(FF6Font);
		g2.setColor(Color.white);
		g2.drawString("Key = " + gp.player.hasKey, 25, 50);
		//g2.drawString("Enter to Open Menu", 25, 100);
		
		// TIME
		playTime +=(double)1/60;
		g2.drawString("Time: " + dFormat.format(playTime), 25, 100);
		
		// MESSAGE
		if(gp.gameState == gp.dialogueStateObject) {
			
			g2.setFont(g2.getFont().deriveFont(50F));
			g2.drawString(message, 50, 500);
			
			// Closes the message when...
			if(gp.gameState == gp.playState) {
				messageOn = false;
			}
			
			
		}
		
		
		if(messageActive && !battleMessage.equals("")) {
		    g2.setFont(g2.getFont().deriveFont(20F));
		    g2.setColor(Color.white);
		    g2.drawString(battleMessage, 50, gp.tileSize * 10); // Change position if needed

		    messageCounter++;
		    if(messageCounter > messageDuration) {
		        messageActive = false;
		        battleMessage = "";
		        messageCounter = 0;
		    }
		}


	}
	
	
	
	
	// GAME OVER SCREEN----------------------------------
	public void drawGameOverScreen(){
		
		//Background
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//Title Name
		g2.setFont(FF6Font.deriveFont(Font.BOLD, 80F));

		String text = "Game Over";
		int x = 85;
		int y = 125;
		
		// shadow
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
				
		// text
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.CENTER_BASELINE,50F));
		g2.setColor(Color.white);
			
				
		text = "Return to Title Screen";
		x = (768/2) - (48*2)-48;
		y = 350;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, x - gp.tileSize, y - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
				
		text = "Quit Game";
		x = (768/2) - (48*2)-48;
		y = 400;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, x - gp.tileSize, y - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
				
		
	}
	
	
	
	// HELP SCREEN ----------------------------------------------------
	public void drawHelpScreen() {
		//Background
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
				
				
		// main box
		Color c = new Color(10, 29, 97);
		g2.setColor(c);
		
		g2.fillRoundRect(75, 50, 600, gp.screenHeight-100, 35, 35);
		// main box border
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(75+5, 50+5, 600-10, (gp.screenHeight-100)-10, 25, 25);
		
		
		g2.setFont(FF6Font.deriveFont(Font.BOLD, 60F));
		   
		g2.setColor(Color.black);
		g2.drawString("How to Play:", 95+5, 105+5);
		g2.setColor(Color.white);
		g2.drawString("How to Play:", 95, 105);
		
		g2.setFont(FF6Font.deriveFont(Font.BOLD, 40F));
		
		g2.setColor(Color.black);
		g2.drawString("Be careful! There are enemies", 95+5, 155+5);
		g2.setColor(Color.white);
		g2.drawString("Be careful! There are enemies", 95, 155);
		
		g2.setColor(Color.black);
		g2.drawString("around. Try to make your way", 95+5, 185+5);
		g2.setColor(Color.white);
		g2.drawString("around. Try to make your way", 95, 185);
		
		g2.setColor(Color.black);
		g2.drawString("through the maze and find the key", 95+5, 215+5);
		g2.setColor(Color.white);
		g2.drawString("through the maze and find the key", 95, 215);
		
		g2.setColor(Color.black);
		g2.drawString("to get inside the castle. Make sure", 95+5, 245+5);
		g2.setColor(Color.white);
		g2.drawString("to get inside the castle. Make sure", 95, 245);
		
		g2.setColor(Color.black);
		g2.drawString("you fight plenty of enemies to", 95+5, 275+5);
		g2.setColor(Color.white);
		g2.drawString("you fight plenty of enemies to", 95, 275);
		
		
		g2.setColor(Color.black);
		g2.drawString("level up. There is a powerful boss", 95+5, 305+5);
		g2.setColor(Color.white);
		g2.drawString("level up. There is a powerful boss", 95, 305);
		
		g2.setColor(Color.black);
		g2.drawString("at the end!", 95+5, 335+5);
		g2.setColor(Color.white);
		g2.drawString("at the end!", 95, 335);
	}
	
	
	
	// TITLE SCREEN ---------------------------------------------------
	public void drawTitleScreen() {
		
		//Background
		if (titleBackground != null) {
		    g2.drawImage(titleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
		}
		
		
		
		// Title Name
		g2.setFont(new Font("Serif", Font.BOLD, 100));

		String text = "Kingdom Quest";
		int x = 50;
		int y = 200;
		
		
		// shadow
		g2.setColor(Color.BLACK);
		g2.drawString(text, x+2, y+2);
		
		// text
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		
		
		
		//MENU
		g2.setFont(FF6Font.deriveFont(Font.BOLD, 50F));
		
		//g2.setFont(g2.getFont().deriveFont(Font.CENTER_BASELINE,50F));
		g2.setColor(Color.white);
		
		
		text = "New Game";
		x = (768/2) - (48*2)-48;
		y = 350;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, x - gp.tileSize, y - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
		
		text = "Load Game";
		x = (768/2) - (48*2)-48;
		y = 400;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, x - gp.tileSize, y - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
		
		text = "Quit Game";
		x = (768/2) - (48*2)-48;
		y = 450;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, x - gp.tileSize, y - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
		
		
	}
	
	
	// MENU SCREEN -----------------------------------------------
	public void drawMenuScreen() {
		
		//Background
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		
		// main box
		Color c = new Color(10, 29, 97);
		g2.setColor(c);
		g2.fillRoundRect(0, 50, 600, gp.screenHeight-100, 35, 35);
		// main box border
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(0+5, 50+5, 600-10, (gp.screenHeight-100)-10, 25, 25);
		
		
		// selection box 1
		g2.setColor(c);
		g2.fillRoundRect(500, 25, 270, 400, 35, 35);
		// selection box border
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(500+5, 25+5, 270-10, (400)-10, 25, 25);
		
		// location box
		g2.setColor(c);
		g2.fillRoundRect(375, 575, 395, 75, 35, 35);
		// location box border
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(375+5, 575+5, 395-10, (75)-10, 25, 25);
		
		
		// time/money box
		g2.setColor(c);
		g2.fillRoundRect(545, 457, 225, 120, 35, 35);
		// time/money box border
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(545+5, 457+5, 225-10, (120)-10, 25, 25);
		
		
		
		//Title Name
		g2.setFont(FF6Font.deriveFont(Font.BOLD, 50F));

		
		String text2 = gp.locationText;
		
		g2.setColor(Color.black);
		g2.drawString(text2, 400+5, 625+5);
		
		g2.setColor(Color.white);
		g2.drawString(text2, 400, 625);
		
		
		String text4 = "Item";   
		g2.setColor(Color.black);
		g2.drawString(text4, 525+5, 75+5);
		
		g2.setColor(Color.white);
		g2.drawString(text4, 525, 75);
		
		if(commandNum == 0) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 525 - gp.tileSize, 75 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
		
		String text5 = "Help";   
		g2.setColor(Color.black);
		g2.drawString(text5, 525+5, 125+5);
		
		g2.setColor(Color.white);
		g2.drawString(text5, 525, 125);
		
		if(commandNum == 1) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 525 - gp.tileSize, 125 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
		
		String text6 = "Stats";   
		g2.setColor(Color.black);
		g2.drawString(text6, 525+5, 175+5);
		
		g2.setColor(Color.white);
		g2.drawString(text6, 525, 175);
		
		if(commandNum == 2) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 525 - gp.tileSize, 175 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
		
		String text7 = "Save";   
		g2.setColor(Color.black);
		g2.drawString(text7, 525+5, 225+5);
		
		g2.setColor(Color.white);
		g2.drawString(text7, 525, 225);
		
		if(commandNum == 3) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 525 - gp.tileSize, 225 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
		
		String text3 = "Quit";   
		g2.setColor(Color.black);
		g2.drawString(text3, 525+5, 400+5);
		
		g2.setColor(Color.white);
		g2.drawString(text3, 525, 400);
		
		if(commandNum == 4) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 525 - gp.tileSize, 400 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
		
		
		
		g2.setColor(Color.black);
		g2.drawString("Time:", 560+5, 500+5);

		g2.setColor(Color.white);
		g2.drawString("Time:", 560, 500);

		long totalSeconds = gp.timePlayed / 1000;
		long hours = totalSeconds / 3600;
		long minutes = (totalSeconds % 3600) / 60;

		String timeStr = String.format("%02d:%02d", hours, minutes);

		g2.setColor(Color.black);
		g2.drawString(timeStr, 560+5, 550+5);
		
		g2.setColor(Color.white);
		g2.drawString(timeStr, 560, 550);
		
		
		
		//PLAYER WITH WHITE PORTRAIT
		
		// sub box 1
		g2.setColor(Color.white);
		g2.fillRoundRect(50, 100, 128, 128, 35, 35);
		
		// Draw player image
	    if (playerBattleImage != null) {
	        int playerX = 50;
	        int playerY = 100;
	        int playerWidth = 128; 
	        int playerHeight = 128;
	        g2.drawImage(playerBattleImage, playerX, playerY, playerWidth, playerHeight, null);
	    }
	    
	    
	    
	    String text103 = String.valueOf(gp.player.getName());
	    g2.setColor(Color.black);
	    g2.drawString(text103, 200+5, 125+5);
	    g2.setColor(Color.white);
	    g2.drawString(text103, 200, 125);
	    
	    String text102 = String.valueOf(gp.player.getLvl());
	    String newText102 = "Level: " + text102;
	    g2.setColor(Color.black);
	    g2.drawString(newText102, 200+5, 170+5);
	    g2.setColor(Color.white);
	    g2.drawString(newText102, 200, 170);
	    
	    String text99 = String.valueOf(gp.player.getHp());
	    String newText99 = "HP: " + text99;
	    g2.setColor(Color.black);
	    g2.drawString(newText99, 200+5, 215+5);
	    g2.setColor(Color.white);
	    g2.drawString(newText99, 200, 215);
	    
	    String text101 = String.valueOf(gp.player.getExp());
	    String newText101 = "EXP: " + text101;
	    g2.setColor(Color.black);
	    g2.drawString(newText101, 200+5, 260+5);
	    g2.setColor(Color.white);
	    g2.drawString(newText101, 200, 260);
	    
	    
	}
	
	
	public void drawBattleScreen() {
		
	    Color c = new Color(10, 29, 97);
	    
	    //sky
	    Color c2 = new Color(18, 164, 222);
	    
	    //grass
	    Color c3 = new Color(4, 117, 13);
	    
	    //wall
	    Color c4 = new Color(128, 130, 121);
	    
	    //floor
	    Color c5 = new Color(110, 83, 39);
	    
	    
	    
	    Color c6 = new Color(0,0,0);
	    
	    Color c7 = new Color(51, 49, 49);
	    
	    // Background
	    if(gp.gameState == gp.battleStateZombie || gp.gameState == gp.battleStateSlime) {
		    g2.setColor(c3);
		    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		    
		    g2.setColor(c2);
		    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight / 2);
	    }
	    
	    if(gp.gameState == gp.battleStateGhost || gp.gameState == gp.battleStateSkeleton) {
		    g2.setColor(c5);
		    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		    
		    g2.setColor(c4);
		    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight / 2);
	    }
	    
	    if(gp.gameState == gp.battleStateGhostBoss || gp.gameState == gp.battleStateSkeletonBoss) {
		    g2.setColor(c7);
		    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		    
		    g2.setColor(c6);
		    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight / 2);
	    }
	    

	    // Draw the player battle sprite
	    if (playerBattleImage != null) {
	    	int playerX = 100; // Adjust position as needed
	        int playerY = 275;
	        int playerWidth = 128;  // Adjust size as needed
	        int playerHeight = 128;
	        g2.drawImage(playerBattleImage, playerX, playerY, playerWidth, playerHeight, null);
	    }

	    // Draw the ghost (enemy) battle sprite
	    if (enemyBattleImage == null && gp.ghost != null) {
	        enemyBattleImage = gp.ghost.getGhostImage();
	    }

	    if (enemyBattleImage != null) {
	    	int enemyX = gp.screenWidth-200;  // Adjust position
	        int enemyY = 275;  // Adjust position
	        int enemyWidth = 128;
	        int enemyHeight = 128;
	        
	        if(gp.gameState == gp.battleStateSkeletonBoss || gp.gameState == gp.battleStateGhostBoss) {
	        	enemyX = enemyX-150;
	        	enemyY = enemyY-100;
	        	enemyWidth = 256;
	        	enemyHeight = 256;
	        }
	        g2.drawImage(enemyBattleImage, enemyX, enemyY, enemyWidth, enemyHeight, null);
	    }
	    

	    // Sub-boxes
	    g2.setColor(c);
	    g2.fillRoundRect(0, 450, 350, 225, 35, 35);
	    g2.setColor(Color.white);
	    g2.setStroke(new BasicStroke(5));
	    g2.drawRoundRect(5, 455, 340, 215, 25, 25);

	    g2.setColor(c);
	    g2.fillRoundRect(350, 450, 420, 225, 35, 35);
	    g2.setColor(Color.white);
	    g2.setStroke(new BasicStroke(5));
	    g2.drawRoundRect(355, 455, 410, 215, 25, 25);
	    
	    
	    g2.setFont(FF6Font.deriveFont(Font.BOLD, 25F));
	    
	    
	    
	    
	    // HEADINGS -----------------------------------------
	    
	    String text = "Name";
	    g2.setColor(Color.black);
	    g2.drawString(text, 50, 475);
	    g2.setColor(Color.white);
	    g2.drawString(text, 50, 475);
	    
	    String text2 = "Commands";
	    g2.setColor(Color.black);
	    g2.drawString(text2, 175, 475);
	    g2.setColor(Color.white);
	    g2.drawString(text2, 175, 475);
	    
	    String text3 = "HP";
	    g2.setColor(Color.black);
	    g2.drawString(text3, 400, 475);
	    g2.setColor(Color.white);
	    g2.drawString(text3, 400, 475);
	    
	    String text4 = "";
	    if(gp.gameState == gp.battleStateZombie) {
	        text4 = "Zombie";
	    }
	    else if(gp.gameState == gp.battleStateSlime) {
	        text4 = "Slime";
	    }
	    else if(gp.gameState == gp.battleStateGhost) {
	        text4 = "Ghost";
	    }
	    else if(gp.gameState == gp.battleStateGhostBoss) {
	        text4 = "Ghost Boss";
	    }
	    else if(gp.gameState == gp.battleStateSkeleton) {
	        text4 = "Skeleton";
	    }
	    else if(gp.gameState == gp.battleStateSkeletonBoss) {
	        text4 = "Skeleton Boss";
	    }
	    g2.setColor(Color.black);
	    g2.drawString(text4, 500, 475);
	    g2.setColor(Color.white);
	    g2.drawString(text4, 500, 475);
	    
	    String text5 = "EXP";
	    g2.setColor(Color.black);
	    g2.drawString(text5, 650, 475);
	    g2.setColor(Color.white);
	    g2.drawString(text5, 650, 475);
	    
	    
	    
	    g2.setFont(FF6Font.deriveFont(Font.CENTER_BASELINE, 35F));
	    gp.player.getHp();
	    
	    
	    String text99 = String.valueOf(gp.player.getHp());
	    g2.setColor(Color.black);
	    g2.drawString(text99, 400, 515);
	    g2.setColor(Color.white);
	    g2.drawString(text99, 400, 515);
	    
	    
	    String text101 = String.valueOf(gp.player.getExp());
	    g2.setColor(Color.black);
	    g2.drawString(text101, 650, 515);
	    g2.setColor(Color.white);
	    g2.drawString(text101, 650, 515);
	    
	    

	    
	    // HEADINGS -----------------------------------------
	    
	    String text6 = "Player";
	    g2.setColor(Color.black);
	    g2.drawString(text6, 50, 515);
	    g2.setColor(Color.white);
	    g2.drawString(text6, 50, 515);
	    
	    
	    String text7 = "Attack";
	    g2.setColor(Color.black);
	    g2.drawString(text7, 175, 515);
	    g2.setColor(Color.white);
	    g2.drawString(text7, 175, 515);
	    
	    if(commandNum == 0) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 175 - gp.tileSize, 515 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
	    
	    String text8 = "Sp. Attack";
	    g2.setColor(Color.black);
	    g2.drawString(text8, 175, 545);
	    g2.setColor(Color.white);
	    g2.drawString(text8, 175, 545);
	    
	    if(commandNum == 1) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 175 - gp.tileSize, 545 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
	    
	    String text9 = "Potion";
	    g2.setColor(Color.black);
	    g2.drawString(text9, 175, 575);
	    g2.setColor(Color.white);
	    g2.drawString(text9, 175, 575);
	    
	    if(commandNum == 2) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 175 - gp.tileSize, 575 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
	    
	    String text10 = "Run";
	    g2.setColor(Color.black);
	    g2.drawString(text10, 175, 605);
	    g2.setColor(Color.white);
	    g2.drawString(text10, 175, 605);
	    
	    if(commandNum == 3) {
			if (cursorImage != null) {
			    g2.drawImage(cursorImage, 175 - gp.tileSize, 605 - gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			}

		}
	    
	    
	    
	    
	    if(!battleMessage.equals("")) {
	        
	    	//SCREEN OVERLAY
	    	g2.setColor(c);
		    g2.fillRoundRect(0, 450, 350, 225, 35, 35);
		    g2.setColor(Color.white);
		    g2.setStroke(new BasicStroke(5));
		    g2.drawRoundRect(5, 455, 340, 215, 25, 25);
		    
		    //---------------------------------------------------------------------
		    //New Message Screen
	    	g2.setColor(c);
		    g2.fillRoundRect(0, 405, 768, 45, 35, 35);
		    g2.setColor(Color.white);
		    g2.setStroke(new BasicStroke(5));
		    g2.drawRoundRect(0, 405, 768, 45, 25, 25);
		    
		    
		    g2.setFont(FF6Font.deriveFont(Font.BOLD, 25F));
	    	String text16 = "Name";
		    g2.setColor(Color.black);
		    g2.drawString(text16, 50, 475);
		    g2.setColor(Color.white);
		    g2.drawString(text16, 50, 475);
		    String text17 = "Commands";
		    g2.setColor(Color.black);
		    g2.drawString(text17, 175, 475);
		    g2.setColor(Color.white);
		    g2.drawString(text17, 175, 475);
		    g2.setFont(FF6Font.deriveFont(Font.CENTER_BASELINE, 35F));
	    	String text11 = "Player";
		    g2.setColor(Color.black);
		    g2.drawString(text11, 50, 515);
		    g2.setColor(Color.white);
		    g2.drawString(text11, 50, 515);
		    String text12 = "Attack";
		    g2.setColor(Color.black);
		    g2.drawString(text12, 175, 515);
		    g2.setColor(Color.white);
		    g2.drawString(text12, 175, 515);
		    String text13 = "Sp. Attack";
		    g2.setColor(Color.black);
		    g2.drawString(text13, 175, 545);
		    g2.setColor(Color.white);
		    g2.drawString(text13, 175, 545);
		    String text14 = "Potion";
		    g2.setColor(Color.black);
		    g2.drawString(text14, 175, 575);
		    g2.setColor(Color.white);
		    g2.drawString(text14, 175, 575); 
		    String text15 = "Run";
		    g2.setColor(Color.black);
		    g2.drawString(text15, 175, 605);
		    g2.setColor(Color.white);
		    g2.drawString(text15, 175, 605);
		    
	    	
	        // Battle Text
		    g2.setFont(g2.getFont().deriveFont(30F));
	        g2.setColor(Color.white);
	        g2.drawString(battleMessage, 10, 436);
	        
	    }
	    
	}

	
	public void drawDialogueScreen() {
		// WINDOW
		int x = 25;
		int y = 25;
		int width = gp.screenWidth - 50;
		int height = 200;
		drawTextBox(x, y, width, height);
		
		x+= gp.tileSize;
		y += gp.tileSize;
		g2.drawString(currentText, x, y);
		
	}
	
	public void drawObjectScreen() {
		// WINDOW
		int x = 25;
		int y = gp.screenHeight-225;
		int width = gp.screenWidth - 50;
		int height = 200;
		drawTextBox(x, y, width, height);
		
	}
	
	public void drawTextBox(int x, int y, int width, int height) {
		// box
		Color c = new Color(10, 29, 97);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		// border
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	
	
	
}
