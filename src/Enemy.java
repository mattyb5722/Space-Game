import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB{

	Animation anim;
	Controller c;
	SoundManager sound;

	Random r = new Random();

	private double velY = r.nextInt(5) + 1;

	public Enemy(double x, double y, MainBody main, 
			SoundManager sound, Textures tex, Controller c){
		super(x, y, "Enemy", main, tex);
		this.c = c;
		this.sound = sound;
		setSize();
	}
	
	public void tick(){
		// Move based on screen size
		if (main.getScreenSize() == 1) {
			y += velY-1;
		}else if (main.getScreenSize() == 2) {
			y += velY;
		}else if (main.getScreenSize() == 3) {
			y += velY+1;
		}
		
		// Enemy is off the screen
		if (y >= MainBody.HEIGHT){
			x = r.nextInt(MainBody.WIDTH-100);
			y = -100;
		}

		// Collisions
		LinkedList<EntityA> ea = c.getEntityA();	// Bullets
		for(int i = 0; i < ea.size(); i++ ){
			EntityA tempEnt = ea.get(i);
			if(Physics.collision(tempEnt, this)){	// Hit by bullet
				c.removeEntity(this);
				c.removeEntity(tempEnt);
				main.setEnemyKilled(main.getEnemyKilled()+1);
				main.setAmmoCounter(main.getAmmoCounter()+200);
				sound.playOneShot("/Explosion.wav");
			}
		}
	}

	public void render(Graphics g){
		anim.runAnimation();			// Change Image
		anim.drawAnimation(g, x, y);	// Draw image
	}

	public Rectangle getBounds(){		// Hit box based on screen size
		if (main.getScreenSize() == 1) {
			return new Rectangle((int)x,(int)y,78,78);
		}else if (main.getScreenSize() == 2) {
			return new Rectangle((int)x,(int)y,100,100);
		}else if (main.getScreenSize() == 3) {
			return new Rectangle((int)x,(int)y,122,122);
		}
		return null;
	}
	
	public void setSize() {				// Change image based on screen size
		anim = new Animation(5,tex.Enemy[0],tex.Enemy[1]);
	}
	public String getID() {
		return ID;
	}
}
