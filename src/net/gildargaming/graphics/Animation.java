package net.gildargaming.graphics;

public class Animation {
	public Spritesheet sheet;
	public Sprite[] frames;
	private int framePos;
	private double speed, timeSinceFrameChange = 0;
	private boolean animating, removed, looping;
	public int x,y;
	
	public Animation(int x, int y, int frameWidth, 
			int frameHeight,int startX, int startY, 
			int maxNumFrames,
			Spritesheet sheet, double speed ) {
		frames = new Sprite[maxNumFrames];
		for (int i = 1; i <= maxNumFrames; i++) {
			frames[i-1] = new Sprite(i-1+startX, startY, 16, sheet);			
		}
		this.x = x;
		this.y = y;
		this.removed = false;
		this.framePos = 0;
		this.speed = speed;
		animating = true;
		timeSinceFrameChange = 0;

	}
	
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	
	public void setAnimating(boolean animating) {
		this.animating = animating;
	}
	
	public void remove() {
		this.removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public Sprite getCurrentFrame() {
		return frames[framePos];
	}
	
	public void update(int elapsedTime) {
		System.out.println(framePos);
		if (timeSinceFrameChange * speed > 1.0) {
			if (framePos < frames.length - 1) {
				framePos++;
				timeSinceFrameChange = 0;				
			} else {
				//timeSinceFrameChange += elapsedTime;
				if (this.looping) {
					framePos = 0;
				} else {
					this.remove();
				}
				//timeSinceFrameChange += elapsedTime;				
				
			}

		} else {
			timeSinceFrameChange += elapsedTime;
		}
		
		
	}
	
	public void render(Screen screen) {
		screen.renderObject(this.x, this.y, this.getCurrentFrame());
		//System.out.println(this.x);
	}
}
