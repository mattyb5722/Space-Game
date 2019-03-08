import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;

public class Player extends GameObject implements EntityA{
	
	Animation anim;
	Controller c;

	private double velX = 0;
	private double velY = 0;
	
	private Boolean firing = false;
	
	private int healthStart = 3;		// Starting Health value
	private int ammoStart = 5;			// Starting Ammo value
	
	private int health;
	private int ammo;
	
	private final int healthRefuel = 1;	// health from health pack
	private final int ammoRefuel = 3;	// ammo from ammo pack
	
	public Player(MainBody main, Textures tex, Controller c){
		super(MainBody.WIDTH/2, MainBody.HEIGHT-120, "Player", main, tex);
		this.c = c;
		setSize();
	}

	public void startRound(int difficulty) { // Set values based on difficulty
		// Health and Ammo
		if (difficulty == 1) {
			healthStart = 3;
			ammoStart = 5;
		}else if (difficulty == 2) {
			healthStart = 2;
			ammoStart = 3;
		}else if (difficulty == 3) {
			healthStart = 1;
			ammoStart = 1;
		}
		health = healthStart;
		ammo = ammoStart;
		
		// Starting position
		x = MainBody.WIDTH/2;
		y = MainBody.HEIGHT-120;
	}
	
	public void tick(){						// Update Function
		// Move Player
		x += velX;
		y += velY;
		
		// Player is off the screen
		if (x <= 0)
			x = 0;
		if (y <= 120)
			y = 120;
		if (main.getScreenSize() == 1) {
			if(x >= MainBody.WIDTH - 78)
				x = MainBody.WIDTH - 78;
			if (y >= MainBody.HEIGHT - 78 - 20)
				y = MainBody.HEIGHT - 78 - 20;
		}else if (main.getScreenSize() == 2) {
			if(x >= MainBody.WIDTH - 100)
				x = MainBody.WIDTH - 100;
			if (y >= MainBody.HEIGHT - 100 - 20)
				y = MainBody.HEIGHT - 100 - 20;
		}else if (main.getScreenSize() == 3) {
			if(x >= MainBody.WIDTH - 122)
				x = MainBody.WIDTH - 122;
			if (y >= MainBody.HEIGHT - 122 - 20)
				y = MainBody.HEIGHT - 122 - 20;	
		}

		// Collisions
		LinkedList<EntityB> eb = c.getEntityB(); // Enemies
		for(int i = 0; i < eb.size(); i++ ){
			EntityB tempEnt = eb.get(i);
			if(Physics.collision(this, tempEnt)){ // Player hit Enemy
				c.removeEntity(tempEnt);
				main.setEnemyKilled(main.getEnemyKilled()+1);
				health -= 1;
				if(health == 0){				// Game Over
					main.setPrev(main.getPhase().MENU);
					main.setPhase(main.getPhase().GAME_OVER);
				}
			}
		}
		LinkedList<EntityC> ec = c.getEntityC(); // Aid Packs
		for(int i = 0; i < ec.size(); i++ ){
			EntityC tempEnt = ec.get(i);
			if(Physics.collision(this, tempEnt)){ // Player hit Aid Pack
				if (tempEnt.getID() == "AmmoPack") {
					c.removeEntity(tempEnt);
					ammo += ammoRefuel;
					if (ammo > ammoStart) {
						ammo = ammoStart;
					}
				}else if (tempEnt.getID() == "HealthPack") {
					c.removeEntity(tempEnt);
					health += healthRefuel;
					if (health > healthStart) {
						health = healthStart;
					}
				}
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
		anim = new Animation(5, tex.Player[0], tex.Player[1]);
	}
	
	public void keyPressed(KeyEvent e) { // Changing player vel based on size of screen
		if (main.getPhase() == main.getPhase().GAME) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_D){	// D Key
				if (main.getScreenSize() == 1) {
					velX = 4;
				}else if (main.getScreenSize() == 2) {
					velX = 5;
				}else if (main.getScreenSize() == 3) {
					velX = 6;
				}
			}if (key == KeyEvent.VK_A){	// A Key
				if (main.getScreenSize() == 1) {
					velX = -4;
				}else if (main.getScreenSize() == 2) {
					velX = -5;
				}else if (main.getScreenSize() == 3) {
					velX = -6;
				}
			}if (key == KeyEvent.VK_W){	// W Key
				if (main.getScreenSize() == 1) {
					velY = -4;
				}else if (main.getScreenSize() == 2) {
					velY = -5;
				}else if (main.getScreenSize() == 3) {
					velY = -6;
				}
			}if (key == KeyEvent.VK_S){	// S Key
				if (main.getScreenSize() == 1) {
					velY = 4;
				}else if (main.getScreenSize() == 2) {
					velY = 5;
				}else if (main.getScreenSize() == 3) {
					velY = 6;
				}
			}if(key == KeyEvent.VK_SPACE && !firing && ammo > 0){ // Space Key
				firing = true;
				// Create bullet based on player position and player size
				if (main.getScreenSize() == 1) {
					c.addEntity(new Bullet(x + 19, y, main, tex, c));
				}else if (main.getScreenSize() == 2) {
					c.addEntity(new Bullet(x + 25, y, main, tex, c));
				}else if (main.getScreenSize() == 3) {
					c.addEntity(new Bullet(x + 30, y, main, tex, c));
				}
				ammo -= 1;
			}
		}
	}

	public void keyReleased(KeyEvent e) { // Set velocity to 0
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D && velX > 0){
			velX = 0;
		}if (key == KeyEvent.VK_A && velX < 0){
			velX = 0;
		}if (key == KeyEvent.VK_W && velY < 0){
			velY = 0;
		}if (key == KeyEvent.VK_S && velY > 0){
			velY = 0;
		}if(key == KeyEvent.VK_SPACE){
			firing = false;
		}
	}

	public String getID() {
		return ID;
	}
	public int getAmmo() {
		return ammo;
	}
	public int getHealth() {
		return health;
	}
}
