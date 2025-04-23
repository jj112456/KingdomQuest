package main;

import object.OBJ_Chest;
import object.OBJ_Silver_Chest;
import object.OBJ_Door;
import object.OBJ_Final_Boss;
import object.OBJ_Gold_Chest;
import object.OBJ_Key;
import object.OBJ_Mini_Boss;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		// create and place object in world
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 11 * gp.tileSize;
		gp.obj[0].worldY = 28 * gp.tileSize;
		
		gp.obj[11] = new OBJ_Key();
		gp.obj[11].worldX = 10 * gp.tileSize;
		gp.obj[11].worldY = 45 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Door();
		gp.obj[1].worldX = 7 * gp.tileSize;
		gp.obj[1].worldY = 5 * gp.tileSize;
		
		gp.obj[10] = new OBJ_Door();
		gp.obj[10].worldX = 37 * gp.tileSize;
		gp.obj[10].worldY = 4 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Chest();
		gp.obj[3].worldX = 5 * gp.tileSize;
		gp.obj[3].worldY = 1 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Mini_Boss();
		gp.obj[2].worldX = 7 * gp.tileSize;
		gp.obj[2].worldY = 12 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Final_Boss();
		gp.obj[4].worldX = 36 * gp.tileSize;
		gp.obj[4].worldY = 3 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Silver_Chest();
		gp.obj[5].worldX = 7 * gp.tileSize;
		gp.obj[5].worldY = 28 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Gold_Chest();
		gp.obj[6].worldX = 45 * gp.tileSize;
		gp.obj[6].worldY = 3 * gp.tileSize;
		
	}
}
