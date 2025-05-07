package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.OpenChestObject;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		// adjust hit box
		solidArea = new Rectangle(8, 16, 32, 32);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	

	
	public void setDefaultValues() {
		// player starting position
		worldX = 5 * gp.tileSize;
		worldY = 42 * gp.tileSize;
		speed = 4;
		direction = "down";
		
		
		type = 0;
		name = "Player";
		
		lvl = 1;
		hp = 100;
		maxHp = 100;
		attack = 10;
		specialAttack = 8;
		defense = 10;
		specialDefense = 10;
		attackSpeed = 10;
		luck = 10;
		
		exp = 0;
		nextLevelExp = 100;
		currency = 0;
		
		potion = 0;
		
		
	}
	
	public void getPlayerImage() {
		
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
			down1= ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void levelUpPlayer() {
		gp.player.setLvl((gp.player.getLvl())+1);
		gp.player.setAttack((gp.player.getAttack())+2);
		gp.player.setSpecialAttack((gp.player.getLvl())+2);
		gp.player.setMaxHp((gp.player.getMaxHp())+5);
		gp.player.setHp(gp.player.getMaxHp());
		gp.player.setDefense((gp.player.getDefense())+2);
		gp.player.setSpecialDefense((gp.player.getSpecialDefense())+2);
		
		gp.ui.battleMessage += "Level Up! Player is now level "+ gp.player.getLvl();
        gp.ui.messageActive = true;
	}
	
	public void update() {

		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

		
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			else if(keyH.escapePressed == true) {
				
			}
			
			// checks tile collision
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
			// check object collision
			int objIndex = gp.collisionChecker.checkObject(this, true);
			
			pickUpObject(objIndex);
			
			
			// if collision is false, the player can move
			if (collisionOn == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
		
			}
		
			// animation movement speed
			spriteCounter++;
			if(spriteCounter> 15) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		
		}
		
	}
	
	
	
	// Effects of picking up the object in the over world
	public void pickUpObject(int i) {
		if(i != 999) {
			// deletes object touched
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				gp.playSE(2);
				hasKey++;
				gp.obj[i] = null;
				gp.gameState = gp.dialogueStateObject;
				gp.ui.showMessage("Picked Up: " + "Key");
				break;
			case "Skeleton Boss":
				gp.gameState = gp.battleStateSkeletonBoss;
				gp.obj[i] = null;
				break;
			case "Ghost Boss":
				gp.gameState = gp.battleStateGhostBoss;
				gp.obj[i] = null;
				break;
			case "Potion Chest":
				gp.playSE(1);
				gp.gameState = gp.dialogueStateObject;
				gp.ui.showMessage("Potion was inside");
				gp.player.setPotion((gp.player.getPotion())+1);
				gp.obj[i] = null;
				
				gp.obj[7] = new OpenChestObject();
				gp.obj[7].worldX = 7 * gp.tileSize;
				gp.obj[7].worldY = 28 * gp.tileSize;
				break;
			case "Armor Chest":
				gp.playSE(1);
				gp.gameState = gp.dialogueStateObject;
				gp.ui.showMessage("Armor was inside (Defense +2)");
				gp.player.setAttack(gp.player.getDefense()+2);
				gp.player.setAttack(gp.player.getSpecialDefense()+2);
				gp.obj[i] = null;
				
				gp.obj[8] = new OpenChestObject();
				gp.obj[8].worldX = 3 * gp.tileSize;
				gp.obj[8].worldY = 8 * gp.tileSize;
				
				break;
			case "Weapon Chest":
				gp.playSE(1);
				gp.gameState = gp.dialogueStateObject;
				gp.ui.showMessage("A new Weapon was inside (Attack +2)");
				gp.player.setAttack(gp.player.getAttack()+2);
				gp.obj[i] = null;
				
				gp.obj[9] = new OpenChestObject();
				gp.obj[9].worldX = 40 * gp.tileSize;
				gp.obj[9].worldY = 1 * gp.tileSize;
				break;
			case "Door":
				gp.playSE(1);
				if(hasKey > 0) {
					gp.obj[i] = null;
					hasKey--;
					gp.gameState = gp.dialogueStateObject;
					gp.ui.showMessage("Opened door with Key");
					worldX = gp.tileSize * 41;
					worldY = gp.tileSize * 3;
					gp.locationText = "Castle";
				}
				else {
					gp.gameState = gp.dialogueStateObject;
					gp.ui.showMessage("A key is needed to open the door");
				}
				break;
			case "Fire":
				//gp.playSE(2);
				gp.player.setHp(gp.player.getHp()-5);
				break;
			case "Stairs":
				gp.playSE(1);
				if(gp.stairTracker == true) {
					worldX = gp.tileSize * 36;
					worldY = gp.tileSize * 11;
					gp.stairTracker = false;
				}else {
					worldX = gp.tileSize * 36;
					worldY = gp.tileSize * 6;
					gp.stairTracker = true;
				}
				
				break;
			// test may not work
			case "Lever":
			    gp.playSE(2);
			    gp.gameState = gp.dialogueStateObject;
			    gp.ui.showMessage("A Door Opened Nearby");

			    for (int j = 0; j < gp.obj.length; j++) {
			        if (gp.obj[j] != null && "Cell".equals(gp.obj[j].name)) {
			            gp.obj[j] = null;
			            break; // remove first cell found
			        }
			    }
			    break;
			case "Cell":
				gp.playSE(1);
				gp.gameState = gp.dialogueStateObject;
				gp.ui.showMessage("Locked");
				break;
			}
		}
	}
	
	
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
	
	
	
}
