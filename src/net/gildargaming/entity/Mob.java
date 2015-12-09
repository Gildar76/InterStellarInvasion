package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.graphics.Screen;
import net.gildargaming.graphics.Sprite;
import net.gildargaming.world.FixedWorld;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected boolean moving = false;

	protected double acceleration = 0.1;
	protected double velocity = 0.1;
	protected int width, height, right, bottom;

	protected boolean wrap = false; //Setting this to true will cause the Mob to wrap arpound the screen.
	
	protected Direction direction;
	protected final int SHOOTDELAY = 1;
	protected double timeUntilNextShot;
	Sprite projectileSprite;	
	
	public Mob(int x, int y, Sprite sprite, Sprite projectileSprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.projectileSprite = projectileSprite;
		this.width = sprite.getSize();
		this.height = sprite.getSize();
		
	}

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
	
	public void update(int elapsedTimeMilisec, FixedWorld level) {
		this.move(elapsedTimeMilisec);
		this.timeUntilNextShot -= elapsedTimeMilisec;
		this.shoot(level, true, 180);
		
	}
	
	public void update(int eleapsedTimeMilisec) {
		this.move(eleapsedTimeMilisec);
		//System.out.println("In update no shooting");
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
	
	protected void shoot(FixedWorld level, boolean useRandomDelay, int angle) {
		if (timeUntilNextShot > 0) return;
		level.projectileList.add(new Projectile(x, y, projectileSprite , 0.1, angle  ));
		if (useRandomDelay) {
			timeUntilNextShot = this.rand.nextInt(SHOOTDELAY) * 1000;
		} else {
			timeUntilNextShot = this.SHOOTDELAY * 1000;			
		}

		System.out.println(this.timeUntilNextShot);
	}
}
