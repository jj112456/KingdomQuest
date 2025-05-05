package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class WeaponChestObject extends SuperObject {
	
public WeaponChestObject() {
		
		name = "Weapon Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// adds collision to chest
		collision = true;
		
		
		
		
		
	}
	
}
