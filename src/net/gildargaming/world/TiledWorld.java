package net.gildargaming.world;

public class TiledWorld extends World {

	
	private int width, height;
	private int[] tiles;
	
	
	public TiledWorld(int width, int height) {
		super(width, height);
		tiles = new int[width * height];
		generateWorld();
	}
	
	public TiledWorld(String path) {
		super();
		

	}
	
	public void generateWorld() {
		
	}
	
	public void loadWorld() {
		
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	public void time() {
		
	}

}
