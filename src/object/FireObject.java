package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class FireObject extends  SuperObject{
	
	public FireObject() {
		
		name = "Fire";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/fire.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	
		
	
	}
	
	
	
	
	
	
	
}
