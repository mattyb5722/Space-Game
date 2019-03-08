import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

public class MainBody extends Canvas {

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 1229;
	public static int HEIGHT = 691;
	private final String title = "Space Game";

	private String frameRate = "60 Ticks, FPS 60";
	private int screenSize = 2;
	
	private int level = 1;
	private int difficulty = 1;
	private int highscore = 0;
	private int enemyKilled = 0;			// Enemies killed this level
	
	private final int healthCounterMax = 5;	// Max time to health pack drop
	private int ammoCounter = 0;			// Current time to ammo pack drop
	private final int ammoCounterMax = 1000;// Max time to ammo pack drop
	
	private PHASE phase = PHASE.MENU;		// Current phase
	private PHASE prev = PHASE.MENU;		// Previous phase

	private static MainBody main = new MainBody();
	private static JFrame frame;
		
	private BufferedImageLoader loader = new BufferedImageLoader();
	private SoundManager sound = new SoundManager();
	private Textures tex = new Textures(this, loader);
	private Controller c = new Controller(this, sound, tex);
	private Player p = new Player(this, tex, c);
	private Menu m = new Menu(this, loader, sound, p, tex);

	private BufferedImage image = new BufferedImage(
		WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
	private BufferedImage backGround;

	public enum PHASE{
		MENU,
		GAME,
		GAME_OVER,
		SETTING
	};
	
	public void changeScreenSize(int size) {
		// Small: 956 x 537			70% of 1366 x 768 
		// Standard: 1229 x 691		90% of 1366 x 768 
		// Large: 1503 x 845		110% of 1366 x 768
		if (size != screenSize) {
			if (size == 1) {				// Small Screen
				WIDTH = 956;
				HEIGHT = 537;
			}else if (size == 2) {			// Standard Screen
				WIDTH = 1229;
				HEIGHT = 691;
			}else if (size == 3) {			// Large Screen
				WIDTH = 1503;
				HEIGHT = 845;
			}
			// Change Window Size
			main.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			main.setMaximumSize(new Dimension(WIDTH, HEIGHT));
			main.setMinimumSize(new Dimension(WIDTH, HEIGHT));
			frame.pack();
			
			// Change Images and Menu sizes and location
			tex.setTextures(size);
			c.setSize();
			p.setSize();
			m.setSize();
			screenSize = size;
		}
	}
		
	private void init(){				// Initialize values
		requestFocus();
		try {
			backGround = loader.loadImage("/BackGround.png"); // Background image
		} catch (IOException e) {e.printStackTrace();}

		sound.playContinuously("/Background.wav");	// Play music
		
		this.addMouseListener(new MouseInput(this, sound, m));
		this.addKeyListener(new KeyInput(p));
	}
	
	public void startRound() {
		p.startRound(difficulty);		// Set Player values and location
		c.createEnemy(1);
		prev = PHASE.MENU;
		phase = PHASE.GAME;
	}
	
	private void tick() {				// Update Function
		if(phase == PHASE.GAME){
			p.tick();					// Update Player
			c.tick();					// Update other objects
			ammoCounter += 1;
			if (ammoCounter >= ammoCounterMax) { // Spawn Ammo Pack
				ammoCounter -= ammoCounterMax;
				c.createAmmoPack();
			}
			if (enemyKilled == (2*level)-1){ // End of Round
				level += 1;
				enemyKilled = 0;
				c.createEnemy((2*level)-1);	
				if (level % healthCounterMax == 0) { // Spawn Health Pack
					c.createHealthPack();
				}
			}
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3); 	// Triple buffering
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(backGround, 0, 0, null);
		//////////////////////////////////
		if(phase == PHASE.GAME){			
			p.render(g);				// Render player
			c.render(g);				// Render other objects
		}
		m.render(g);					// Render interface and menu
		//////////////////////////////////
		g.dispose();
		bs.show();
	}

	public void reset(){				// Reset Game
		prev = PHASE.MENU;
		phase = PHASE.MENU;
		if (level > highscore) {
			highscore = level;
		}	
		c.reset();						// Delete Objects
		level = 1;
		enemyKilled = 0;
		ammoCounter = 0;
	}
	
	private void run() {
		init();							// Initialize values
		
		// Regulates number of ticks per second and frame rate
		long lastTime = System.nanoTime(); // Time of last check
		double ns = 1000000000 / 60.0;	// Nanosecond
		double delta = 0;				// Time from last tick
		int updates = 0;				// Number of ticks
		int frames = 0;					// Number of renders
		long timer = System.currentTimeMillis(); // Game Timer
		
		while(true){					// Game loop
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; // Update and/or render run time
			lastTime = now;				
			if(delta >= 1){				// Sets cap of ticks per second
				tick();					// Update GameObjects
				updates++;
				delta--;
			}
			render();					// Render GameObjects and Menu
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000){ // Per Second
				timer += 1000;
				frameRate = updates + " Ticks, FPS " + frames;
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public static void main(String arg[]){	
		// Set Dimension of Window
		main.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		main.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		main.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		// Create Window
		frame = new JFrame(main.title);
		frame.add(main);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		main.run();						// Start Game loop
	}
	
	public PHASE getPhase() {
		return phase;
	}
	public String getFrameRate() {
		return frameRate;
	}
	public void setPhase(PHASE phase) {
		this.phase = phase;
	}
	public PHASE getPrev() {
		return prev;
	}
	public void setPrev(PHASE prev) {
		this.prev = prev;
	}
	public int getEnemyKilled() {
		return enemyKilled;
	}
	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}
	public int getAmmoCounter() {
		return ammoCounter;
	}
	public void setAmmoCounter(int ammoCounter) {
		this.ammoCounter = ammoCounter;
	}
	public int getAmmoCounterMax() {
		return ammoCounterMax;
	}
	public int getHealthCounterMax() {
		return healthCounterMax;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getDifficulty() {
		return difficulty;
	}

	public int getScreenSize() {
		return screenSize;
	}
	public int getLevel() {
		return level;
	}
	public int getHighscore() {
		return highscore;
	}

}
