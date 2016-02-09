package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.ProjectileType;
import net.gildargaming.audio.SoundEffect;
import net.gildargaming.graphics.Screen;
import net.gildargaming.graphics.Sprite;
import net.gildargaming.world.FixedWorld;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected boolean moving = false;

	protected double acceleration = 0.1;
	protected double velocity = 0.1;
	protected int width, height, right, bottom;

	protected boolean clamp = false; //Setting this to true will allow the mob to leave the screen.
	
	protected Direction direction;
	protected int shootDelay = 2;
	protected double timeUntilNextShot;
	protected Sprite projectileSprite;	
	protected int timeSinceLastMove = 0;
	protected ProjectileType projectileType = ProjectileType.ENEMY;
	protected SoundEffect shootSound;
	protected SoundEffect explosionSound;
	
	public Mob(int x, int y, Sprite sprite, Sprite projectileSprite, SoundEffect shootSound, SoundEffect explosionSound) {
		this(x,y,sprite);
		this.projectileSprite = projectileSprite;
		this.shootSound = shootSound;
		this.explosionSound = explosionSound;

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
	
	public void move(int elapsedTimeMilisec) {

		//System.out.println(elapsedTimeMilisec);
		if (velocity * (elapsedTimeMilisec) >= 1.0) {
			if (direction == Direction.LEFT) x -= (int)(velocity * elapsedTimeMilisec);
			if (direction == Direction.RIGHT) x += (int)(velocity * elapsedTimeMilisec);		 
			if (direction == Direction.UP) y -= (int)(velocity * elapsedTimeMilisec);
			if (direction == Direction.DOWN) y += (int)(velocity * elapsedTimeMilisec);		 
			right = x + width;
			bottom = y + height;
			timeSinceLastMove = 0;
		} else {
			//System.out.println(velocity * elapsedTimeMilisec);
			this.timeSinceLastMove += elapsedTimeMilisec;
			//System.out.println(this.timeSinceLastMove);
			
		}
	}
	
	public void update(int elapsedTimeMilisec, FixedWorld level) {
		elapsedTimeMilisec += this.timeSinceLastMove;
		this.move(elapsedTimeMilisec);
		
		this.timeUntilNextShot -= elapsedTimeMilisec;
		this.shoot(level, true, 180, 0.1);
		
	}
	
	public void update(int eleapsedTimeMilisec) {
		this.move(eleapsedTimeMilisec);

	}

	
	public boolean collision() {
		return false;
	}
	
	public boolean collisionWith(Mob m, int xOffset, int yOffset) {
		return (right < m.x + xOffset || m.right - xOffset < x || bottom < m.y - yOffset || m.bottom - yOffset < y ) ? false : true;

	}
	
	public void render(Screen screen) {
		if (clamp) {
			x = (x < 0) ? 0 : ((x > screen.getWidth() - sprite.getSize()) ? screen.getWidth() - sprite.getSize() : x);
			y = (y < 0) ? 0 : ((y > screen.getHeight() - sprite.getSize()) ? screen.getHeight() - sprite.getSize() : y);
		}
		if (y + height < 0 || x + width < 0 || x > screen.getWidth() || y > screen.getHeight()) {
			this.removed = true;
		} else {
			screen.renderObject(x, y, sprite);			
		}

	}
	
	public int getWith() {
		return width;
		
	}
	
	public int getHeight() {
		return height;
		
	}
	
	protected void shoot(FixedWorld level, boolean useRandomDelay, int angle, double vel) {
		if (timeUntilNextShot > 0) return;
		level.projectileList.add(new Projectile(x, y, projectileSprite , vel, angle, this.projectileType  ));
		shootSound.play();
		if (useRandomDelay) {
			timeUntilNextShot = this.rand.nextInt(this.shootDelay + 1) * 1000;

		
		} else {
			timeUntilNextShot = this.shootDelay * 1000;			
		}


	}
	public void remove() {
		super.remove();
		//this.explosionSound.play();
		
	}
	
	public void setTimeUntilNextShot(int tuns) {
		this.timeUntilNextShot = tuns;
	}
	
	public double getTimeUntilNextShot() {
		return this.timeUntilNextShot;
	}
}
