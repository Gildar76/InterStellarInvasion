package net.gildargaming.entity;

import net.gildargaming.graphics.Screen;

public class WallPiece extends Entity {
	
	protected int size, color;
	
	public WallPiece(int x, int y, int size, int color) {
		super();
		this.x = x;
		this.y = y;
		
		this.color = color;
		this.size = size;
	}
	
	public void render(Screen screen) {
		screen.renderSquare(x, y, size, color);
		//System.out.println("rendering wall");
	}
	public boolean collisionWith(Mob m, int xOffset, int yOffset) {
		return (x+size < m.x + xOffset || m.right - xOffset < x || y+size < m.y - yOffset || m.bottom - yOffset < y ) ? false : true;

	}
}
