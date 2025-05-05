package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class CellObject extends SuperObject{
	
public CellObject() {
		
		name = "Cell";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/cell.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		// adds collision to door
		collision = true;
	}
	
}
