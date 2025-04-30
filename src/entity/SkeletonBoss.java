package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class SkeletonBoss extends Entity {
    
    public SkeletonBoss(GamePanel gp) {
        super();
        type = 1;
        name = "Skeleton Boss";
        hp = 100;
        maxHp = 100;
        
        attack = 15;
        expDrop = 125;
    }

    public BufferedImage getSkeletonBossImage() {
        BufferedImage skeletonBossImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/skeletonBoss.png")) {
        	skeletonBossImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skeletonBossImage;
    }
}
