import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;

public class Controller {
	MainBody main;
	Textures tex;
	SoundManager sound;
	Random r = new Random();

	private LinkedList<EntityA> ea = new LinkedList<EntityA>(); // Bullets
	private LinkedList<EntityB> eb = new LinkedList<EntityB>(); // Enemies
	private LinkedList<EntityC> ec = new LinkedList<EntityC>(); // Aid Packs
	
	public Controller(MainBody main, SoundManager sound, Textures tex){
		this.main = main;
		this.tex = tex;
		this.sound = sound;
	}
	
	public void createEnemy(int enemyCount){ // Create enemy based on screen size
		if (main.getScreenSize() == 1) {
			for(int i = 0; i < enemyCount; i++){
				addEntity(new Enemy(
					r.nextInt(MainBody.WIDTH-78), 0, main, sound, tex, this));
			}
		}else if (main.getScreenSize() == 2) {
			for(int i = 0; i < enemyCount; i++){
				addEntity(new Enemy(
					r.nextInt(MainBody.WIDTH-100), 0, main, sound, tex, this));
			}
		}else if (main.getScreenSize() == 3) {
			for(int i = 0; i < enemyCount; i++){
				addEntity(new Enemy(
					r.nextInt(MainBody.WIDTH-122), 0, main, sound, tex, this));
			}
		}
	}

	public void createAmmoPack() {
		addEntity(new AmmoPack(r.nextInt(MainBody.WIDTH-300)+125,
				r.nextInt(MainBody.HEIGHT-250)+150, main, tex));
	}
	
	public void createHealthPack() {
		addEntity(new HealthPack(r.nextInt(MainBody.WIDTH-300)+125, 
				r.nextInt(MainBody.HEIGHT-250)+150, main, tex));
	}

	public void reset(){
		while (ea.size() != 0) {			// Delete bullets
			EntityA tempEnt = ea.get(0);
			removeEntity(tempEnt);
		}
		while (eb.size() != 0) {			// Delete enemies
			EntityB tempEnt = eb.get(0);
			removeEntity(tempEnt);
		}
		while (ec.size() != 0) {			// Delete aid packs
			EntityC tempEnt = ec.get(0);
			removeEntity(tempEnt);
		}
	}
			
	public void tick(){
		for(int i = 0; i<ea.size();i++){	// Move bullets
			EntityA tempEnt = ea.get(i);
			tempEnt.tick();
		}
		for(int i = 0; i<eb.size();i++){	// Delete enemies
			EntityB tempEnt = eb.get(i);
			tempEnt.tick();
		}	
	}
	
	public void render(Graphics g){
		for(int i = 0; i<ea.size();i++){	// Render bullets
			EntityA tempEnt = ea.get(i);
			tempEnt.render(g);
		}
		for(int i = 0; i<eb.size();i++){	// Render enemies
			EntityB tempEnt = eb.get(i);
			tempEnt.render(g);
		}
		for(int i = 0; i<ec.size();i++){	// Render aid packs
			EntityC tempEnt = ec.get(i);
			tempEnt.render(g);
		}
	}
	
	public void setSize() {			// Set size based on screen size
		for(int i = 0; i<ea.size();i++){	// Set bullets size
			EntityA tempEnt = ea.get(i);
			tempEnt.setSize();
		}
		for(int i = 0; i<eb.size();i++){	// Set enemies size
			EntityB tempEnt = eb.get(i);
			tempEnt.setSize();
		}
		for(int i = 0; i<ec.size();i++){	// Set aid packs size
			EntityC tempEnt = ec.get(i);
			tempEnt.setSize();
		}
		
	}
	
	public void addEntity(EntityA block){
		ea.add(block);
	}
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	public void addEntity(EntityB block){
		eb.add(block);
	}
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	public void addEntity(EntityC block){
		ec.add(block);
	}
	public void removeEntity(EntityC block){
		ec.remove(block);
	}
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}
	public LinkedList<EntityC> getEntityC(){
		return ec;
	}
}
