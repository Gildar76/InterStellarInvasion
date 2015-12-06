package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.graphics.Sprite;

public class Invader extends Mob {
	
	double shootingDelay;

	public Invader(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		this.shootingDelay = rand.nextDouble() * 3 + 0.5;
		this.direction = Direction.RIGHT;
		
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
