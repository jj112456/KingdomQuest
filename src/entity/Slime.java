package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Slime extends Entity {
    
    public Slime(GamePanel gp) {
        super();
        type = 1;
        name = "Slime";
        hp = 20;
        maxHp = 20;
        
        attack = 5;
        expDrop = 10;
    }

    public BufferedImage getSlimeImage() {
        BufferedImage slimeImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/slime.png")) {
        	slimeImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return slimeImage;
    }
}
