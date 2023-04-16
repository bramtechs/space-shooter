package objects.player;

import java.awt.Color;
import java.awt.Graphics2D;

import audio.AudioPlayer;
import engine.BasicMath;
import engine.Game;
import gui.primitives.Healthbar;
import gui.primitives.Label;
import input.Input;
import objects.Ship;
import objects.gameover.GameOverScreen;
import objects.particles.Thruster;
import objects.projectiles.Bullet;

public class Player extends Ship {

	public static int width = 40;
	public static int height = 30;

	public static float speed = 3.5f;
	public static boolean canMove = true;
	public static Player obj;
	public static float delay = 0.5f;

	static float d;

	static Healthbar bar;
	static Label score;
	static Player o;

	Thruster[] thrusters;

	boolean ended;
	
	float bulletDamage = 5f;
	float bulletSpeed = 30f;
	Color bulletColor = Color.GREEN;
	boolean rainbowBullets = false;

	public long score;
	public byte maxhealthlevel;
	public byte powerbulletslevel;
	public static PlayerData o;
	
	public int smallDroneKills, sinusDroneKills, dualDroneKills, homingDroneKills, circleDroneKills;
	
	public static void addMaxHealth() {
		o.maxhealthlevel++;
		Player.maxHealth += 20;
		Player.health = Player.maxHealth;
		Game.print("Upgraded maxHealth to " + o.maxhealthlevel);
	}

	public static void addPowerBullets() {
		o.powerbulletslevel++;
		DoubleBullets.bulletDamage += 2;
		DoubleBullets.bulletSpeed += 5;
		Player.delay -= 0.05f;
		Player.delay = Math.max(Player.delay, 0.2f);
		Color c = DoubleBullets.bulletColor;
		if (o.powerbulletslevel == 1)
			c = Color.ORANGE;
		else if (o.powerbulletslevel == 2)
			c = Color.MAGENTA;
		else if (o.powerbulletslevel == 3)
			c = Color.BLUE;
		else if (o.powerbulletslevel == 4)
			c = Color.CYAN;
		else if (o.powerbulletslevel >= 5)
			c = Color.PINK;
		if (o.powerbulletslevel >= 7)
			DoubleBullets.rainbowBullets = true;
		DoubleBullets.bulletColor = c;

		Game.print("Upgraded powerbullets to " + o.powerbulletslevel);
	}

	public static void score(long s) {
		o.score += s;
		Game.print("Scored " + s);
	}
	
	public static long getScore() {
		return o.score;
	}
	
	public Player(float x, float y) {
		super(x, y, 40, 30, 100, "playerdual.png");
		health = maxHealth;
		o = this;
		if (Player.obj != null)
			Game.print("Player already made!");
		Player.obj = this;

		//Particles
		thrusters = new Thruster[] {
				new Thruster(),
				new Thruster()
		};
	}

	@Override
	public void update() {
		if (!canMove) return;

		if (Input.IsKeyPressed('d')) {
			x += speed;
		}
		if (Input.IsKeyPressed('q')) {
			x -= speed;
		}
		if (Input.IsKeyPressed('z')) {
			y -= speed / 2f;
		}
		if (Input.IsKeyPressed('s')) {
			y += speed;
		}

		x = BasicMath.clamp(x, 0, Game.size.x);
		y = BasicMath.clamp(y, 0, Game.size.y);
		
		if (Input.IsKeyPressed('M')) { //TODO: Delete this!
			PlayerData.score(10000);
		}

		if (Input.isMouseDown() && d > delay) {
			d = 0f;
			shoot();
		}

		d += Game.deltaTime;

		int xx = (int) x - width / 2;
		int yy = (int) y;
		if (!ended) {
			hit.move(xx, yy, width, height);
		} else {
			hit.move(-300, 0, 2, 2);
		}

		// Thruster particles
		thr1.move(x - 20, y + 25);
		thr2.move(x + 14, y + 25);

		if (health <= 0 && !ended) {
			ended = true;
			died();
		}
	}

	public void shoot() {
		Color c;
		if (!rainbowBullets) {
			c = bulletColor;
		}
		else {
			int r = engine.Math.randInt(150,254);
			int g = engine.Math.randInt(150,254);
			int b = engine.Math.randInt(150,254);
			c = new Color(r,g,b);
		}
		
		//LEFT
		Bullet bl = new Bullet(p.x-Player.width/2+8, p.y-5, false, c, bulletDamage,4,bulletSpeed);
		//RIGHT
		Bullet br = new Bullet(p.x+Player.width/2-8, p.y-5, false, c, bulletDamage,4,bulletSpeed);
	
		Handler.obj.addObj(bl);
		Handler.obj.addObj(br);
	}

	@Override
	public void draw() {
		Game.g.setColor(canMove ? Color.green : Color.red);
		int xx = (int) (x - width / 2);
		int yy = (int) y;

		Game.g.drawImage(SpriteLoader.player, xx, yy, width, height, null);
	}
	
	@Override
	public void draw(Graphics2D graph) {
		
	}

	public static void damage(float d) {
		health -= d;
		AudioPlayer.play("playerhurt");
		Game.print("Damaged " + d);
	}

	public static void heal(float h) {
		health += h;
		if (health > maxHealth)
			health = maxHealth;
		Game.print("Healed " + h);
	}

	static void died() {
		Game.print("Player died");
		Handler.obj.deleteObj(thr1);
		Handler.obj.deleteObj(thr2);

		Handler.obj.deleteObj(obj);
		new GameOverScreen();
	}

}
