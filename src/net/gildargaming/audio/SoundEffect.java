package net.gildargaming.audio;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.*;

public class SoundEffect {

	private Clip clip;
	
	public SoundEffect(String filePath) {

	    File file = new File(filePath);
	    System.out.println(file.getAbsolutePath());
	    System.out.println(file.exists());
	    try {
	        clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
	        clip.open(inputStream);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        System.out.println(e.getStackTrace());
	        System.out.println(e.getCause());
	    }
	}


	public void play(){
	/*    System.out.println("play");
	    if(clip.isActive()){
	        clip.stop();
	    }
	    */
	    clip.setFramePosition(0);
	    clip.start();
	}

	public void stop(){
	    clip.stop();
	}

	public void loop(){
	    if(!clip.isActive()){
	        clip.setFramePosition(0);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	    }else{
	        System.out.println("ALREADY PLAYING");
	    }
	
	}
	
	public boolean getActive(){
		return clip.isActive();
	}
}