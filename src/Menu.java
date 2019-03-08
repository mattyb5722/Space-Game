import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu {
	
	// Menu 
	private Rectangle playButton, playAgainButton, prevButton, settingButton;
	private BufferedImage settingLogo, backArrow;

	// Sound 
	private Rectangle effectMinusButton, effectVolumeSquare, effectPlusButton;
	private Rectangle backgroundMinusButton, backgroundVolumeSquare, backgroundPlusButton;
		
	// Difficulty
	private Rectangle easyButton, mediumButton, hardButton;
	
	// Resolution
	private Rectangle smallButton, standardButton, largeButton;
	
	// How to Play
	private Rectangle howToPlaySquare;
		
	MainBody main;
	SoundManager sound;
	Player p;
	Textures tex;
	
	// Animations 
	private Animation playerAnim;
	private Animation enemyAnim;
	private Animation bulletAnim;
	private Animation ammoPackAnim;
	private Animation healthPackAnim;

	public Menu(MainBody main, BufferedImageLoader loader, 
			SoundManager sound, Player p, Textures tex){
		this.main = main;
		this.sound = sound;
		this.p = p;
		this.tex = tex;
		
		// Gets setting and back arrow images
		try {
			settingLogo = loader.loadImage("/Setting.png");
			backArrow = loader.loadImage("/BackArrow.png");
		} catch (IOException e) {e.printStackTrace();}
		setSize();
		
		playerAnim = new Animation(5, tex.Player[0], tex.Player[1]);
		enemyAnim = new Animation(5, tex.Enemy[0], tex.Enemy[1]);
		bulletAnim = new Animation(5, tex.Bullet[0], tex.Bullet[1]);
		ammoPackAnim = new Animation(5, tex.AmmoPack[0], tex.AmmoPack[1]);
		healthPackAnim = new Animation(5, tex.HealthPack[0], tex.HealthPack[1]);
	}
	
	public void setSize() {			// Sets size of buttons based on screen size
		playButton = new Rectangle(MainBody.WIDTH/2-125, 250, 250, 100);
		playAgainButton = new Rectangle(MainBody.WIDTH/2-270, MainBody.HEIGHT/2, 540, 100);
		prevButton = new Rectangle(10, MainBody.HEIGHT-60, 50, 50);
		settingButton = new Rectangle(70, MainBody.HEIGHT-60, 50, 50);
		
		// Sound 
		effectMinusButton = new Rectangle(MainBody.WIDTH/2+70, 35, 30, 30);
		effectVolumeSquare = new Rectangle(MainBody.WIDTH/2+100, 25, 50, 50);
		effectPlusButton = new Rectangle(MainBody.WIDTH/2+150, 35, 30, 30);
		backgroundMinusButton = new Rectangle(MainBody.WIDTH/2+70, 110, 30, 30);
		backgroundVolumeSquare = new Rectangle(MainBody.WIDTH/2+100, 100, 50, 50);
		backgroundPlusButton = new Rectangle(MainBody.WIDTH/2+150, 110, 30, 30);
			
		// Difficulty
		easyButton = new Rectangle(MainBody.WIDTH/2+40, 175, 50, 50);
		mediumButton = new Rectangle(MainBody.WIDTH/2+100, 175, 50, 50);
		hardButton = new Rectangle(MainBody.WIDTH/2+160, 175, 50, 50);
		
		// Resolution
		smallButton = new Rectangle(MainBody.WIDTH/2+40, 250, 50, 50);
		standardButton = new Rectangle(MainBody.WIDTH/2+100, 250, 50, 50);
		largeButton = new Rectangle(MainBody.WIDTH/2+160, 250, 50, 50);
		
		// How to Play
		howToPlaySquare  = new Rectangle(MainBody.WIDTH/2-150, 350, 50, 50);
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;	
				
		// Frame Rate Font
		g.setFont( new Font("arial", Font.BOLD, 14) );
		g.setColor(Color.YELLOW);
		g.drawString(main.getFrameRate(), MainBody.WIDTH-150, MainBody.HEIGHT- 20);
		
		// Setting and Previous Menu Buttons
		g.setColor(Color.WHITE);	
		g2d.draw(settingButton);
		g2d.draw(prevButton);
		g.drawImage(settingLogo, settingButton.x, settingButton.y, null);
		g.drawImage(backArrow, prevButton.x, prevButton.y, null);
		
		if(main.getPhase() == main.getPhase().MENU){		// Menu
			// Title
			g.setFont( new Font("arial", Font.BOLD, 125) );
			g.setColor(Color.WHITE);
			g.drawString("SPACE GAME", MainBody.WIDTH/2-420, 200);
			
			// Play Button
			g.setFont( new Font("arial", Font.BOLD, 100) );	
			g2d.draw(playButton);
			g.drawString("Play", playButton.x+15, playButton.y+80);
		}
		else if(main.getPhase() == main.getPhase().GAME){	// In game
			
			//Health		
			g.setColor(Color.gray);
			g.fillRect(0, 0, MainBody.WIDTH, 75);
			g.setColor(Color.green);
			g.fillRect(0, 0, MainBody.WIDTH/3*p.getHealth(), 75);
			g.setColor(Color.white);
			g.drawRect(0, 0, MainBody.WIDTH, 75);

			//Ammo
			g.setColor(Color.gray);
			g.fillRect(0, 75, MainBody.WIDTH, 25);
			g.setColor(Color.red);
			g.fillRect(0, 75, MainBody.WIDTH/5*p.getAmmo(), 25);
			g.setColor(Color.white);
			g.drawRect(0, 75, MainBody.WIDTH, 25);
			
			// Statics
			g.setFont( new Font("arial", Font.BOLD, 14) );
			g.drawString("Health", 0, 70);
			g.drawString("Ammo", 0, 95);
			g.drawString("Next Ammo Drop: " + (main.getAmmoCounterMax() - 
					main.getAmmoCounter()), 0, 115);
			g.drawString("Next Health Drop: " + (main.getHealthCounterMax() - 
					main.getLevel() % main.getHealthCounterMax()), 0, 135);
			g.drawString("Current Level: " + main.getLevel(), 0, 155);
			g.drawString("High Score: " + main.getHighscore(), 0, 175);
		}
		else if(main.getPhase() == main.getPhase().GAME_OVER){	// Game over screen
			// Game Over Font
			g.setFont( new Font("arial", Font.BOLD, 150) );
			g.setColor(Color.red);
			g.drawString("GAME OVER", MainBody.WIDTH/2-463, MainBody.HEIGHT/2-50);
		
			// Play Again Button
			g.setFont( new Font("arial", Font.BOLD, 100) );
			g.setColor(Color.white);
			g2d.draw(playAgainButton);
			g.drawString("Play Again", playAgainButton.x+15, playAgainButton.y+80);
		}
		else if(main.getPhase() == main.getPhase().SETTING){	// Setting screen
			g.setFont( new Font("arial", Font.BOLD, 50) );
			g.setColor(Color.white);
			
			// Sound Effects
			g.drawString("Effect Volume:", effectMinusButton.x-373-30, 
					effectMinusButton.y+31);
			
			// Decrease Button
			g2d.draw(effectMinusButton);
			g.drawString("-", effectMinusButton.x+6, effectMinusButton.y+28);
			
			// Volume
			if (sound.getVolumeSoundEffect() <= -2) {
				g.setColor(Color.RED);
				g.drawString("X", effectVolumeSquare.x+10, effectVolumeSquare.y+43);
				g.setColor(Color.white);
			}
			else if (sound.getVolumeSoundEffect() >= 0) {
				g.drawString((int)sound.getVolumeSoundEffect()+"", 
						effectVolumeSquare.x+13, effectVolumeSquare.y+43);
			}else {
				g.drawString((int)sound.getVolumeSoundEffect()+"", 
						effectVolumeSquare.x+4, effectVolumeSquare.y+43);
			}
			
			// Increase Button
			g2d.draw(effectPlusButton);
			g.drawString("+", effectPlusButton.x+1, effectPlusButton.y+33);

			// Music
			g.drawString("Music Volume:", backgroundMinusButton.x-373-30, 
					backgroundMinusButton.y+31);
			
			// Decrease Button
			g2d.draw(backgroundMinusButton);
			g.drawString("-", backgroundMinusButton.x+6, backgroundMinusButton.y+28);
			
			// Volume
			if (sound.getVolumeBackground() <= -2) {
				g.setColor(Color.RED);
				g.drawString("X", backgroundVolumeSquare.x+10, 
						backgroundVolumeSquare.y+43);
				g.setColor(Color.white);
			}else if (sound.getVolumeBackground() >= 0) {
				g.drawString((int)sound.getVolumeBackground()+"", 
						backgroundVolumeSquare.x+13, backgroundVolumeSquare.y+43);
			}else {
				g.drawString((int)sound.getVolumeBackground()+"", 
						backgroundVolumeSquare.x+4, backgroundVolumeSquare.y+43);
			}
			
			// Increase Button
			g2d.draw(backgroundPlusButton);
			g.drawString("+", backgroundPlusButton.x+1, backgroundPlusButton.y+33);
			
			// Difficulty
			g.drawString("Difficulty:", easyButton.x-250, easyButton.y+38);	
			g2d.draw(easyButton);
			g.drawString("E", easyButton.x+8, easyButton.y+43);
			g2d.draw(mediumButton);
			g.drawString("M", mediumButton.x+5, mediumButton.y+43);
			g2d.draw(hardButton);
			g.drawString("H", hardButton.x+7, hardButton.y+43);
			
			g.setColor(Color.RED);
			if (main.getDifficulty() == 1) {
				g2d.draw(easyButton);
			}else if (main.getDifficulty() == 2) {
				g2d.draw(mediumButton);
			}else if (main.getDifficulty() == 3) {
				g2d.draw(hardButton);
			}
			g.setColor(Color.white);
			
			// Resolution
			g.drawString("Resolution:", smallButton.x-295, smallButton.y+38);	
			g2d.draw(smallButton);
			g.drawString("S", smallButton.x+8, smallButton.y+43);
			g2d.draw(standardButton);
			g.drawString("M", standardButton.x+5, standardButton.y+43);
			g2d.draw(largeButton);
			g.drawString("L", largeButton.x+8, largeButton.y+43);
			
			g.setColor(Color.RED);
			if (main.getScreenSize() == 1) {
				g2d.draw(smallButton);
			}else if (main.getScreenSize() == 2) {
				g2d.draw(standardButton);
			}else if (main.getScreenSize() == 3) {
				g2d.draw(largeButton);
			}
			g.setColor(Color.white);
			
			// How to play
			g.drawString("How to Play:", howToPlaySquare.x, howToPlaySquare.y);	
			g.setFont( new Font("arial", Font.BOLD, 24) );
			// Movement
			g.drawString("Movement:", MainBody.WIDTH/2-320, howToPlaySquare.y+45);
			g.drawString("W: Up", MainBody.WIDTH/2-300, howToPlaySquare.y+80);	
			g.drawString("S: Down", MainBody.WIDTH/2-300, howToPlaySquare.y+105);	
			g.drawString("A: Left", MainBody.WIDTH/2-300, howToPlaySquare.y+130);	
			g.drawString("D: Right", MainBody.WIDTH/2-300, howToPlaySquare.y+155);
			g.drawString("Space: Shot", MainBody.WIDTH/2-300, howToPlaySquare.y+180);
			
			// Game Objects
			g.drawString("Game Objects:", MainBody.WIDTH/2-50, howToPlaySquare.y+35);
			
			g.drawString("Player:", MainBody.WIDTH/2-150, howToPlaySquare.y+60);	
			playerAnim.runAnimation();			// Change Image
			playerAnim.drawAnimation(g, MainBody.WIDTH/2-165, howToPlaySquare.y+70);

			g.drawString("Enemy:", MainBody.WIDTH/2-25, howToPlaySquare.y+60);	
			enemyAnim.runAnimation();			// Change Image
			enemyAnim.drawAnimation(g, MainBody.WIDTH/2-35, howToPlaySquare.y+70);
			
			g.drawString("Bullet:", MainBody.WIDTH/2+100, howToPlaySquare.y+60);	
			bulletAnim.runAnimation();			// Change Image
			bulletAnim.drawAnimation(g, MainBody.WIDTH/2+175, howToPlaySquare.y+30);
			
			g.drawString("Ammo Pack:", MainBody.WIDTH/2+100, howToPlaySquare.y+112);	
			ammoPackAnim.runAnimation();			// Change Image
			ammoPackAnim.drawAnimation(g, MainBody.WIDTH/2+250, howToPlaySquare.y+80);
			
			g.drawString("Health Pack:", MainBody.WIDTH/2+100, howToPlaySquare.y+165);	
			healthPackAnim.runAnimation();			// Change Image
			healthPackAnim.drawAnimation(g, MainBody.WIDTH/2+250, howToPlaySquare.y+133);
		}
	}

	public Rectangle getPlayButton() {
		return playButton;
	}
	public Rectangle getPlayAgainButton() {
		return playAgainButton;
	}
	public Rectangle getSettingButton() {
		return settingButton;
	}
	public Rectangle getEffectPlusButton() {
		return effectPlusButton;
	}
	public Rectangle getEffectMinusButton() {
		return effectMinusButton;
	}
	public Rectangle getBackgroundPlusButton() {
		return backgroundPlusButton;
	}
	public Rectangle getBackgroundMinusButton() {
		return backgroundMinusButton;
	}
	public Rectangle getPrevButton() {
		return prevButton;
	}
	public Rectangle getEasyButton() {
		return easyButton;
	}
	public Rectangle getMediumButton() {
		return mediumButton;
	}
	public Rectangle getHardButton() {
		return hardButton;
	}
	public Rectangle getSmallButton() {
		return smallButton;
	}
	public Rectangle getStandardButton() {
		return standardButton;
	}
	public Rectangle getLargeButton() {
		return largeButton;
	}	
}
