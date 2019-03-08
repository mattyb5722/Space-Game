
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private int speed;				// Frames before switch
	private int frame = 0;			// Current frame
	private int i = 0;				// Current image
	
	private BufferedImage[] images;
		
	public Animation(int speed, BufferedImage img1, BufferedImage img2){
		this.speed = speed;
		images = new BufferedImage[2];
		this.images[0] = img1;
		this.images[1] = img2;
	}

	public void runAnimation(){	// Change image after 5 frames
		frame++;
		if(frame > speed){			
			frame = 0;
			nextFrame();
		}	
	}
	
	public void nextFrame(){		// Change Image
		i++;
		if(i >= images.length)
			i = 0;
	}
	
	public void drawAnimation(Graphics g, double x, double y){
		g.drawImage(images[i], (int) x, (int) y, null);
	}	
}