package net.gildargaming.gamescreens;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.gildargaming.graphics.Font;
import net.gildargaming.graphics.Screen;
import net.gildargaming.world.FixedWorld;

public class GameOverScreen extends GameScreen {


	public GameOverScreen(int width, int height, String path, Font font) {
		super( width, height,  path, font);
	}
	public void update() {
		
	}

	public void render(Screen screen) {
		super.render(screen);
		font.render("GAME OVER!", screen, width/2-100, height/2-100);
		font.render("PRESS ENTER ", screen, width/2-110, height/2-50);		
	}
}
