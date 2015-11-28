package net.gildargaming.world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.gildargaming.graphics.Screen;

public class FixedWorld extends World {
	
	public int[] pixels;
	public String path;
	
	
	public FixedWorld(String path) {
		super();
		this.path = path;
		load();
		
	}
	
	public void load() {
		try {
			//I can't comprehend what the .class does except return a class object (sort of)
			BufferedImage img =	ImageIO.read(FixedWorld.class.getResource(path));
			//We now should have the image loaded. Create a pixel array.
			width = img.getWidth();
			height = img.getHeight();
			pixels = new int[width*height];
			img.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public void render(int xOffset, int yOffset, Screen screen) {
		int x0 = xOffset;
		int y0 = yOffset;
		int x1 = x0 + screen.getWidth();
		int y1 = y0 + screen.getHeight();
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				screen.pixels[x + y * screen.getWidth()] = pixels[x + y * width];
				
				
				
			}
		}
	}

}
