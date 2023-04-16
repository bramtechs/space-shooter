package states.menu;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.BasicMath;
import engine.Game;
import gui.fonts.FontLoader;
import gui.fonts.FontRenderer;
import gui.primitives.Button;
import states.GameState;
import states.State;

public class MainMenu extends State {

	final String TITLE_TEXT = "RETRO SPACE COMMANDER";

	public MainMenu() {

		Button playButton = new Button("Play", 120 * 2, 80).position(Game.getCenter().x - 120, 150)
				.colors(new Color(64, 156, 88), new Color(85, 201, 116)).link(() -> {
					startGame();
				});
		addObject(playButton);

		Button quitButton = new Button("Quit", 120 * 2, 80).position(Game.getCenter().x - 120, 150 + 80 + 20)
				.colors(new Color(235, 108, 82), new Color(235, 137, 117)).link(() -> {
					Game.running = false;
				});
		addObject(quitButton);

		// Spawn asteroids
		int amount = BasicMath.randInt(100, 120);
		for (int i = 0; i < amount; i++) {
			Asteroid asteroid = new Asteroid();
			addObject(asteroid);
		}
	}

	private void startGame() {
		GameState game = new GameState();
		State.switchState(game);
	}

	@Override
	public void draw(Graphics2D graphics) {

		// draw title
		int titleX = Game.w / 2 - 190;
		int titleY = 50;
		FontRenderer.drawText(titleX, titleY, 18, TITLE_TEXT, FontLoader.blue);

		super.draw(graphics);
	}
}
