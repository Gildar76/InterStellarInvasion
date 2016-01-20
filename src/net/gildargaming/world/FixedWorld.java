package net.gildargaming.world;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;

import net.gildargaming.entity.Projectile;
import net.gildargaming.entity.Wall;
import net.gildargaming.graphics.Screen;
import net.gildargaming.graphics.Sprite;
import net.gildargaming.graphics.Spritesheet;

public class FixedWorld extends World {
	
	public int[] pixels;
	public String path;
	public ArrayList<Projectile> projectileList;
	public Spritesheet projectileSheet = new Spritesheet("/sprites/projectiles.png", 64);
	public Sprite playerProjectileSprite;
	public Sprite invaderProjectileSprite;
	public ArrayList<Wall> walls;
	
	public FixedWorld(String path, int width, int height) {
		super();
		this.path = path;
		load();
		
		projectileList = new ArrayList<Projectile>();
		playerProjectileSprite = new Sprite(0, 1,8,this.projectileSheet);
		invaderProjectileSprite = new Sprite(1, 0,8,this.projectileSheet);			
		walls = new ArrayList<Wall>();

		walls.add(new Wall(width / 10*1, height - height / 8, 4, 15*15*200));
		walls.add(new Wall(width / 10*3, height - height / 8, 4, 15*15*200));
		walls.add(new Wall(width / 10 * 5, height - height / 8, 4, 15*15*200));
		walls.add(new Wall(width / 10 * 7, height - height / 8, 4, 15*15*200));
//		walls.add(new Wall(width / 10 * 9, height - height / 8, 4, 15*15*200));

		
	}
	
	public void load() {
		try {
			//I can't comprehend what the .class does except return a class object (sort of)
			BufferedImage img =	ImageIO.read(FixedWorld.class.getResource(path));
			//We now should have the image loaded. Create a pixel array.
			width = img.getWidth();
			height = img.getHeight();
			pixels = new int[width*height];
			img.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public void update(int elapsedTimeMilisec) {
		for (Projectile p : projectileList) {
			p.update(elapsedTimeMilisec);

		}
		//Remove inactive invaders
		for (int i = this.projectileList.size() - 1; i >= 0; i--) {
			if (this.projectileList.get(i).isRemoved()) {
				this.projectileList.remove(i);
			}
		}
		for (Wall w : this.walls) {
			w.clearPieces();
		}
		for (int i = this.walls.size() - 1; i >= 0; i--) {

			if (walls.get(i).isRemoved()) {
				walls.remove(i);
			}
		}
	}
	
	
	public void render(int xOffset, int yOffset, Screen screen) {
		int x0 = xOffset;
		int y0 = yOffset;
		int x1 = x0 + screen.getWidth();
		int y1 = y0 + screen.getHeight();

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				screen.pixels[x + y * screen.getWidth()] = pixels[x + y * width];
				
				
				
			}
		}
		for (Projectile p : projectileList) {
			p.render(screen);
		}
		for (Wall w : walls) {
			w.render(screen);
			
		}
	}


}
