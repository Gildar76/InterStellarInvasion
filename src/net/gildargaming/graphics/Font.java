package net.gildargaming.graphics;

public class Font {
	Spritesheet sheet;
	public Sprite[] sprites;
	public String characters = "ABCDEFGHIJKLM" + //
								"NOPQRSTUVWXYZ" + //
								"abcdefghijklm" + //
								"nopqrstuvwxyz" + //
								"0123456789.,''" + //
								"'\"\";:!@$%()-+";
	public Font(Spritesheet sheet) {
		this.sheet = sheet;
		sprites = this.sheet.split(16, 16);
		for (Sprite s : sprites) {
			s.replaceColor(0xff000000, 0xffffffff);
			//s.replaceColor(-16777216, 0xffffffff);
			//s.replaceColor(-65281, 0xffffffff);
			//s.replaceColor(0, 0xffffffff);
		}
		
	}
	
	public void render(String text, Screen screen, int x, int y) {
		for (int i = 0; i < text.length(); i++) {
			char currentChar = text.charAt(i);
			int index = characters.indexOf(currentChar);
			if (index > -1) screen.renderObject(x+i*sprites[index].getSize()+10, y, sprites[index]);			
		}

		
	}
}
