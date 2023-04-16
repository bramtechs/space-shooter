package objects.shop.items;

import graphics.SpriteLoader;
import objects.player.PlayerData;
import objects.shop.Shop;

public class MaxHealth extends ShopItem {

	public MaxHealth(int id, Shop s) {
		super(id, s,500,1.7f, SpriteLoader.items.maxhealth, "MAX HEALTH");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void buy() {
		PlayerData.addMaxHealth();
		super.buy();
	}
}
