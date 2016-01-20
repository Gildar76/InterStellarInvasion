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
	
	public void clearPieces() {
		for (int i = this.parts.size() - 1; i >= 0; i--) {
			if (parts.get(i).isRemoved()) {
				parts.remove(i);
			}
		}
	}
	public void render(Screen screen) {
		for (WallPiece w : parts) {
			w.render(screen);
		}
	}

	public boolean collisionWith(Mob m, int xOffset, int yOffset) {
		if (x+16 < m.x + xOffset || m.right - xOffset < x || y+16 < m.y - yOffset || m.bottom - yOffset < y-4 ) {
			//This statements is a reversed, so if true, we don't have a collision.
			//System.out.println("no collision");
			return false;
			
		} else {
			//System.out.print(m.x);
			//System.out.print(", ");
			//System.out.println(this.x);
			//CHeck collision for individual pieces to determine what to destroy.
			for (WallPiece w : parts) {
				if (w.collisionWith(m, xOffset, yOffset)) {
					w.remove();
					//System.out.println("COLLISION");
					//If we found the collision we can return here
					return true;
				}
			}
			//Return false in case we never found a collision despite tha fact that we are inside the boundaries of the wall.
			return false;
		}

	}
}
