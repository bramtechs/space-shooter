package objects;

import gui.WidgetManager;
import states.GameState;
import states.menu.MainMenu;

public class SceneManager {
	static GameState game;
	static MainMenu menu;
	
	public static void unloadAll() {
		game = null;
		menu = null;
		WidgetManager.clean();
	}
	
	public static void loadGame() {
		unloadAll();
		game = new GameState();
	}
	
	public static void loadMenu() {
		unloadAll();
		menu = new MainMenu();
	}
	
	public static void update() {
		if (game != null) {
			game.update();
		}
		else if (menu != null) {
			menu.update();
		}
	}
	
	public static void draw() {
		if (game != null) {
			game.draw();
		}
		else if (menu != null) {
			menu.draw();
		}
	}
}
