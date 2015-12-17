package net.gildargaming.entity;
import java.util.ArrayList;
import java.util.List;

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
	
	public Invadergroup(int topLeftX, int topLeftY, int distX, int distY, int groupSize) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.invadorCount = 0;
		this.distX = distX;
		this.distY = distY;
		this.invaders = new ArrayList<Invader>();
		this.groupSize = groupSize;
		
		
	}

	public void addInvader(Sprite sprite, Sprite projectileSprite) {
		//Get position of last invader before adding the new one.
		int invaderPosX = 0;
		int invaderPosY = 0;
		if (this.invaders.size() > 0) {
			invaderPosX = invaders.get(invaders.size() - 1).x + invaders.get(invaders.size() - 1).getWith() + distX;
			invaderPosY = invaders.get(invaders.size() - 1).y;
			
			//invaderPosY = 0;
		
			if ((this.invaders.size()) % groupSize == 0) {
				System.out.println("inside if");
				invaderPosY = invaders.get(invaders.size() - 1).y + invaders.get(invaders.size() - 1).getHeight() + distY;
				invaderPosX = 0;
			}
			
		}
		System.out.println((this.invaders.size()) % groupSize);
		System.out.println(this.invaders.size());
		System.out.println(invaderPosX);
		System.out.println(invaderPosY);
		invaders.add(new Invader(invaderPosX, invaderPosY, sprite, projectileSprite ));
		
		
	}
	
	public void updateGroup(int elapsedTimeMilisec, int left, int right, FixedWorld level) {
		int maxRight = 0; //keeps track of the right boundary of the invadergroup
		int maxLeft = 99999;
		for (Invader inv : invaders) {
			inv.update(elapsedTimeMilisec, level);
			if (inv.x + inv.getWith() > maxRight) {
				maxRight = inv.x + inv.getWith();
		
				
			}
			if (inv.x <= maxLeft) {
				maxLeft = inv.x;
			}

			
		}
		if (maxRight >= right - 5 || maxLeft <= left ) {
			for (Invader inv : invaders) {
				inv.ChangeDirection();

			}
			//System.out.println(right);
			//System.out.println(right);
		}

	}
	
	public void renderGroup(Screen screen) {
		for (Invader inv : invaders) {
			inv.render(screen);;
		}
	}
		
}



