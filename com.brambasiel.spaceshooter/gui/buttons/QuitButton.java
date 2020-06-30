package gui.buttons;

import java.awt.Color;

import engine.Game;
import gui.primitives.Button;

public class QuitButton extends Button{

	public QuitButton(int x, int y, int w, int h) {
		super(x, y, w, h, "Quit", new Color(235, 108, 82), new Color(235, 137, 117));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		Game.running = false;
	}
	
}
