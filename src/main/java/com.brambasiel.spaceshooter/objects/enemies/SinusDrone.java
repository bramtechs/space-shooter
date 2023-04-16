package objects.enemies;

import java.awt.Color;

import engine.Game;
import graphics.SpriteLoader;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.player.PlayerData;
import objects.projectiles.Bullet;
import objects.projectiles.JoltBullet;

public class SinusDrone extends Enemy {

	public SinusDrone() {
		//    w   h sp mh id cm
		super(40,20,5f,30,ID.Enemy,true,40);
		Game.print("Sinus drone spawned");
	}

	
	@Override
	public void update() {
		
		float shootsp = engine.BasicMath.clamp(WaveManager.o.wave/20f,3,6);
		if (timer > 12f/shootsp) {
			timer = 0f;
			shoot();
		}
		
		y += speed*0.1f;
		
		//Relocation
		if (respawn) {
			respawn = false;
			x = engine.BasicMath.randFloat(50, Game.w-50);
			y = 20;
		}
		
		super.update();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(Color.orange);
		Game.g.drawImage(SpriteLoader.sinusdrone, (int)x, (int)y, (int)w, (int)(h*1.5f),null);
		super.draw();
	}
	
	void shoot() {
		Bullet b = new JoltBullet(x+w, y+h+5, true, 25f,12f, Color.magenta);
		Handler.obj.addObj(b);
	}
	
	@Override
	public void destroy() {
		PlayerData.o.sinusDroneKills++;
		super.destroy();
	}
}
