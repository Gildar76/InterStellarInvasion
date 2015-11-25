package net.gildargaming;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import net.gildargaming.graphics.Screen;
import net.gildargaming.input.*;

public class Game extends Canvas {

	private JFrame gameWindow;
	public static int width = 300;
	public static int height = width * 4/3;
	public static int scale = 2;
	public boolean running = false;
	private BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private static double updateFreq = 60.0;
	private String gameTitle = "InterStellarInvasion";
	private int[] pixels = ((DataBufferInt)bImage.getRaster().getDataBuffer()).getData();
	private Screen screen;
	private Keyboard kb;
	
	//Default Constructor
	public Game() {
		screen = new Screen(width,height);

		this.initializeGame();
	}
		
	//Constructor with settings
	public Game(int w, int h, int s) {
		width = w;
		height = h;
		scale = s;
		screen = new Screen(width,height);
		this.initializeGame();
	}
	
	
	public void initializeGame() {
		this.startWindow();
		this.kb = new Keyboard();
		addKeyListener(kb);
	}
	//crates and opens the game window.
	public void startWindow() {
		Dimension size = new Dimension(width * scale, height * scale);
		this.setPreferredSize(size);
		this.gameWindow = new JFrame();
		this.gameWindow.setResizable(false);
		this.gameWindow.setTitle(gameTitle);
		this.gameWindow.add(this);
		this.gameWindow.setVisible(true);
		this.gameWindow.setSize(size);
		
	}
	
	public void update() {
		kb.updateKeyState();
		
	}
	
	public void render() {
		BufferStrategy buffer = getBufferStrategy(); 
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		screen.render();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
			
		}
		Graphics drawGraphics = buffer.getDrawGraphics();
		drawGraphics.fillRect(0, 0, getWidth(), getHeight());
		drawGraphics.drawImage(bImage, 0, 0, getWidth(), getHeight(), null);
		drawGraphics.dispose();
		buffer.show();
	}
	
	//Initializes everything and starts the main game loop
	public void run() {
		running = true;
		//Set a variable that stores the last time the update loop was run.
		long prevTime = System.nanoTime();
		//Set the time of one "tick"
		final double tickTime = 1000000000.0 / updateFreq;
		System.out.print(tickTime);
		double delta = 0;
		//Test variable to help keep track of FPS
		long timer = System.currentTimeMillis();
		//Some test variables to test speed.
		int numFrames = 0;
		int numUpdates = 0;		
		while (running) {
			//Main game loop

			//Get current time to see if we need to update
			long now = System.nanoTime();
			delta += (now - prevTime) / tickTime;
			prevTime = now;
			while (delta >= 1) {
				update();				
				delta--;
				numUpdates++;
			}
			//Our rendering does not care about speed. We jsut render as fast as possible.
			render();
			numFrames++;
			// Print fps
			if (System.currentTimeMillis() - timer > 1000) {
				gameWindow.setTitle(gameTitle + "(Updates: "+ numUpdates + " FPS: " + numFrames + ")");
				timer += 1000;
				numUpdates = 0;
				numFrames = 0;
			}
		}

	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();	
	}
}
