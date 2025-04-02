package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Ghost extends Entity {
	
	public Ghost(GamePanel gp) {
		super();
		type = 1;
		name = "Ghost";
		hp = 50;
		getImage();
			
	}
		
public void getImage() {
			
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/enemy/Ghost.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
		
		
}
