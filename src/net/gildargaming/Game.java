package net.gildargaming;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.gildargaming.audio.SoundEffect;
import net.gildargaming.entity.Invader;
import net.gildargaming.entity.Invadergroup;
import net.gildargaming.entity.Player;
import net.gildargaming.entity.Projectile;
import net.gildargaming.entity.Wall;
import net.gildargaming.gamescreens.GameMenuScreen;
import net.gildargaming.gamescreens.GameOverScreen;
import net.gildargaming.graphics.Animation;
import net.gildargaming.graphics.Font;
import net.gildargaming.graphics.Screen;
import net.gildargaming.graphics.Sprite;
import net.gildargaming.graphics.Spritesheet;
import net.gildargaming.input.*;
import net.gildargaming.world.FixedWorld;


public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private Thread thread;
	private static final long serialVersionUID = 1L;
	private JFrame gameWindow;
	public static final int GROUPSIZE = 8;
	public static int width = 320	;
	public static int height = width * (3/2);
	public static int scale = 2;
	public boolean running = false;
	private BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private static double updateFreq = 60.0;
	private String gameTitle = "InterStellarInvasion";
	private int[] pixels = ((DataBufferInt)bImage.getRaster().getDataBuffer()).getData();
	private Screen screen;
	private Keyboard kb;
	private FixedWorld level;
	private Player player;
	private int elapsedTimeMilisec = 0;
	public static Spritesheet mobsheet = new Spritesheet("/sprites/mobs.png", 512);	
	public static Sprite playerSprite = new Sprite(0,31,16,mobsheet);
	public static Sprite invaderSprite = new Sprite(0,0,16,mobsheet);	
	public Invadergroup invGroup;
	public static SoundEffect playerShootSound, playerExplosionSound; 
	public static SoundEffect wallHit, invaderExplosionSound, invaderExplosionSound2, invaderShootSound, hitWallSound;
	public static Spritesheet invaderExplosionSheet;
	public static ArrayList<Animation> explosionList;
	public static Font font;
	public Spritesheet fontSheet;
	public static final int ALPHA_COLOR2 = -8454017;
	public static final int ALPHA_COLOR = -65281;
	public GameState state;
	public GameOverScreen gameOverScreen;
	public GameMenuScreen menuScreen;
	//Default Constructor
	public Game() {
		screen = new Screen(width,height);

	}
		
	//Constructor with settings
	public Game(int w, int h, int s) {
		width = w;
		height = h;
		scale = s;
		screen = new Screen(width,height);
		//this.initializeGame();

	}
	
	public synchronized void start() {
		thread = new Thread(this);
		running = true;		
		this.initializeGame();
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			this.running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void restart() {
		player.setScore(0);
		player.revive();
		//TODO: MOVE INVADERGROUP TO LEVEL
		invGroup = new Invadergroup(10, 25, 10, 10, GROUPSIZE);
		for (int i = 0; i < GROUPSIZE * GROUPSIZE; i++) {
			if (i % 2 == 0) {
				invGroup.addInvader(invaderSprite, level.invaderProjectileSprite, invaderShootSound, this.invaderExplosionSound );				
			} else {
				invGroup.addInvader(invaderSprite, level.invaderProjectileSprite, invaderShootSound, this.invaderExplosionSound );								
			}

		}		
		state = GameState.GAMERUNNING;
		
	}
	public void initializeGame() {
		this.startWindow();
		playerShootSound = new SoundEffect("./res/sounds/playerMisile.wav");
		invaderShootSound = new SoundEffect("./res/sounds/invaderShot1.wav");
		invaderExplosionSound = new SoundEffect("./res/sounds/Explosion2.wav");
		playerExplosionSound = new SoundEffect("./res/sounds/death.wav");
		invaderExplosionSound2 = new SoundEffect("./res/sounds/Explosion3.wav");
		wallHit = new SoundEffect("./res/sounds/wallHit.wav");
		invaderExplosionSheet = new Spritesheet("/sprites/explosion_spritesheet.png");
		explosionList = new ArrayList<Animation>();	
		this.kb = new Keyboard();
		addKeyListener(kb);
		level = new FixedWorld("/background/stars.png", screen.getWidth(), screen.getHeight());
		player = new Player(screen.getWidth() / 2,screen.getHeight() - screen.getHeight() / 16,playerSprite, kb, level.playerProjectileSprite, this.playerShootSound, this.playerExplosionSound);
		//TODO: MOVE INVADERGROUP TO LEVEL
		invGroup = new Invadergroup(10, 25, 10, 10, GROUPSIZE);
		for (int i = 0; i < GROUPSIZE * GROUPSIZE; i++) {
			if (i % 2 == 0) {
				invGroup.addInvader(invaderSprite, level.invaderProjectileSprite, invaderShootSound, this.invaderExplosionSound );				
			} else {
				invGroup.addInvader(invaderSprite, level.invaderProjectileSprite, invaderShootSound, this.invaderExplosionSound );								
			}

		}

		fontSheet = new Spritesheet("/fonts/arial.png");
		font = new Font(fontSheet);
		state = GameState.MENU;
	}
	//crates and opens the game window.
	public void startWindow() {
		//Game window stuff
		Dimension size = new Dimension(width * scale, height * scale);
		this.setPreferredSize(size);
		this.gameWindow = new JFrame();
		this.gameWindow.setSize(size);
		this.gameWindow.setResizable(false);

		this.gameWindow.setTitle(gameTitle);
		this.gameWindow.add(this);
		this.gameWindow.setVisible(true);

		this.gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//Create player
		
	}
	

	
	public void update() {
		kb.updateKeyState();

		
		if (state == GameState.GAMERUNNING) {
			
		
			player.update(elapsedTimeMilisec, level);
			//player.render(screen);
			invGroup.updateGroup(elapsedTimeMilisec, 0, screen.getWidth(), level);
			level.update(elapsedTimeMilisec);
			resolveCollisions();			
		} else if (state == GameState.GAMEOVER) {
			if (gameOverScreen == null) {
				gameOverScreen = new GameOverScreen(screen.getWidth(), screen.getHeight(), "/background/stars.png", font);
				return;
				//CONtinue update on next go.

			}
			if (kb.space) {
				restart();
			}
			
		} else if (state == GameState.MENU) {

			if (menuScreen == null) {
				menuScreen = new GameMenuScreen(screen.getWidth(), screen.getHeight(), "/background/stars.png", font);
				return;
				//CONtinue update on next go.
			}
			if (kb.space) {
				state = GameState.GAMERUNNING;
			}
		}
	}
	
	public void render() {
		BufferStrategy buffer = getBufferStrategy(); 
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		if (state == GameState.GAMERUNNING) {
		
	
			level.render(0,0,screen);
			player.render(screen);
			invGroup.renderGroup(screen);
			screen.render();
		} else if (state == GameState.GAMEOVER) {
			if (gameOverScreen == null) {
				gameOverScreen = new GameOverScreen(screen.getWidth(), screen.getHeight(), "/background/stars.png", font);
				return;
				//CONtinue update on next go.
			}
			gameOverScreen.render(screen);
		}  else if (state == GameState.MENU) {
			if (menuScreen == null) {
				menuScreen = new GameMenuScreen(screen.getWidth(), screen.getHeight(), "/background/stars.png", font);
				return;
				//CONtinue update on next go.
			}
			menuScreen.render(screen);
		}
		font.render("SCORE " + player.getScore(),screen, 5, 5);
		font.render("LIVES " + player.getLives(), screen, 175, 5);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
			
		}
		Graphics drawGraphics = buffer.getDrawGraphics();
		drawGraphics.drawImage(bImage, 0, 0, getWidth(), getHeight(), null);
		drawGraphics.dispose();
		buffer.show();
	}
	
	//Initializes everything and starts the main game loop
	public void run() {

		//Set a variable that stores the last time the update loop was run.
		long prevTime = System.nanoTime();
		//Set the time of one "tick"
		final double tickTime = 1000000000.0 / updateFreq;

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
				this.elapsedTimeMilisec = (int)(delta * tickTime / 1000000);
				update();
				this.elapsedTimeMilisec = 0;
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
	
	
	public void resolveCollisions() {
		//Check for collision with projectiles
		for (Projectile p : level.projectileList) {
			if (p.getType() == ProjectileType.ENEMY && p.collisionWith(player, 8, 0)) {
				//System.out.println("Player collision detected");
				int lives = player.kill();
				p.remove();
				if (lives < 1) {
					state = GameState.GAMEOVER;
				}
			} else if (p.getType() == ProjectileType.PLAYER) {
				//Wall collision will be done no matter what type the projectile is.			

				for (Invader inv : this.invGroup.invaders) {
					if (p.collisionWith(inv, 5, 10)) {
						player.setScore(player.getScore()+1);
						
						Game.invaderExplosionSound.play();
						//Start explosion
						explosionList.add(new Animation(inv.getX(), inv.getY(), 16, 16, 0, 2, 8,this.mobsheet, 0.05));
						
						inv.remove();
						p.remove();
						continue;
					}
				}

			}
			for (int i = explosionList.size() - 1 ; i >= 0; i--) {
				if (explosionList.get(i).isRemoved()) explosionList.remove(i);
			}
			
			for (Wall w : level.walls) {
				if (w.collisionWith(p, 0, -4)) {
					p.remove();
					continue;
				}

			}			
		}
	}	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();	
	}
}
