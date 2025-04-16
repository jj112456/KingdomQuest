package object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class OBJ_Final_Boss extends  SuperObject{
	
	public OBJ_Final_Boss() {
		
		name = "Final Boss";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/enemy/ghostBoss.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	
	public BufferedImage getFinalBossImage() {
        BufferedImage finalBossImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/ghostBoss.png")) {
        	finalBossImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalBossImage;
    }
	
	
	
	
}
