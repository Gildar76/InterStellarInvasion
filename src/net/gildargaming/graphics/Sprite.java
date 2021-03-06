package net.gildargaming.graphics;

public class Sprite {
	
	private final int SIZE;
	private int x,y, width, height;
	public int[] pixels;
	private Spritesheet sheet;

	public int test;
	
	public Sprite(int x, int y, int size, Spritesheet sheet) {
		SIZE = size;
		width = size;
		height = size;
		pixels = new int[SIZE*SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;

		this.sheet = sheet;
		loadSprite();
	}

	public Sprite(int width, int height, int[] pixels) {
		SIZE = width;
		this.width = width;
		this.height = height;

		x = 0;
		y = 0;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
			
		}

		
	}
	
	private void loadSprite() {
		//Loads the sprite from the spritesheet into the pixel array.
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(this.x + x) + (y + this.y) * sheet.getWidth() ];
			}
		}

	}
	
	public int getSize() {
		return SIZE;
		
	}
	
	public void replaceColor(int from, int to) {


		for (int i = 0; i < this.pixels.length; i++) {
			if (pixels[i] == from) pixels[i] = to;
		}
		

		
	}
}
