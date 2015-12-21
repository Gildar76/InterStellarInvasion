package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.graphics.Sprite;

public class Invader extends Mob {
	


	public Invader(int x, int y, Sprite sprite, Sprite projectileSprite) {
		super(x, y, sprite, projectileSprite );
		this.shootDelay = rand.nextInt(120) + 10;
		this.timeUntilNextShot = this.shootDelay;
		this.direction = Direction.RIGHT;
		this.velocity = 0.05;
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
