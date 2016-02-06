package net.gildargaming.graphics;

public class Sprite {
	
	private final int SIZE;
	private int x,y;
	public int[] pixels;
	private Spritesheet sheet;

	public int test;
	
	public Sprite(int x, int y, int size, Spritesheet sheet) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;

		this.sheet = sheet;
		loadSprite();
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
		//System.out.println(this.pixels);
		//System.out.println(from);

		for (int p : pixels) {
			//if (p == from) p = to;
			//System.out.println(p);
		}
		
	}
}
