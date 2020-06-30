package gui.primitives;

import engine.Game;
import gui.fonts.FontLoader;
import gui.fonts.FontRenderer;

public class Title {
	
	int x, y;
	String text = "RETRO SPACE COMMANDER";
	
	public Title() {
		x = Game.w/2-190;
		y = 50;
	}
	
	public void draw() {
		FontRenderer.drawText(x,y,18,text,FontLoader.blue);
	}
}
