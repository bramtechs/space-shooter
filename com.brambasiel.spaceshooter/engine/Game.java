package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import input.Input;
import objects.SceneManager;
import states.State;

public class Game extends Canvas{
	
	private static final long serialVersionUID = 7964798631767742455L;
	
	public static final int TARGET_FPS = 60;
	public static final float deltaTime = 1f/TARGET_FPS; //This is horrible
	public static final int w = 360;
	public static final int h = 640;
	public static Graphics2D g = null;

	public static SpriteLoader loader;
	
	public static boolean performant = false;
	public static boolean debug = false;
	
	public static float scale = 1.7f;
	
	private Timer timer;
	private State state;
	
	//private Graphics2D g;
	
	public static void main(String[] args) {
		if (args.length > 0) {
			scale = Float.parseFloat(args[0]);
		}
		new BasicMath();
		new Game();
	}

	public Game() {
		BasicMath.setup();
		new Window(w, h, this);
		
		//User input
		Input input = new Input();
		addMouseListener(input);
		addKeyListener(input);
		
		loader = new SpriteLoader();
		
		SceneManager.loadMenu();
		
	    timer = new Timer();	
	    timer.schedule(new GameLoop(), 0L, (long)(1000 / TARGET_FPS));
	}
	
	public static boolean running = true;
	class GameLoop extends TimerTask{
	    public void run() //this becomes the loop
	    {
	        tick();
	        render();
	        if (!running)
	        {
	            timer.cancel();
	        }
	    }
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		g.scale(scale, scale);
		////////////////////////
		state.draw(g);
		///////////////////////
		g.dispose();
		Toolkit.getDefaultToolkit().sync();
		bs.show();
	}

	private void tick() {
		SceneManager.update();
	}

	public static void print(String p) {
		System.out.println(p);
	}
	
	private static Dimension getScaledScreenSize() {
		return new Dimension((int) (w * scale), (int) (h * scale));
	}

	public static Point getCenter() {
		return new Point(w/2,h/2);
	}
}