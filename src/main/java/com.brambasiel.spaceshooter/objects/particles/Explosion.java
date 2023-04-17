package objects.particles;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import audio.AudioPlayer;
import com.brambasiel.spaceshooter.BasicMath;
import engine.Game;
import objects.GameObject;
import objects.Handler;
import objects.ID;

public class Explosion extends GameObject { //Boom!!!	
	
	List<Particle> parts = new ArrayList<Particle>();
	
	Point spawn;
	float force, lifetime;
	Color start;
	
	public Explosion(Point spawn,float force,int amount,float size,float lifetime, Color start){
		super(spawn.x,spawn.y,ID.Particle);
		this.spawn = spawn;
		this.force = force;
		this.lifetime = lifetime;
		this.start = start;
		for (int i = 0; i < amount; i++) {
			Particle p = new Particle(spawn.x,spawn.y,size);
			parts.add(p);
			Handler.obj.addObj(p);
		}
		AudioPlayer.play("explosion");
	}
	
	class Particle extends GameObject{
		float velx, vely, size, time;
		Color c;
		Particle(int x, int y, float size){
			super(x,y,ID.Particle);
			vely = BasicMath.randFloat(-force, force);
			velx = BasicMath.randFloat(-force, force);
			c = start;
			this.size = size;
		}
		@Override
		public void update() {
			time += 1f/lifetime+ BasicMath.randFloat(-0.1f, 0.1f);
			x += velx;
			y += vely;
			if (velx >= 0f)
				velx -= 0.1f;
			else if (velx < 0f)
				velx += 0.1f;
			if (vely >= 0f)
				vely -= 0.1f;
			else if (vely < 0f)
				vely += 0.1f;
			
			if (time > 2f) {
				Handler.obj.deleteObj(this);
				parts.remove(this);
			}
		}
		@Override
		public void draw() {
			Game.g.setColor(c);
			Game.g.fillOval((int)x, (int)y, (int)size, (int)size);
		}
	}
	
	@Override
	public void update() {
		//If done
		if (parts.size() == 0) {
			Game.print("Explosion done");
			Handler.obj.deleteObj(this);
		}
	}
	
	@Override
	public void draw() {}
}
