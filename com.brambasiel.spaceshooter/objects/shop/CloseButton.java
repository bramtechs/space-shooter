package objects.shop;

import java.awt.Color;

import gui.primitives.Button;
import states.GameState;

public class CloseButton extends Button{

	public CloseButton(Shop s) {
		super(s.x+s.width-50, s.y+10, 40, 40, "X", Color.RED, new Color(255,40,40));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pressed() {
		GameState.closeShop();
	}
	
}
