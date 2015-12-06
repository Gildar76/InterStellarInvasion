package net.gildargaming.entity;

import java.util.Random;

import net.gildargaming.graphics.Screen;

public abstract class Entity {
	
	protected  int x,y;
	protected boolean removed;
	
	public final Random rand = new Random();
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
		
	}
}
