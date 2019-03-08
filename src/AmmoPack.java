import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityC;

public class AmmoPack extends GameObject implements EntityC{
	
	Animation anim;

	public AmmoPack(double x, double y, MainBody main, Textures tex){
		super(x, y, "AmmoPack", main, tex);
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
		anim = new Animation(5, tex.AmmoPack[0], tex.AmmoPack[1]);
	}
	public String getID() {
		return ID;
	}

}
