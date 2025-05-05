package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OpenChestObject extends SuperObject {
	
public OpenChestObject() {
		
		name = "Open Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/openChest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// adds collision to chest
		collision = false;
		
		
		
		
		
	}
	
}
