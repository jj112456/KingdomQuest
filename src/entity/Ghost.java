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
        hp = 45;
        maxHp = 45;
        
        specialAttack = 25;
        expDrop = 25;
        
        //specialDefense = 30;
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
