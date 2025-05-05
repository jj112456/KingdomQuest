package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Skeleton extends Entity {
	
	public Skeleton(GamePanel gp) {
		super();
        type = 1;
        name = "Skeleton";
        hp = 50;
        maxHp = 50;
        
        attack = 15;
        expDrop = 25;
		
	}
	
	public BufferedImage getSkeletonImage() {
        BufferedImage skeletonImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/skeleton.png")) {
            skeletonImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skeletonImage;
    }
	
	
}
