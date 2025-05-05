package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class GhostBoss extends Entity {
    
    public GhostBoss(GamePanel gp) {
        super();
        type = 1;
        name = "Ghost Boss";
        hp = 150;
        maxHp = 150;
        
        specialAttack = 20;
        expDrop = 125;
    }

    public BufferedImage getGhostBossImage() {
        BufferedImage ghostBossImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/ghostBoss.png")) {
        	ghostBossImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ghostBossImage;
    }
}
