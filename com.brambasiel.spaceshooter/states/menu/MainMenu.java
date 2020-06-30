package states.menu;

import java.util.ArrayList;
import java.util.List;

import engine.Game;
import gui.buttons.PlayButton;
import gui.buttons.QuitButton;
import gui.primitives.Button;
import gui.primitives.Title;

public class MainMenu {
	
	Title t;
	Button[] buttons;
	List<Asteroid> asteroids = new ArrayList<Asteroid>();

	public MainMenu() {
		
		t = new Title();
		buttons = new Button[]{
				new PlayButton(Game.getCenter().x-120,150,120*2,80),
				new QuitButton(Game.getCenter().x-120,150+80+20,120*2,80)
		};
		
		//Spawn asteroids
		for (int i = 0; i < engine.BasicMath.randInt(100, 120); i++) {
			asteroids.add(new Asteroid());
		}
	}
	
	public void update() {
		for (Asteroid a : asteroids) {
			a.update();
		}
		for (Button b : buttons) {
			b.update();
		}
	}
	
	public void draw() {
		for (Asteroid a : asteroids) {
			a.draw();
		}
		for (Button b : buttons) {
			b.draw();
		}
	}
}
