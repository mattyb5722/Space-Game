import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {
	
	// Images
	public BufferedImage[] Player = new BufferedImage[2];
	public BufferedImage[] Bullet = new BufferedImage[2];
	public BufferedImage[] Enemy = new BufferedImage[2];
	public BufferedImage[] AmmoPack = new BufferedImage[2];
	public BufferedImage[] HealthPack = new BufferedImage[2];
	
	BufferedImageLoader loader;
	
	public Textures(MainBody main, BufferedImageLoader loader){
		this.loader = loader;
		setTextures(2);
	}
	
	public void setTextures(int resolution) {	// Set images based on screen size
		if (resolution == 1) {					// Small screen size
			try {
				Player[0] = (new SpriteSheet(
					loader.loadImage("/Small/Player0.png"))).grabImage(78, 78);
				Player[1] = (new SpriteSheet(
					loader.loadImage("/Small/Player1.png"))).grabImage(78, 78);
				Bullet[0] = (new SpriteSheet(
					loader.loadImage("/Small/Bullet0.png"))).grabImage(39, 39);
				Bullet[1] = (new SpriteSheet(
					loader.loadImage("/Small/Bullet1.png"))).grabImage(39, 39);
				Enemy[0] = (new SpriteSheet(
					loader.loadImage("/Small/Enemy0.png"))).grabImage(78, 78);
				Enemy[1] = (new SpriteSheet(
					loader.loadImage("/Small/Enemy1.png"))).grabImage(78, 78);
				AmmoPack[0] = (new SpriteSheet(
					loader.loadImage("/Small/AmmoPack0.png"))).grabImage(39, 39);
				AmmoPack[1] = (new SpriteSheet(
					loader.loadImage("/Small/AmmoPack1.png"))).grabImage(39, 39);
				HealthPack[0] = (new SpriteSheet(
					loader.loadImage("/Small/HealthPack0.png"))).grabImage(39, 39);
				HealthPack[1] = (new SpriteSheet(
					loader.loadImage("/Small/HealthPack1.png"))).grabImage(39, 39);
			} catch (IOException e) {e.printStackTrace();}
		}else if (resolution == 2) {			// Standard screen size
			try {
				Player[0] = (new SpriteSheet(
					loader.loadImage("/Standard/Player0.png"))).grabImage(100, 100);
				Player[1] = (new SpriteSheet(
					loader.loadImage("/Standard/Player1.png"))).grabImage(100, 100);
				Bullet[0] = (new SpriteSheet(
					loader.loadImage("/Standard/Bullet0.png"))).grabImage(50, 50);
				Bullet[1] = (new SpriteSheet(
					loader.loadImage("/Standard/Bullet1.png"))).grabImage(50, 50);
				Enemy[0] = (new SpriteSheet(
					loader.loadImage("/Standard/Enemy0.png"))).grabImage(100, 100);
				Enemy[1] = (new SpriteSheet(
					loader.loadImage("/Standard/Enemy1.png"))).grabImage(100, 100);
				AmmoPack[0] = (new SpriteSheet(
					loader.loadImage("/Standard/AmmoPack0.png"))).grabImage(50, 50);
				AmmoPack[1] = (new SpriteSheet(
					loader.loadImage("/Standard/AmmoPack1.png"))).grabImage(50, 50);
				HealthPack[0] = (new SpriteSheet(
					loader.loadImage("/Standard/HealthPack0.png"))).grabImage(50, 50);
				HealthPack[1] = (new SpriteSheet(
					loader.loadImage("/Standard/HealthPack1.png"))).grabImage(50, 50);
			} catch (IOException e) {e.printStackTrace();}
		}else if (resolution == 3) {			// Large screen size
			try {
				Player[0] = (new SpriteSheet(
					loader.loadImage("/Large/Player0.png"))).grabImage(122, 122);
				Player[1] = (new SpriteSheet(
					loader.loadImage("/Large/Player1.png"))).grabImage(122, 122);
				Bullet[0] = (new SpriteSheet(
					loader.loadImage("/Large/Bullet0.png"))).grabImage(61, 61);
				Bullet[1] = (new SpriteSheet(
					loader.loadImage("/Large/Bullet1.png"))).grabImage(61, 61);
				Enemy[0] = (new SpriteSheet(
					loader.loadImage("/Large/Enemy0.png"))).grabImage(122, 122);
				Enemy[1] = (new SpriteSheet(
					loader.loadImage("/Large/Enemy1.png"))).grabImage(122, 122);
				AmmoPack[0] = (new SpriteSheet(
					loader.loadImage("/Large/AmmoPack0.png"))).grabImage(61, 61);
				AmmoPack[1] = (new SpriteSheet(
					loader.loadImage("/Large/AmmoPack1.png"))).grabImage(61, 61);
				HealthPack[0] = (new SpriteSheet(
					loader.loadImage("/Large/HealthPack0.png"))).grabImage(61, 61);
				HealthPack[1] = (new SpriteSheet(
					loader.loadImage("/Large/HealthPack1.png"))).grabImage(61, 61);
			} catch (IOException e) {e.printStackTrace();}
		}
	 }
}
