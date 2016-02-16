package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.GameSettings;
import net.gildargaming.ProjectileType;
import net.gildargaming.audio.SoundEffect;
import net.gildargaming.graphics.Sprite;
import net.gildargaming.input.Keyboard;
import net.gildargaming.world.FixedWorld;
import net.gildargaming.world.World;

public class Player extends Mob {
	
	private Keyboard input;
	private int score, lives;
	private double acceleration;
	

	public Player(int x, int y, Sprite sprite, Keyboard input, Sprite projectileSprite, SoundEffect shootSound, SoundEffect explosionSound) {
		super(x,y,sprite, projectileSprite, shootSound, explosionSound);
		//this.x = x;
		//this.y = y;
		this.timeUntilNextShot = 0;
		this.input = input;
		this.clamp = true;
		//this.sprite = sprite;
		this.projectileType = ProjectileType.PLAYER;
		//this.velocity = 0;
		velocity = GameSettings.PLAYER_VELOCITY;
		shootDelay = GameSettings.PLAYER_SHOOT_DELAY;
		score = 0;
		lives = GameSettings.PLAYER_LIVES;
		acceleration = GameSettings.PLAYER_ACCELERATION;
		
	}
	//Maybe should change fixedworld to world 
	public void update(int eleapsedTimeMilisec, FixedWorld level) {
		//System.out.println("updating player");
		//System.out.println(input.right);
		if (input.left) direction = Direction.LEFT;
		if (input.right) direction = Direction.RIGHT;
		if (input.space) {
			shoot(level, false, 0, GameSettings.PLAYER_PROJECTILE_VELOCITY);
		} else {
			//Reset time until next shot if the player releases space to allow faster shots.
			this.timeUntilNextShot = 0;
		}
		//System.out.println(this.direction);
		
		/*
		if (this.direction != Direction.NONE) {
			
			if (this.velocity < GameSettings.PLAYER_VELOCITY ) {
				this.velocity += acceleration;
			}
		} else if (this.velocity > 0) {
			this.velocity -= acceleration;
		}
		*/
		this.move(eleapsedTimeMilisec);
		this.timeUntilNextShot -= (double)(eleapsedTimeMilisec);
		direction = Direction.NONE;
	
		
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLives() {
		return lives;
	}
	
	public int kill() {
		lives = lives - 1;
		return lives;
	}
	
	public void revive() {
		lives = 3;
		
	}
}
