package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.graphics.Screen;
import net.gildargaming.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected boolean moving = false;

	protected double acceleration = 0.1;
	protected double velocity = 0.1;
	protected int width, height, right, bottom;

	protected boolean wrap = false; //Setting this to true will cause the Mob to wrap arpound the screen.
	
	protected Direction direction;
	
	public Mob(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.width = sprite.getSize();
		this.height = sprite.getSize();
		
	}

	public void startMoving(Direction dir) {
		this.direction = dir;
		
	}
	
	public void move(int eleapsedTimeMilisec) {

		if (direction == Direction.LEFT) x -= (int)(velocity * eleapsedTimeMilisec);
		if (direction == Direction.RIGHT) x += (int)(velocity * eleapsedTimeMilisec);		 
		if (direction == Direction.UP) y -= (int)(velocity * eleapsedTimeMilisec);
		if (direction == Direction.DOWN) y += (int)(velocity * eleapsedTimeMilisec);		 
		right = x + width;
		bottom = y + height;
	}
	
	public void update(int eleapsedTimeMilisec) {
		this.move(eleapsedTimeMilisec);
	}

	
	public boolean collision() {
		return false;
	}
	
	public void render(Screen screen) {
		if (!wrap) {
			x = (x < 0) ? 0 : ((x > screen.getWidth() - sprite.getSize()) ? screen.getWidth() - sprite.getSize() : x);
			y = (y < 0) ? 0 : ((y > screen.getHeight() - sprite.getSize()) ? screen.getHeight() - sprite.getSize() : y);
		}
		screen.renderObject(x, y, sprite);
	}
	
	public int getWith() {
		return width;
		
	}
	
	public int getHeight() {
		return height;
		
	}
}
