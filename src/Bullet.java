import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA{

	Animation anim;
	Controller c;

	public Bullet(double x, double y, MainBody main, 
			Textures tex, Controller c){
		super(x, y, "Bullet", main, tex);	
		this.c = c;
		setSize();
	}
	
	public void tick(){					// Move based on screen size
		if (main.getScreenSize() == 1) {
			y -= 5.5;
		}else if (main.getScreenSize() == 2) {
			y -= 7;
		}else if (main.getScreenSize() == 3) {
			y -= 8.5;
		}
		if (y <= 0) {					// Bullet is off the screen
			c.removeEntity(this);
		}
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
	
	public void setSize() {				// Change Image based on screen size
		anim = new Animation(5,tex.Bullet[0],tex.Bullet[1]);
	}
	public String getID() {
		return ID;
	}
}
