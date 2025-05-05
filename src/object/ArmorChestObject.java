package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ArmorChestObject extends SuperObject {
	
public ArmorChestObject() {
		
		name = "Armor Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// adds collision to chest
		collision = true;
		
		
		
		
		
	}
	
}
