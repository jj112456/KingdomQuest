package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Zombie extends Entity {
    
    public Zombie(GamePanel gp) {
        super();
        type = 1;
        name = "Zombie";
        hp = 30;
        maxHp = 30;
        
        attack = 10;
        expDrop = 25;
    }

    public BufferedImage getZombieImage() {
        BufferedImage zombieImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/zombie.png")) {
        	zombieImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zombieImage;
    }
}
