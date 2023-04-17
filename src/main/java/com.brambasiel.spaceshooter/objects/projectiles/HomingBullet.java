package objects.projectiles;

import java.awt.Color;

import com.brambasiel.spaceshooter.BasicMath;
import engine.Game;
import objects.Handler;
import objects.player.Player;

public class HomingBullet extends Bullet {

	float xx,yy;
	double angle;
	
	public HomingBullet(float x, float y, boolean enemy, Color c, float damage, int width, float speed) {
		super(x, y, enemy, c, damage, width, speed);
		
		if (enemy) {
			xx = Player.obj.x;
			yy = Player.obj.y;
		}
		angle = Math.toRadians(BasicMath.getAngle((int)x, (int)y, (int)xx,(int)yy));
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (enemy) {
			chasePlayer();
			checkPlayerCollision();
		}else {
			chaseEnemy();
			checkEnemyCollision();
		}
		
		checkBulletCollision();
		
		//Despawn out of bounds
		if (y < -10) {
			destroy();
		}
		else if (y > Game.h+10) {
			destroy();
		}
		
		hit.move((int)x, (int)y, (int)width, (int)width*2);
	}
	
	void chaseEnemy() {
		
	}
	void chasePlayer() {
		x += speed*Math.cos(angle)*0.1f;
		y += speed*Math.sin(angle)*0.1f;
	}
	
	@Override
	void checkBulletCollision() {
		for (int i = 0; i < Handler.obj.bullets.size(); i++)
		{
			Bullet b = Handler.obj.bullets.get(i);
			if (b.equals(this)) continue;
			if (b.hit.isCollidingHitbox(hit) && !b.enemy) {
				Game.print("Bullet touched another");
				destroy();
			}
		}
	}

}
