import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager {
	Clip backgroundClip;
	// -2 = Mute, -1 = Soft, 0 = Standard, 1 = Loud
	float volumeSoundEffect = 0; 
	float volumeBackground = 0; 
	FloatControl volumeControl;		// Controls music volume
	
	public SoundManager(){
		try {
			backgroundClip = AudioSystem.getClip(); // Free clip
		} catch (LineUnavailableException e) {e.printStackTrace();}
	}
		
	public void playOneShot(String path) {		// Plays sound once
		if (volumeSoundEffect > -2) {			// Not muted
			try {
				Clip clip = AudioSystem.getClip(); // Free clip
				clip.open(AudioSystem.getAudioInputStream(
						getClass().getResource(path))); // Open sound file
				FloatControl gainControl = (FloatControl) 
						clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(volumeSoundEffect*6); // Set volume
				clip.start();					// Play clip
			} 
			catch (UnsupportedAudioFileException e) {e.printStackTrace();} 
			catch (IOException e) {e.printStackTrace();} 
			catch (LineUnavailableException e) {e.printStackTrace();}	
		}
	}
	
	public void playContinuously(String path) { // Plays sound continuously
		try {
			backgroundClip.open(AudioSystem.getAudioInputStream(
					getClass().getResource(path))); // Open sound file
			volumeControl = (FloatControl) 
					backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
			volumeControl.setValue(-10); 		// Set default volume
			backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
		} 
		catch (UnsupportedAudioFileException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (LineUnavailableException e) {e.printStackTrace();}	
	}
	
	public void changeBackgroundVolume(float volume) {
		if (volumeBackground < -1 && volume >= -1) { // Unmute music
			backgroundClip.start();
		}
		if(volume < -1) {						// Mute music
			backgroundClip.stop();
		}else {
			volumeControl.setValue((volume-1)*10); // Change Volume
		}
		this.volumeBackground = volume;
	}

	public float getVolumeSoundEffect() {
		return volumeSoundEffect;
	}
	public void setVolumeSoundEffect(float volumeSoundEffect) {
		this.volumeSoundEffect = volumeSoundEffect;
	}
	public float getVolumeBackground() {
		return volumeBackground;
	}	
}
