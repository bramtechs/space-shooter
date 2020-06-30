package objects.shop.items;

import objects.shop.Shop;
import graphics.SpriteLoader;
import objects.player.PlayerData;

public class PowerBullets extends ShopItem {
	
	public PowerBullets(int id, Shop s) {
		super(id, s,750,2f, SpriteLoader.items.powerbullets, "POWER BULLETS");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void buy() {
		PlayerData.addPowerBullets();
		super.buy();
	}
}
