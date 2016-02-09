package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.ProjectileType;
import net.gildargaming.audio.SoundEffect;
import net.gildargaming.graphics.Sprite;
import net.gildargaming.input.Keyboard;
import net.gildargaming.world.FixedWorld;
import net.gildargaming.world.World;

public class Player extends Mob {
	
	private Keyboard input;
	private int score;

	

	public Player(int x, int y, Sprite sprite, Keyboard input, Sprite projectileSprite, SoundEffect shootSound, SoundEffect explosionSound) {
		super(x,y,sprite, projectileSprite, shootSound, explosionSound);
		//this.x = x;
		//this.y = y;
		this.timeUntilNextShot = 0;
		this.input = input;
		this.clamp = true;
		//this.sprite = sprite;
		this.projectileType = ProjectileType.PLAYER;
		this.velocity = 0.2;
		shootDelay = 1;
		score = 0;
	}
	//Maybe should change fixedworld to world 
	public void update(int eleapsedTimeMilisec, FixedWorld level) {
		//System.out.println("updating player");
		//System.out.println(input.right);
		if (input.left) direction = Direction.LEFT;
		if (input.right) direction = Direction.RIGHT;
		if (input.space) shoot(level, false, 0, 0.2);
		//System.out.println(this.direction);
		
		
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
}
