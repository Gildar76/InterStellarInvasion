package net.gildargaming.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	private String path;
	private final int SIZE;
	
	public int[] pixels;
	
	public static Spritesheet stsheet = new Spritesheet("/sprites/grid_github.png", 256);
	public Spritesheet(String path, int size) {
		SIZE = size;
		this.path = path;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	private void load() {
		try {
			//I can't comprehend what the .class does except return a class object (sort of)
			BufferedImage img =	ImageIO.read(Spritesheet.class.getResource(path));
			//We now should have the image loaded. Create a pixel array.
			int width = img.getWidth();
			int height = img.getHeight();
			img.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	public int getSize() {
		return SIZE;
		
	}
}
