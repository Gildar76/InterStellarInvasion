package net.gildargaming.graphics;

public class Sprite {
	
	private final int SIZE;
	private int x,y;
	public int[] pixels;
	private Spritesheet sheet;
	public static Sprite currentsprite = new Sprite(0,0,16,Spritesheet.stsheet);
	public int test;
	
	Sprite(int x, int y, int size, Spritesheet sheet) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		loadSprite();
	}
	
	private void loadSprite() {
		for (y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(this.x + x) + (y + this.y) * sheet.getSize() ];
				
				
			}
		}
		this.test = SIZE;
	}
	
	public int getSize() {
		return SIZE;
		
	}
}
