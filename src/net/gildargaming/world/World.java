package net.gildargaming.world;

import net.gildargaming.graphics.Screen;

abstract public class World {
	
	protected int width, height;

	
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;

	}
	
	public World() {
		//Currently not used for anything
		
	}

	
	public void generateWorld() {
		
	}
	
	public void loadWorld() {
		
	}
	
	public void update() {
		
	}
	
	public void render(int xOffset, int yOffset, Screen screen) {
		int x0 = xOffset;
		int y0 = yOffset;
		int x1 = x0 + screen.getWidth();
		int y1 = y0 + screen.getHeight();
	}
	
	public void time() {
		
	}
	
}
