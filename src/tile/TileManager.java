package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		
		// path to map text file in use
		loadMap("/maps/map01.txt"/*, 0*/);
		//loadMap("/maps/map02.txt", 1);
	}
	
	
	public void getTileImage() {
		// Add new tile images here:
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/grass.png"));
			tile[0].hasEncountersGrass = true;
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResource("/tiles/wood.png"));
			tile[3].hasEncountersWood = true;
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResource("/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResource("/tiles/path.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResource("/tiles/torch.png"));
			tile[6].collision = true;
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResource("/tiles/carpet.png"));
			
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		for (int col = 0; col < gp.maxWorldCol; col++) {
		    for (int row = 0; row < gp.maxWorldRow; row++) {
		        mapTileNum[col][row] = 0; // or some default tile number
		    }
		}
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new  BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row ++;
				}
			}
			br.close();
			
		}catch(Exception e){
			
		}
		
	}
	
	
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//only loads tiles in view of camera
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
		
	}
	
}
