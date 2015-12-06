package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.graphics.Sprite;
import net.gildargaming.input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	
	
	public Player(int x, int y, Sprite sprite, Keyboard input) {
		super(x,y,sprite);
		//this.x = x;
		//this.y = y;
		this.input = input;
		//this.sprite = sprite;
	}
	
	public void update(int eleapsedTimeMilisec) {
		//System.out.println("updating player");
		//System.out.println(input.right);
		if (input.left) direction = Direction.LEFT;
		if (input.right) direction = Direction.RIGHT;
		//System.out.println(this.direction);
		
		
		this.move(eleapsedTimeMilisec);
		
		direction = Direction.NONE;
		
		
	}
	
	
}
