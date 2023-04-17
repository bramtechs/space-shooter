package com.brambasiel.spaceshooter;

import com.brambasiel.spaceshooter.states.State;
import com.brambasiel.spaceshooter.states.menu.MainMenuState;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Canvas {

    private static final long serialVersionUID = 7964798631767742455L;

    public static final int TARGET_FPS = 60;
    public static final float deltaTime = 1f / TARGET_FPS; //This is horrible

    private boolean shouldBeRunning;
    private boolean performantMode;
    private boolean debugMode;
    private State state;

    private float scale;
    //private Graphics2D g;

    public Game(float scale) {
        this.scale = scale;

        //User input
        Input input = new Input();
        addMouseListener(input);
        addKeyListener(input);

        state = new MainMenuState();
        shouldBeRunning = true;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                render();
                if (!shouldBeRunning) {
                    timer.cancel();
                }
            }
        }, 0L, (long) (1000 / TARGET_FPS));
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics2D graph = (Graphics2D) bs.getDrawGraphics();
        graph.setColor(Color.BLACK);
        graph.fillRect(0, 0, WIDTH, HEIGHT);
        graph.scale(scale, scale);
        ////////////////////////
        state.draw(g);
        ///////////////////////
        graph.dispose();
        Toolkit.getDefaultToolkit().sync();
        bs.show();
    }

    private Dimension getScaledScreenSize() {
        return new Dimension((int) (WIDTH * scale), (int) (HEIGHT * scale));
    }

    public static Point getCenter() {
        return new Point(WIDTH / 2, HEIGHT / 2);
    }

    public void quit() {
        shouldBeRunning = false;
    }
}