package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Silver_Chest extends SuperObject {
	
public OBJ_Silver_Chest() {
		
		name = "Silver Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// adds collision to chest
		collision = true;
		
		
		
		
		
	}
	
}
