import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;

	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabImage(int Width, int Height){
		return image.getSubimage(1, 1, Width-1, Height-1);
	}	
}
