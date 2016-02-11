package net.gildargaming.gamescreens;

import net.gildargaming.graphics.Font;
import net.gildargaming.graphics.Screen;

public class GameMenuScreen extends GameScreen {

	public GameMenuScreen(int width, int height, String path, Font font) {
		super(width, height, path, font);	
	}

	public void render(Screen screen) {
		super.render(screen);
		font.render("PRESS SPACE", screen, 35, 140);
		
	}
}
