package com.brambasiel.spaceshooter.states.menu;

import java.awt.Color;
import java.awt.Graphics2D;

import com.brambasiel.spaceshooter.AssetLoader;
import com.brambasiel.spaceshooter.BasicMath;
import com.brambasiel.spaceshooter.GameWindow;
import com.brambasiel.spaceshooter.gui.PixmapFont;
import com.brambasiel.spaceshooter.states.State;
import com.brambasiel.spaceshooter.gui.Button;

public class MainMenuState extends State {

	final String TITLE_TEXT = "RETRO SPACE COMMANDER";

	public MainMenuState(MainMenuResponses responses) {

		Button playButton = new Button("Play", 120 * 2, 80).position(GameWindow.getCenter().x - 120, 150)
				.colors(new Color(64, 156, 88), new Color(85, 201, 116)).link(responses::pressedPlay);
		addObject(playButton);

		Button quitButton = new Button("Quit", 120 * 2, 80).position(GameWindow.getCenter().x - 120, 150 + 80 + 20)
				.colors(new Color(235, 108, 82), new Color(235, 137, 117)).link(responses::pressedQuit);
		addObject(quitButton);

		// Spawn asteroids
		int amount = BasicMath.randInt(100, 120);
		for (int i = 0; i < amount; i++) {
			Asteroid asteroid = new Asteroid();
			addObject(asteroid);
		}
	}

	@Override
	public void draw(Graphics2D graph) {

		// draw title
		int titleX = GameWindow.WIDTH / 2 - 190;
		int titleY = 50;
		PixmapFont font = AssetLoader.getInstance().getFont(Color.blue);
		font.drawText(graph, titleX, titleY, 18, TITLE_TEXT);

		super.draw(graph);
	}
}
