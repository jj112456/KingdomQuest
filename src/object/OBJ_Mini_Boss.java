package object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class OBJ_Mini_Boss extends  SuperObject{
	
	public OBJ_Mini_Boss() {
		
		name = "Mini Boss";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/enemy/skeletonBoss.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public BufferedImage getMiniBossImage() {
        BufferedImage miniBossImage = null;
        try (InputStream is = getClass().getResourceAsStream("/enemy/skeletonBoss.png")) {
        	miniBossImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return miniBossImage;
    }
	
	
	
	
	
}
