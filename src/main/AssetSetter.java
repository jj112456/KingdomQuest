package main;

import object.PotionChestObject;
import object.ArmorChestObject;
import object.CellObject;
import object.DoorObject;
import object.FireObject;
import object.GhostBossObject;
import object.WeaponChestObject;
import object.KeyObject;
import object.LeverObject;
import object.SkeletonBossObject;
import object.StairsObject;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		// create and place object in world
		
		gp.obj[0] = new KeyObject();
		gp.obj[0].worldX = 11 * gp.tileSize;
		gp.obj[0].worldY = 28 * gp.tileSize;
		
		gp.obj[11] = new KeyObject();
		gp.obj[11].worldX = 10 * gp.tileSize;
		gp.obj[11].worldY = 45 * gp.tileSize;
		
		gp.obj[1] = new DoorObject();
		gp.obj[1].worldX = 7 * gp.tileSize;
		gp.obj[1].worldY = 5 * gp.tileSize;
		
		
		gp.obj[3] = new PotionChestObject();
		gp.obj[3].worldX = 5 * gp.tileSize;
		gp.obj[3].worldY = 1 * gp.tileSize;
		
		
		
		
		gp.obj[2] = new SkeletonBossObject();
		gp.obj[2].worldX = (7 * gp.tileSize)-55;
		gp.obj[2].worldY = 8 * gp.tileSize;
		
		
		
		gp.obj[4] = new GhostBossObject();
		gp.obj[4].worldX = 46 * gp.tileSize;
		gp.obj[4].worldY = 0 * gp.tileSize;
		
		gp.obj[5] = new ArmorChestObject();
		gp.obj[5].worldX = 7 * gp.tileSize;
		gp.obj[5].worldY = 28 * gp.tileSize;
		
		
		
		gp.obj[6] = new WeaponChestObject();
		gp.obj[6].worldX = 40 * gp.tileSize;
		gp.obj[6].worldY = 1 * gp.tileSize;
		
		
		
		
		gp.obj[17] = new CellObject();
		gp.obj[17].worldX = 46 * gp.tileSize;
		gp.obj[17].worldY = 3 * gp.tileSize;
		
		gp.obj[18] = new StairsObject();
		gp.obj[18].worldX = 35 * gp.tileSize;
		gp.obj[18].worldY = 6 * gp.tileSize;
		
		gp.obj[19] = new StairsObject();
		gp.obj[19].worldX = 35 * gp.tileSize;
		gp.obj[19].worldY = 11 * gp.tileSize;
		
		gp.obj[20] = new LeverObject();
		gp.obj[20].worldX = 47 * gp.tileSize;
		gp.obj[20].worldY = 13 * gp.tileSize;
		
		
		/*
		gp.obj[12] = new FireObject();
		gp.obj[12].worldX = 45 * gp.tileSize;
		gp.obj[12].worldY = 3 * gp.tileSize;
		
		gp.obj[13] = new FireObject();
		gp.obj[13].worldX = 45 * gp.tileSize;
		gp.obj[13].worldY = 3 * gp.tileSize;
		
		gp.obj[14] = new FireObject();
		gp.obj[14].worldX = 45 * gp.tileSize;
		gp.obj[14].worldY = 3 * gp.tileSize;
		
		gp.obj[15] = new FireObject();
		gp.obj[15].worldX = 45 * gp.tileSize;
		gp.obj[15].worldY = 3 * gp.tileSize;
		
		gp.obj[16] = new FireObject();
		gp.obj[16].worldX = 45 * gp.tileSize;
		gp.obj[16].worldY = 3 * gp.tileSize;
		*/
		
	}
}
