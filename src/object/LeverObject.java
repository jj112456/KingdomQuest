package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class LeverObject extends SuperObject{
	
public LeverObject() {
		
		name = "Lever";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/lever.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		// adds collision to door
		collision = true;
	}
	
}
