package net.gildargaming.entity;
import java.util.ArrayList;
import java.util.List;

import net.gildargaming.Direction;
import net.gildargaming.audio.SoundEffect;
import net.gildargaming.entity.*;
import net.gildargaming.graphics.Screen;
import net.gildargaming.graphics.Sprite;
import net.gildargaming.world.FixedWorld;


public class Invadergroup {
	public ArrayList<Invader> invaders;
	public int invadorCount;
	public int topLeftX;
	public int topLeftY;
	public int distX, distY;
	private int groupSize;
	private int moveCounter = 0;
	
	public Invadergroup(int topLeftX, int topLeftY, int distX, int distY, int groupSize) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.invadorCount = 0;
		this.distX = distX;
		this.distY = distY;
		this.invaders = new ArrayList<Invader>();
		this.groupSize = groupSize;
		
		
	}

	public void addInvader(Sprite sprite, Sprite projectileSprite, SoundEffect shootSound, SoundEffect explosionSound) {
		//Get position of last invader before adding the new one.
		int invaderPosX = 0;
		int invaderPosY = 0;
		if (this.invaders.size() > 0) {
			invaderPosX = invaders.get(invaders.size() - 1).x + invaders.get(invaders.size() - 1).getWith() + distX;
			invaderPosY = invaders.get(invaders.size() - 1).y;
			
			//invaderPosY = 0;
		
			if ((this.invaders.size()) % groupSize == 0) {
				//System.out.println("inside if");
				invaderPosY = invaders.get(invaders.size() - 1).y + invaders.get(invaders.size() - 1).getHeight() + distY;
				invaderPosX = 0;
			}
			
		}

		invaders.add(new Invader(invaderPosX, invaderPosY, sprite, projectileSprite, shootSound, explosionSound ));
		
		
	}
	
	public void updateGroup(int elapsedTimeMilisec, int left, int right, FixedWorld level) {
		int maxRight = 0; //keeps track of the right boundary of the invadergroup
		int maxLeft = 99999;
		//Create hashset to reload 
		for (Invader inv : invaders) {
			inv.update(elapsedTimeMilisec, level);
			if (inv.x + inv.getWith() > maxRight) {
				maxRight = inv.x + inv.getWith();
		
				
			}
			if (inv.x <= maxLeft) {
				maxLeft = inv.x;
			}

			
		}
		if (maxRight >= right - 5) {
			moveCounter++;
			for (Invader inv : invaders) {
				inv.setDirection(Direction.LEFT);
				

			}

		} else if (maxLeft <= left) {
			moveCounter++;
			for (Invader inv : invaders) {
				inv.setDirection(Direction.RIGHT);
				

			}			
		}
		
		if (moveCounter > 10) {
			for (Invader inv : invaders) {
				inv.y += this.distY;
				if (inv.shootDelay > 2.0)inv.shootDelay = inv.shootDelay / 2;
				if (inv.velocity < 0.75)inv.velocity = inv.velocity * 1.2;
				
			}
			moveCounter = 0;
		}
		
		//System.out.println(maxLeft);
		//System.out.println(left);
		
		//Remove inactive invaders
		for (int i = this.invaders.size() - 1; i >= 0; i--) {
			if (invaders.get(i).isRemoved()) {
				invaders.remove(i);
			}
		}
	}
	
	public void renderGroup(Screen screen) {
		for (Invader inv : invaders) {
			inv.render(screen);;
		}
	}
		
}



