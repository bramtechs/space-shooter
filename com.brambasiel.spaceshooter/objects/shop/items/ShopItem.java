package objects.shop.items;

import java.awt.Color;
import java.awt.image.BufferedImage;

import engine.Game;
import gui.primitives.Button;
import objects.shop.Shop;
import objects.player.PlayerData;

public abstract class ShopItem extends Button {
	
	public long cost;
	float priceMult;
	byte level;
	BufferedImage icon;
	String name;
	Color txtcol;
	
	public ShopItem(int id, Shop s,long cost,float priceMult, BufferedImage icon, String name) {
		super(s.x+10 + (id*90), s.y+10, 80, 80, "", Color.GRAY, Color.GRAY);
		this.icon = icon;
		this.cost = cost;
		this.priceMult = priceMult;
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	float buyDelay = 0f;
	@Override
	public void pressed() {
		if (PlayerData.o.score > cost && buyDelay > 0.5f) {
			buyDelay = 0f;
			buy();
		}
	}
	
	public void buy() {
		PlayerData.o.score -= cost;
		level++;
		cost *= priceMult;
	}
	
	@Override
	public void update() {
		super.update();
		
		if (PlayerData.o.score > cost) {
			txtcol = Color.GREEN;
		}
		else {
			txtcol = Color.LIGHT_GRAY;
		}
		buyDelay += Game.deltaTime;
	}
	
	@Override
	public void draw() {
		super.draw();
		Game.g.drawImage(icon, x,y,w,h,null);
		Game.g.setColor(txtcol);
		Game.g.drawString(name, x,y+95);
		Game.g.drawString("Level " + level, x,y+115);
		Game.g.drawString("$ " + cost, x, y+135);
		
		//Draw overlay
		if (PlayerData.o.score < cost) {
			Game.g.setColor(new Color(255,0,0,100));
			Game.g.fillRect(x, y, w, h);
		}
		
	}
}
