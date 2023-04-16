package states;

import java.awt.Graphics2D;

import engine.Game;
import objects.shop.Shop;
import input.Input;
import input.Keyboard;
import objects.WaveManager;
import objects.player.Player;
import objects.player.PlayerData;

public class GameState extends State {
	
	public static boolean shopopen;

	Shop shop;
	Player player;
	
	public GameState() {
		super();
		player = new Player(Game.w/2,Game.h-120f);
		new WaveManager(25);
		shop = new Shop();
		closeShop();
	}
	
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

	@Override
	public void update(float delta, float time) {
		super.update(delta, time);
		shop.priceWatcher();
		WaveManager.o.update();
		
		//Opening and closing shop
		if (Input.IsKeyPressed('e') && !shopopen ) {
			openShop();
		}
	}
	
	@Override
	public void draw(Graphics2D graph) {
		super.draw(graph);
		shop.drawAvailable();
	}
	
	public void restart() {
		
	}
}
