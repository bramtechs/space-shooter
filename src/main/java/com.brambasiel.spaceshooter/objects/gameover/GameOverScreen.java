package objects.gameover;

import java.awt.Color;
import java.awt.image.BufferedImage;

import engine.Game;
import graphics.SpriteLoader;
import gui.Widget;
import gui.WidgetManager;
import com.brambasiel.spaceshooter.gui.Label;
import objects.player.PlayerData;

public class GameOverScreen extends Widget {
	
	Label title;
	final int ITEMS = 5;
	
	public GameOverScreen() {
		super(Game.w / 2, Game.h / 2);
		WidgetManager.addWidget(this);
		created = new boolean[ITEMS];
	}

	int yy = y - 200+60;

	void addItem(int id) {
		new EnemySlot(yy, getColor(id), getSprite(id), getAmount(id));
		yy += 45;
	}

	BufferedImage getSprite(int id) {
		switch (id) {
		case 0:
			return SpriteLoader.smalldrone;
		case 1:
			return SpriteLoader.dualdrone;
		case 2:
			return SpriteLoader.homingdrone;
		case 3:
			return SpriteLoader.sinusdrone;
		case 4:
			return SpriteLoader.circleDrone;
		}
		return null;
	}

	int getAmount(int id) {
		switch (id) {
		case 0:
			return PlayerData.o.smallDroneKills;
		case 1:
			return PlayerData.o.dualDroneKills;
		case 2:
			return PlayerData.o.homingDroneKills;
		case 3:
			return PlayerData.o.sinusDroneKills;
		case 4:
			return PlayerData.o.circleDroneKills;
		}
		return 0;
	}
	
	Color getColor(int id) {
		switch (id) {
		case 0:
			return Color.YELLOW;
		case 1:
			return Color.RED;
		case 2:
			return Color.MAGENTA;
		case 3:
			return Color.PINK;
		case 4:
			return Color.GREEN;
		}
		return Color.WHITE;
	}
	
	float timer;
	boolean[] created;
	@Override
	public void update() {
		// TODO Auto-generated method stub
		for (int i = 0;i < ITEMS; i++) {
			if (timer > i*0.3f && !created[i]) {
				created[i] = true;
				addItem(i);
			}
		}
		
		timer += Game.deltaTime;
		flashTimer += Game.deltaTime;
	}
	
	float flashTimer;
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(Color.DARK_GRAY);
		Game.g.fillRect(x - 150, y - 200, 300, 400);
		Game.g.setColor(Color.LIGHT_GRAY);
		Game.g.drawRect(x - 150, y - 200, 300, 400);
		
		if (flashTimer < 0.7f) {
			Game.g.setColor(Color.orange);
			FontRenderer.drawText(x-64, y - 180, 16, "GAME OVER", FontLoader.red);
		}
		else if (flashTimer > 1.4f) {
			flashTimer = 0f;
		}
	}
}
