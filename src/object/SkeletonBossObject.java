package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class SkeletonBossObject extends  SuperObject{
	
	public SkeletonBossObject() {
	    name = "Skeleton Boss";
	    try {
	        image = ImageIO.read(getClass().getResourceAsStream("/enemy/skeletonBoss.png"));
	    } catch(IOException e) {
	        e.printStackTrace();
	    }

	    // Customize hitbox size and position
	    solidArea.x = 0;
	    solidArea.y = 0;
	    solidArea.width = 200;
	    solidArea.height = 200;
	    solidAreaDefaultX = solidArea.x;
	    solidAreaDefaultY = solidArea.y;
	}

	
	public BufferedImage getMiniBossImage() {
        BufferedImage miniBossImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/skeletonBoss.png")) {
        	miniBossImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return miniBossImage;
    }
	
	
	@Override
	public void draw(Graphics2D g2, GamePanel gp) {
	    int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    int screenY = worldY - gp.player.worldY + gp.player.screenY;

	    if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
	        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
	        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

	        int newSize = gp.tileSize * 2;
	        g2.drawImage(image, screenX, screenY, newSize, newSize, null);

	        // draw hitbox
	        //g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	    }
	}


	
	
}
