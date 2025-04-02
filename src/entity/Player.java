package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

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
		worldX = 750;
		worldY = 750;
		speed = 4;
		direction = "down";
		
		
		type = 0;
		name = "UserName";
		
		lvl = 5;
		hp = 100;
		attack = 10;
		specialAttack = 10;
		defense = 10;
		specialDefense = 10;
		attackSpeed = 10;
		luck = 10;
		
		exp = 0;
		nextLevelExp = 1000;
		currency = 0;
		
		//currentWeapon;
		//currentArmor;
		
		
		
		
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
	
	// effects of picking up the object in the over world
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
			case "Door":
				gp.playSE(1);
				if(hasKey > 0) {
					gp.obj[i] = null;
					hasKey--;
					gp.gameState = gp.dialogueStateObject;
					gp.ui.showMessage("Opened door with Key");
				}
				else {
					gp.gameState = gp.dialogueStateObject;
					gp.ui.showMessage("A key is needed to open the door");
				}
				break;
			// add chest case HERE
			
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
