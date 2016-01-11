package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.graphics.Sprite;

public class Invader extends Mob {
	


	public Invader(int x, int y, Sprite sprite, Sprite projectileSprite) {
		super(x, y, sprite, projectileSprite );
		this.shootDelay = rand.nextInt(500) + 50;
		this.timeUntilNextShot = this.shootDelay * 100;
		this.direction = Direction.RIGHT;
		this.velocity = 0.04;
	}

	public void setDirection(Direction dir) {
		this.direction = dir;
	}
	public void ChangeDirection() {

		if (direction == Direction.RIGHT) {
			direction = Direction.LEFT;
		}
		else if (direction == Direction.LEFT) {
			direction = Direction.RIGHT;
		}
		else if (direction == Direction.DOWN) {
			direction = Direction.UP;
		}
		else if (direction == Direction.UP) {
			direction = Direction.DOWN;
		}

	}
	
	

}
