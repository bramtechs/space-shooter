package com.brambasiel.spaceshooter;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class GameWindow extends JFrame implements WindowListener {

    private static Logger logger = Logger.getLogger(GameWindow.class.getName());
    private static final long serialVersionUID = -8325159550353636036L;
    public static final int WIDTH = 360;
    public static final int HEIGHT = 640;
    private Game game;

    public GameWindow(float scale) {
        super("Java Game!");
        Dimension size = new Dimension(w, h);
        //gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setAlwaysOnTop(true);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setResizable(false);
        addWindowListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        System.out.println("Window created");

        // create the game
        game = new Game(scale);
        add(game);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        logger.info("Closed window");
        game.quit();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    public void setWindowed() {

    }

    public void setFullscreen() {

    }

    public Point getCenter() {
        return new Point(WIDTH / 2, HEIGHT / 2);
    }

    public static void main(String[] args) {
        float scale = 1.7f;
        if (args.length > 0) {
            scale = Float.parseFloat(args[0]);
        }
        GameWindow win = new GameWindow(scale);
    }
}
