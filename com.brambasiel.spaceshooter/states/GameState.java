package states;

import engine.Game;
import gui.WidgetManager;
import objects.shop.Shop;
import input.Keyboard;
import objects.Handler;
import objects.WaveManager;
import objects.player.Player;
import objects.player.PlayerData;

public class GameState {
	
	Shop shop;
	Player player;
	
	public GameState() {
		player = new Player(Game.w/2,Game.h-120f);
		new WaveManager(25);
		shop = new Shop();
		closeShop();
	}
	
	public static boolean shopopen;
	public static void openShop() {
		if (shopopen) return;
		shopopen = true;
		WidgetManager.addWidget(shop);
		Game.print("Opened shop");
	}
	public static void closeShop() {
		if (!shopopen) return;
		shopopen = false;
		WidgetManager.closeWidget(shop);
		Game.print("Closed shop");
	}
	
	float openDelay;
	public void update() {
		shop.priceWatcher();
		WaveManager.o.update();
		handler.update();
		
		//Opening and closing shop
		if (Keyboard.IsKeyPressed('e') && !shopopen ) {
			openShop();
		}
	}
	
	public void draw() {
		handler.draw();
		shop.drawAvailable();
	}
	
	public void restart() {
		
	}
}
