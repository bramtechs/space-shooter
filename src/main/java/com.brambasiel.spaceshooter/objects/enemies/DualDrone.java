package objects.enemies;

import java.awt.Color;

import engine.Game;
import graphics.SpriteLoader;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.player.PlayerData;
import objects.projectiles.Bullet;

public class DualDrone extends Enemy {

	public DualDrone() {
		//    w   h sp mh id
		super(40,30,5f,10,ID.Enemy,false,50);
	}
	@Override
	public void update() {
		
		float shootsp = engine.BasicMath.clamp(WaveManager.o.wave/5f,1,3);
		if (timer > 6f/shootsp) {
			timer = 0f;
			shoot();
		}
		
		y += speed*0.1f;
		
		super.update();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(Color.orange);
		Game.g.drawImage(SpriteLoader.dualdrone, (int)x, (int)y, (int)w, (int)h,null);
		super.draw();
	}

	void shoot() {
		Bullet bl = new Bullet(x+2, y+7, true, Color.red, 20f,4,20f);
		Bullet br = new Bullet(x+w-4, y+7, true, Color.red, 20f,4,20f);
		
		Handler.obj.addObj(bl);
		Handler.obj.addObj(br);
	}
	
	@Override
	public void destroy() {
		PlayerData.o.dualDroneKills++;
		super.destroy();
	}
}
