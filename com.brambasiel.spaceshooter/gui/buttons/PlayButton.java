package gui.buttons;

import java.awt.Color;

import gui.primitives.Button;
import objects.SceneManager;

public class PlayButton extends Button{

	public PlayButton(int x, int y, int w, int h) {
		super(x, y, w, h, "Play", new Color(64, 156, 88), new Color(85, 201, 116));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		SceneManager.loadGame();
	}
}
