package objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import engine.Game;
import gui.primitives.Label;
import gui.primitives.Message;
import objects.enemies.CircleDrone;
import objects.enemies.DualDrone;
import objects.enemies.Enemy;
import objects.enemies.HomingDrone;
import objects.enemies.MissileDrone;
import objects.enemies.SinusDrone;
import objects.enemies.SmallDrone;
import objects.enemies.bosses.MegaShip;
import objects.enemies.bosses.MegaSinusDrone;

public class WaveManager extends GameObject {
	
	public int wave;
	public Color waveColor;
	public static WaveManager o;
	
	//UI
	Label waveText,enemiesText;
	
	public WaveManager(int wave) {
		o = this;
		this.wave = wave;
		waveColor = Color.green;
		nextWave();
		
		waveText = new Label(20,60,false);
		WidgetManager.addWidget(waveText);
		enemiesText = new Label(20,80,true);
		WidgetManager.addWidget(enemiesText);
	}

	public void nextWave() {
		wave++;
		say("Wave " + wave, waveColor);
		if (wave <= 3) {
			wave1to3();
		}
		else if (wave <= 6) {
			wave4to6();
		}
		else if (wave == 10) {
			boss1();
		}
		else if (wave <= 12) {
			wave7to12();
		}
		else if (wave <= 17) {
			wave13to17();
		}
		else if (wave == 20) {
			boss2();
		}
		else if (wave <= 23) {
			wave18to23();
		}
		else if (wave <= 29) {
			wave24to29();
		}
		else {
			randomWave();
		}
		done = false;
	}
	
	static float endTimer = 5f;
	public static void endWave() {
		Game.print("Wave ended");
		say("Wave cleared!",Color.yellow);
	}
	
	public static void say(String txt, Color c) {
		Handler.obj.addObj(new Message(txt,c));
	}
	
	///////////////////////////////////////////////
	
	void wave1to3() {
		for (int i = 0; i < wave*2; i++) {
			spawn(new SmallDrone());
		}
		waveColor = Color.GREEN;
	}
	void wave4to6() {
		for (int i = 0; i < wave/3; i++) {
			spawn(new DualDrone());
		}
		for (int i = 0; i < wave/1.5; i++) {
			spawn(new SmallDrone());
		}
		waveColor = Color.GREEN;
	}

	void wave7to12() {
		for (int i = 0; i < wave/3; i++) {
			spawn(new SmallDrone());
		}
		for (int i = 0; i < wave/6; i++) {
			spawn(new HomingDrone());
		}
		for (int i = 0; i < wave/4; i++) {
			spawn(new DualDrone());
		}	
		waveColor = Color.YELLOW;
	}
	
	void wave13to17() {
		for (int i = 0; i < wave/6; i++) {
			spawn(new SinusDrone());
		}
		for (int i = 0; i < wave/6; i++) {
			spawn(new HomingDrone());
		}
		for (int i = 0; i < wave/4; i++) {
			spawn(new SmallDrone());
		}
		waveColor = Color.ORANGE;
	}
	
	void wave18to23() {
		for (int i = 0; i < wave/6; i++) {
			spawn(new SinusDrone());
		}
		for (int i = 0; i < wave/8; i++) {
			spawn(new CircleDrone(wave));
		}
		for (int i = 0; i < wave/17; i++) {
			spawn(new HomingDrone());
		}
		for (int i = 0; i < wave/12; i++) {
			spawn(new DualDrone());
		}
	}
	
	void wave24to29() {
		for (int i = 0; i < wave/8; i++) {
			spawn(new CircleDrone(wave));
		}
		for (int i = 0; i < wave/9; i++) {
			spawn(new HomingDrone());
		}
		for (int i = 0; i < wave/6; i++) {
			MissileDrone.amountToSpawn = wave/12;
			spawn(new MissileDrone());
		}
	}

	void boss2() {
		spawn(new MegaSinusDrone());
	}
	
	void boss1() {
		Game.print("Spawning boss");
		spawn(new MegaShip());
	}
	
	void randomWave() {
		int left = (int)(wave*1.5f)+1;
		for (int i = 0; i < engine.BasicMath.randInt(0, left); i++) {
			spawn(new SmallDrone());
			left--;
		}
		for (int i = 0; i < engine.BasicMath.randInt(0, left); i++) {
			spawn(new DualDrone());
		}
		for (int i = 0; i < left; i++) {
			spawn(new HomingDrone());
		}
		waveColor = Color.RED;
	}
	
	///////////////////////////////////////////////////////////////
	
	//TIMELINE
	public void spawn(Enemy e) {
		o.enemies.add(e);
		o.queue.add(e);
	}

	public void spawnNow(Enemy e) {
		o.enemies.add(e);
		getState().addObject(e);
		e.init();
	}
	
	float timer = 0f;
	float nextTimer = 0f;
	boolean done;
	
	public void update(float delta, float time) {
		timer += Game.deltaTime;
		if (timer > nextTimer) {
			nextTimer = Math.abs(engine.BasicMath.randInt(1, 3)-(wave/50f));
			timer = 0;
			if (!queue.isEmpty()) {
				Enemy e = queue.get(0);
				getState().addObject(e);
				e.init();
				queue.remove(0);
			}

			//Check if all enemies destroyed
			if (enemies.isEmpty() && !done){
				endWave();
				done = true;
			}
		}
		
		//Wait a few seconds and start new wave.
		if (done) {
			endTimer -= Game.deltaTime;
			if (endTimer < 0) {
				
				endTimer = 10f;
				done = false;
				nextWave();
			}
		}
		
		//Update gui
		waveText.change("Wave " + wave,waveColor,false);
		boolean flash = enemies.size() == 1;
		if (enemies.size()>0)
			enemiesText.change("Enemies left " + enemies.size(), Color.RED, flash);
		else
			enemiesText.change(" ", Color.RED, flash);
	}
	
	public List<Enemy> enemies = new ArrayList<Enemy>();
	List<Enemy> queue = new ArrayList<Enemy>();
}
