package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Gold_Chest extends SuperObject {
	
public OBJ_Gold_Chest() {
		
		name = "Gold Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// adds collision to chest
		collision = true;
		
		
		
		
		
	}
	
}
