import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityC;

public class HealthPack extends GameObject implements EntityC{

	Animation anim;

	public HealthPack(double x, double y, MainBody main, Textures tex){
		super(x, y, "HealthPack", main, tex);
		setSize();
	}
	
	public void render(Graphics g){
		anim.runAnimation();			// Change Image
		anim.drawAnimation(g, x, y);	// Draw image
	}
			
	public Rectangle getBounds(){		// Hit box based on screen size
		if (main.getScreenSize() == 1) {
			return new Rectangle((int) x, (int) y, 39, 39);
		}else if (main.getScreenSize() == 2) {
			return new Rectangle((int) x, (int) y, 50, 50);
		}else if (main.getScreenSize() == 3) {
			return new Rectangle((int) x, (int) y, 61, 61);
		}
		return null;
	}

	public void setSize() {				// Change image based on screen size
		anim = new Animation(5, tex.HealthPack[0], tex.HealthPack[1]);
	}
	public String getID() {
		return ID;
	}

}
