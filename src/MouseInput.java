import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	MainBody main;
	Menu m;
	SoundManager sound;
	
	public MouseInput(MainBody main, SoundManager sound, Menu m){
		this.main = main;
		this.m = m;
		this.sound = sound;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (main.getPhase() == main.getPhase().MENU){
			// Start Button
			if (mx < m.getPlayButton().x + m.getPlayButton().width 
					&& mx > m.getPlayButton().x ){
				if (my < m.getPlayButton().y + m.getPlayButton().height 
						&& my > m.getPlayButton().y ){
					sound.playOneShot("/Button.wav");
					main.startRound();
				}
			}
		}
		else if (main.getPhase() == main.getPhase().GAME_OVER){
			// Play Again Button
			if (mx < m.getPlayAgainButton().x + m.getPlayAgainButton().width 
					&& mx > m.getPlayAgainButton().x ){
				if (my < m.getPlayAgainButton().y + m.getPlayAgainButton().height 
						&& my > m.getPlayAgainButton().y ){
					sound.playOneShot("/Button.wav");
					main.reset();
				}
			}
		}
		else if (main.getPhase() == main.getPhase().SETTING){
			// Effect Volume Buttons
			if (my < m.getEffectPlusButton().y + m.getEffectPlusButton().height 
					&& my > m.getEffectPlusButton().y ){
				// Increase
				if (mx < m.getEffectPlusButton().x + m.getEffectPlusButton().width 
						&& mx > m.getEffectPlusButton().x ){
					sound.playOneShot("/Button.wav");
					if (sound.getVolumeSoundEffect() + 1 <= 1) {
						sound.setVolumeSoundEffect(sound.getVolumeSoundEffect()+1);
					}
				}
				// Decrease
				else if (mx < m.getEffectMinusButton().x + m.getEffectMinusButton().width 
						&& mx > m.getEffectMinusButton().x ){
					sound.playOneShot("/Button.wav");
					if (sound.getVolumeSoundEffect() - 1 >= -2) {
						sound.setVolumeSoundEffect(sound.getVolumeSoundEffect()-1);
					}	
				}
			}
			// Music Volume Buttons
			else if (my < m.getBackgroundPlusButton().y + m.getBackgroundPlusButton().height 
					&& my > m.getBackgroundPlusButton().y ){
				// Increase
				if (mx < m.getBackgroundPlusButton().x + m.getBackgroundPlusButton().width 
						&& mx > m.getBackgroundPlusButton().x ){
					sound.playOneShot("/Button.wav");
					if (sound.getVolumeBackground() + 1 <= 1) {
						sound.changeBackgroundVolume(sound.getVolumeBackground()+1);
					}
				}
				// Decrease
				else if (mx < m.getBackgroundMinusButton().x + m.getBackgroundMinusButton().width 
						&& mx > m.getBackgroundMinusButton().x ){
					sound.playOneShot("/Button.wav");
					if (sound.getVolumeBackground() - 1 >= -2) {
						sound.changeBackgroundVolume(sound.getVolumeBackground()-1);
					}	
				}
			}
			// Difficulty Buttons
			else if (my < m.getEasyButton().y + m.getEasyButton().height 
					&& my > m.getEasyButton().y ){
				// Easy
				if (mx < m.getEasyButton().x + m.getEasyButton().width 
						&& mx > m.getEasyButton().x ){
					sound.playOneShot("/Button.wav");
					main.setDifficulty(1);
				}
				// Medium
				else if (mx < m.getMediumButton().x + m.getMediumButton().width 
						&& mx > m.getMediumButton().x ){
					sound.playOneShot("/Button.wav");
					main.setDifficulty(2);
				}
				// Hard
				else if (mx < m.getHardButton().x + m.getHardButton().width 
						&& mx > m.getHardButton().x ){
					sound.playOneShot("/Button.wav");
					main.setDifficulty(3);
				}
			}
			// Screen Size Buttons
			else if (my < m.getSmallButton().y + m.getSmallButton().height 
					&& my > m.getSmallButton().y ){
				// Small
				if (mx < m.getSmallButton().x + m.getSmallButton().width 
						&& mx > m.getSmallButton().x ){
					sound.playOneShot("/Button.wav");
					main.changeScreenSize(1);
				}
				// Standard
				else if (mx < m.getStandardButton().x + m.getStandardButton().width 
						&& mx > m.getStandardButton().x ){
					sound.playOneShot("/Button.wav");
					main.changeScreenSize(2);
				}
				// Large
				else if (mx < m.getLargeButton().x + m.getLargeButton().width 
						&& mx > m.getLargeButton().x ){
					sound.playOneShot("/Button.wav");
					main.changeScreenSize(3);
				}
			}
		}
		// Setting and Previous Menu Buttons
		if (my < m.getSettingButton().y + m.getSettingButton().height 
				&& my > m.getSettingButton().y ){
			// Setting
			if (mx < m.getSettingButton().x + m.getSettingButton().width 
					&& mx > m.getSettingButton().x ){
				sound.playOneShot("/Button.wav");
				if (main.getPhase() != main.getPhase().SETTING) {
					main.setPrev(main.getPhase());
					main.setPhase(main.getPhase().SETTING);
				}
			}
			// Previous
			else if (mx < m.getPrevButton().x + m.getPrevButton().width 
					&& mx > m.getPrevButton().x ){
				sound.playOneShot("/Button.wav");
				if (main.getPhase() == main.getPhase().GAME) {
					main.setPrev(main.getPhase().MENU);
					main.setPhase(main.getPhase().GAME_OVER);
				}else if (main.getPhase() == main.getPhase().MENU) {
					System.exit(1);	
				}else {
					main.setPhase(main.getPrev());
				}
			}
		}	
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

}
