package net.gildargaming.entity;

import java.util.ArrayList;

import net.gildargaming.graphics.Screen;

public class Wall extends Entity {

	private ArrayList<WallPiece> parts;
	private int color;
	private int size;
	
	public Wall(int x, int y, int size, int color) {
		super();
		this.x = x;
		this.y = y;
		
		this.color = color;
		this.size = size;
		parts = new ArrayList<WallPiece>();
		buildWall();
	}
	
	private void buildWall() {
		//This is very ugly. Find a better solution.
		//Add wallPieces manually.
		parts.add(new WallPiece(x+8, y-4, 4, color));
		//Next row
		parts.add(new WallPiece(x+4, y, 4, color));
		parts.add(new WallPiece(x+8, y, 4, color));
		parts.add(new WallPiece(x+12, y, 4, color));
		//Next row
		parts.add(new WallPiece(x, y+4, 4, color));
		parts.add(new WallPiece(x+4, y+4, 4, color));
		parts.add(new WallPiece(x+8, y+4, 4, color));
		parts.add(new WallPiece(x+12, y+4, 4, color));
		parts.add(new WallPiece(x+16, y+4, 4, color));
		//Next row
		parts.add(new WallPiece(x, y+8, 4, color));
		parts.add(new WallPiece(x+4, y+8, 4, color));
		parts.add(new WallPiece(x+8, y+8, 4, color));
		parts.add(new WallPiece(x+12, y+8, 4, color));
		parts.add(new WallPiece(x+16, y+8, 4, color));		
		//Next row
		parts.add(new WallPiece(x, y+12, 4, color));
		parts.add(new WallPiece(x+4, y+12, 4, color));
		parts.add(new WallPiece(x+8, y+12, 4, color));
		parts.add(new WallPiece(x+12, y+12, 4, color));
		parts.add(new WallPiece(x+16, y+12, 4, color));				
	}
	
	public void render(Screen screen) {
		for (WallPiece w : parts) {
			w.render(screen);
		}
	}
}
