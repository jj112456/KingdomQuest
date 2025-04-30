package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Ghost extends Entity {
    
    public Ghost(GamePanel gp) {
        super();
        type = 1;
        name = "Ghost";
        hp = 35;
        maxHp = 35;
        
        specialAttack = 5;
        expDrop = 25;
    }

    public BufferedImage getGhostImage() {
        BufferedImage ghostImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/ghost.png")) {
            ghostImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ghostImage;
    }
}
