package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PotionChestObject extends SuperObject {
	
public PotionChestObject() {
		
		name = "Potion Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// adds collision to chest
		collision = true;
		
		
		
		
		
	}
	
}
