package net.gildargaming.gamescreens;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.gildargaming.graphics.Font;
import net.gildargaming.graphics.Screen;
import net.gildargaming.world.FixedWorld;

public class GameScreen {
	public int[] pixels;
	public String path;
	int width, height;
	public Font font;
	
	
	
	public GameScreen(int width, int height, String path, Font font) {
		this.width = width;
		this.height = height;
		this.path = path;
		this.font = font;
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
	
	public void update() {
		
	}

	public void render(Screen screen) {

		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				screen.pixels[x + y * screen.getWidth()] = pixels[x + y * width];
								
			}
		}
		

		
	}
}
