package net.gildargaming.graphics
;

import java.util.Random;

public class Screen {
	private int width;
	private int height;
	public int[] pixels;
	private int[] tiles = new int[64*64];
	private Random rand;
	private int xOffset = 0;
	private int yOffset = 0;
	
	
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
	
	public void renderObject(int xp, int yp, Sprite spr) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < spr.getSize(); y++) {

			int ya = y + yp;
			for ( int x = 0; x < spr.getSize(); x++) {

				
				int xa = x + xp;
				if (xa + ya * width > pixels.length - 1 || xa + ya * width < 0) break;
				if (spr.pixels[x + y * spr.getSize()] != 0)  pixels[xa + ya * width] = spr.pixels[x + y * spr.getSize()];
				
			}
		}
	}
	
	public void renderSquare(int xp, int yp, int size, int color) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < size; y++) {

			int ya = y + yp;
			for ( int x = 0; x < size; x++) {

				
				int xa = x + xp;
				if (xa + ya * width > pixels.length - 1 || xa + ya * width < 0) break;
				pixels[xa + ya * width] = color;;
				
			}
		}
	}
	
	public void renderPlayer() {
		
		
	}
	

	public void setOffset(int xOfset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
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
