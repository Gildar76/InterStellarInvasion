package net.gildargaming.entity;

import net.gildargaming.Direction;
import net.gildargaming.ProjectileType;
import net.gildargaming.graphics.Screen;
import net.gildargaming.graphics.Sprite;

public class Projectile extends Mob {
	private double velocity;
	private int angle;
	Sprite sprite;

	
	public Projectile(int x, int y, Sprite sprite, double velocity, int angle, ProjectileType pType) {
		super(x, y, sprite);
		this.velocity = velocity;
		this.angle = angle;
		this.clamp = false;
		this.projectileType = pType;

		
	}
	
	public void move(int eleapsedTimeMilisec) {


		float angleRadians = (float)Math.PI / (float)180 * angle;
        //Now calculate vertical and horizontal velocity.
        int vox = (int)(velocity*eleapsedTimeMilisec * (float)Math.sin(angleRadians));
        int voy = -1* (int)(velocity* eleapsedTimeMilisec * (float)Math.cos(angleRadians));

        x += vox;
        y += voy;
        
		right = x + width;
		bottom = y + height;
	}
	
	public ProjectileType getType() {
		return this.projectileType;
	}
	
}
