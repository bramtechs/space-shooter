package objects.shop;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import engine.Game;
import gui.Widget;
import gui.primitives.Label;
import objects.player.PlayerData;
import objects.shop.items.*;
import objects.shop.items.MaxHealth;

public class Shop extends Widget {

	public int width, height;

	List<Widget> widgets = new ArrayList<Widget>();
	List<ShopItem> items = new ArrayList<ShopItem>();
	boolean itemAvailable = false;
	Label itemAvailableLabel;

	public Shop() {
		super(Game.w / 2 - 400 / 2, Game.h / 2 - 200 / 2);
		this.width = 400;
		this.height = 200;

		// Create widgets
		widgets.add(new CloseButton(this));

		items.add(new PowerBullets(1, this));
		items.add(new MaxHealth(0, this));

		itemAvailableLabel = new Label(20, Game.h - 100, false);
	}

	@Override
	public void update() {
		for (int i = 0; i < widgets.size(); i++) {
			Widget w = widgets.get(i);
			w.update();
		}
		itemAvailable = false;
		for (int i = 0; i < items.size(); i++) {
			ShopItem item = items.get(i);
			item.update();
		}
	}

	@Override
	public void draw() {
		Game.g.setColor(Color.DARK_GRAY);
		Game.g.fillRect(x, y, width, height);
		Game.g.setColor(Color.GRAY);
		Game.g.drawRect(x, y, width, height);

		for (int i = 0; i < widgets.size(); i++) {
			widgets.get(i).draw();
		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).draw();
		}
	}

	public void priceWatcher() {
		// TODO Auto-generated method stub
		for (int i = 0; i < items.size(); i++) {
			ShopItem item = items.get(i);
			if (item.cost <= PlayerData.getScore()) {
				itemAvailable = true;
				itemAvailableLabel.change("ITEM AVAILABLE", Color.GREEN, false);
				itemAvailableLabel.update();
			}
		}
	}

	public void drawAvailable() {
		if (itemAvailable) {
			itemAvailableLabel.draw();
		}
	}
}
