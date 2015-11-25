package net.gildargaming.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right, space;
	
	public void updateKeyState() {
		up 		= keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down 	= keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left 	= keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right 	= keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space 	= keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_ENTER];
		//Loop for testing keystate
		for (int i = 0; i < keys.length; i++) {
			if (keys[i]) {
				System.out.println(i);
			}
			
		}
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}


	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
		
	}


	public void keyTyped(KeyEvent e) {

		
	}
	
}
