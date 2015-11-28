package net.gildargaming.graphics
;

import java.util.Random;

public class Screen {
	private int width;
	private int height;
	public int[] pixels;
	private int[] tiles = new int[64*64];
	private Random rand;
	public Screen(int w, int h) {
		width = w;
		height = h;
		rand = new Random();
		pixels = new int[width*height];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = rand.nextInt(0xffffff);
			
		}
	}
	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				//pixels[x + y*width] = Sprite.currentsprite.pixels[(x & 15) + (y & 15) * Sprite.currentsprite.getSize()];
				
			}
		}
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
