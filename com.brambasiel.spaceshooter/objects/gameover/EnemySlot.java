package objects.gameover;

import java.awt.Color;
import java.awt.image.BufferedImage;

import engine.Game;
import gui.Widget;
import gui.WidgetManager;
import gui.fonts.FontLoader;
import gui.fonts.FontRenderer;

public class EnemySlot extends Widget {

	Color c;
	BufferedImage spr;
	int amount;
	
	public EnemySlot(int y, Color c, BufferedImage spr, int amount) {
		super(Game.w/2, y);
		this.c = c;
		this.spr = spr;
		this.amount = amount;
		WidgetManager.addWidget(this);
	}

	@Override
	public void update() {

	}

	@Override
	public void draw() {
		Game.g.drawImage(spr, x-140, y, 32, 32, null);
		FontRenderer.drawText((int)x, (int)(y+8), 16, Integer.toString(amount), FontLoader.getFont(c));
	}

}
