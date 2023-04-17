package objects.enemies;

import java.awt.Color;
import java.awt.Point;

import audio.AudioPlayer;
import com.brambasiel.spaceshooter.BasicMath;
import engine.Game;
import objects.GameObject;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.particles.Explosion;
import objects.player.Player;
import objects.player.PlayerData;
import physics.Hitbox;

public abstract class Enemy extends GameObject{

	protected int X,Y,w,h;
	protected float speed, timer;
	protected boolean respawn, canMove;
	protected float horizSpeed;
	protected long score;
	
	public float health, maxHealth;
	public Hitbox hit;
	
	public Enemy(int w, int h, float speed,int maxHealth, ID id, boolean canMove, long score) {
		super(0, 0, ID.Enemy);
		respawn();
		this.w = w;
		this.h = h;
		this.score = score;
		this.speed = speed+(WaveManager.o.wave/16f);
		this.maxHealth = maxHealth;
		this.health = maxHealth+(WaveManager.o.wave/2f);
		this.canMove = canMove;
		this.hit = new Hitbox();
		this.horizSpeed = speed/2f*0.1f;
		// TODO Auto-generated constructor stub
	}

	float flipTimer;
	boolean right;
	@Override
	public void update() {
		X = (int)x;
		Y = (int)y;
		timer += Game.deltaTime+ BasicMath.randFloat(0.02f)+(WaveManager.o.wave/10000f);
		hit.move((int)x, (int)y,w,h);
		checkPlayerCollision();
		//Move back to top when to low.
		if (y > Game.h-200) {
			if (x < Game.w/2) {
				//Move left
				x -= speed*0.3f;
			}
			else {
				//Move right
				x += speed*0.3f;
			}
			
			//If outside of screen
			if (x < -w-80 || x > Game.w+w+80) {
				respawn();
				Game.print("Relocating enemy");
			}
			return;
		}
		
		//Move in random directions (muhahahahaa!!)
		if (canMove) {
			flipTimer -= Game.deltaTime;
			if (flipTimer < 0 || x < 0 || x > Game.w-w*1.5f) {
				right = !right;
				flipTimer = BasicMath.randInt(1, 5);
			}
			x += right ? -horizSpeed:horizSpeed;
		}
	}
	
	public void init() {
		
	}

	void checkPlayerCollision() {
		//Point p = new Point((int)x,(int)y);
		Hitbox b = Player.hit;
		if (b.isCollidingHitbox(hit)) {
			Player.damage(1);
		}
	}
	
	public void respawn() {
		x = BasicMath.randFloat(50, Game.w-50);
		y = -20;
	}
	
	public void damage(float d) {
		AudioPlayer.play("enemyhurt");
		health -= d;
		if (health <= 0f) {
			Game.print("Enemy died");
			destroy();
		}
	}
	
	public void destroy() {
		PlayerData.score(score);
		Explosion e = new Explosion(new Point(X,Y),5,50,3,7f,Color.yellow);
		Handler.obj.deleteObj(this);
		WaveManager.o.enemies.remove(this);
		Handler.obj.addObj(e);
	}
	
	@Override
	public void draw() {
		hit.draw();
	}
}
