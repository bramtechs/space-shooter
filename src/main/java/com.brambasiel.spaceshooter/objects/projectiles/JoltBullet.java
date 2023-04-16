package objects.projectiles;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Game;

public class JoltBullet extends Bullet {

	int startX, startY;
	boolean right;
	Color col;
	public JoltBullet(float x, float y, boolean enemy, float damage, float speed, Color col) {
		super(x, y, enemy, Color.magenta, damage, 10, speed);
		startX = (int) x;
		startY = (int) y;
		this.col = col;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (enemy) {
			y += speed*0.1f;
			checkPlayerCollision();
		}else {
			y -= speed*0.1f;
			checkEnemyCollision();
		}
		
		float distance = y-startY;
		x = (float)(startX + (Math.sin(distance/30f)-0.5f)*30f );
		
		checkBulletCollision();
		
		//Despawn out of bounds
		if (y < -10) {
			destroy();
		}
		else if (y > Game.size.y+10) {
			destroy();
		}
	}
	
	@Override
	public void draw(Graphics2D graph) {
		graph.setColor(c);
		int xx = (int)(x-width/2);
		int yy = (int)(y);
		int ss = (int)(width);
		
		graph.setColor(col);
		graph.fillOval(xx,yy,ss,ss);
	}
}
