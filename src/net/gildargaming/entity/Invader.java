package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.GameSettings;
import net.gildargaming.audio.SoundEffect;
import net.gildargaming.graphics.Sprite;

public class Invader extends Mob {
	


	public Invader(int x, int y, Sprite sprite, Sprite projectileSprite, SoundEffect shootSound, SoundEffect explosionSound, int difficultyMod) {
		super(x, y, sprite, projectileSprite, shootSound, explosionSound );
		this.shootDelay = rand.nextInt(500) + 50 / difficultyMod;
		this.timeUntilNextShot = this.shootDelay * 100 / difficultyMod;
		this.direction = Direction.RIGHT;
		this.velocity = GameSettings.INVADER_VELOCITY * (float)difficultyMod * 0.67;
		this.projectileVelocity = GameSettings.INVADER_PROJECTILE_VELOCITY * (double)difficultyMod*2.0;
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
