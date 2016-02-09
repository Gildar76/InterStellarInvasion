package net.gildargaming.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	private String path;
	private final int SIZE;
	private  int width, height;
	public int[] pixels;
	

	public Spritesheet(String path, int size) {
		SIZE = size;
		this.path = path;
		width = SIZE;
		height = SIZE;
		load();
	}
	public Spritesheet(String path) {
		SIZE = 0;
		this.path = path;
		load();
	}
	private void load() {
		try {
			//I can't comprehend what the .class does except return a class object (sort of)
			BufferedImage img =	ImageIO.read(Spritesheet.class.getResource(path));
			//We now should have the image loaded. Create a pixel array.
			width = img.getWidth();
			height = img.getHeight();
			System.out.println(this.path);
			System.out.print(width + "," + height);
			pixels = new int[width*height];			
			img.getRGB(0, 0, width, height, pixels, 0, width);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
	}
	
	public Sprite[] split(int width, int height) {
		int numSprites = (this.getWidth() * this.getHeight()) / (width * height);
		int count = 0;
		Sprite[] sprites = new Sprite[numSprites];
		int[] pixels = new int[width*height];
		for (int yp = 0; yp < this.getHeight() / height; yp++) {
			for (int xp = 0; xp < this.getWidth() / width; xp++) {
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						int xo = x + xp * width;
						int yo = y + yp * height;
						
						pixels[x + y * width] = this.pixels[xo + yo * this.getWidth()];
						
					}
				}
				sprites[count] = new Sprite(width, height, pixels);
				count++;
			}
		}
		return sprites;
	}
	public int getSize() {
		return SIZE;
		
	}
	public int getWidth() {
		return width;
		
	}
	
	public int getHeight() {
		return height;
		
	}

	
}
