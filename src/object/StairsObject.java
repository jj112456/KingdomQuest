package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class StairsObject extends SuperObject{
	
public StairsObject() {
		
		name = "Stairs";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/stairs.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		// adds collision to door
		collision = true;
	}
	
}
