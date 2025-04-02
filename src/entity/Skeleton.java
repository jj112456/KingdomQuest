package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Skeleton extends Entity {
	
	public Skeleton(GamePanel gp) {
		super();
		type = 1;
		name = "Skeleton";
		hp = 30;
		getImage();
		
	}
	
public void getImage() {
		
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/enemy/skeleton.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
