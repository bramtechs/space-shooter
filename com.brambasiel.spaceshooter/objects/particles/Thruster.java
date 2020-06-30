package objects.particles;

import java.awt.Color;
import java.util.ArrayList;

import engine.Game;
import objects.GameObject;
import objects.Handler;
import objects.ID;

public class Thruster extends GameObject {

	float delayBetweenSpawns = 0.05f;
	float lifeTime = 800f;
	Color color = Color.CYAN;

	class Particle extends GameObject {
		int a = 255;
		Color col;
		Color origColor;

		public Particle(float x, float y) {
			super(x, y, ID.Particle);
			origColor = color;
		}

		@Override
		public void update() {
			y += Game.deltaTime * 50;
			a -= Game.deltaTime*lifeTime;
			if (a > 0) {
				col = new Color(origColor.getRed(), origColor.getGreen(), origColor.getBlue(), Math.max(1,a));
			} else {
				Handler.obj.deleteObj(this);
				particles.remove(this);
			}
		}

		@Override
		public void draw() {
			// TODO Auto-generated method stub
			Game.g.setColor(col);
			Game.g.fillRect((int) x, (int) y, 6, 6);
		}

		void destroy() {
			Handler.obj.deleteObj(this);
		}

	}

	ArrayList<Particle> particles;

	public Thruster() {
		super(-200, -50, ID.Particle);
		particles = new ArrayList<Particle>();
	}

	public void move(float x, float y) {
		this.x = x;
		this.y = y;
	}

	void spawn(Particle p) {
		particles.add(p);
		Handler.obj.addObj(p);
	}

	private float delay;

	@Override
	public void update() {
		// TODO Auto-generated method stub
		delay += Game.deltaTime;
		if (delay > delayBetweenSpawns) {
			delay = 0f;
			spawn(new Particle(x, y));
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
