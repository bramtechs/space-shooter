package input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import engine.Game;

public class Input implements MouseListener, KeyListener {

	static Map<Character,Boolean> keys = new HashMap<Character,Boolean>();
	static Point lastPoint = new Point(0,0);
	
	
	//KEYBOARD

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.put(e.getKeyChar(),true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.put(e.getKeyChar(),false);
	}
	
	public static boolean IsKeyPressed(char c) {
		if (keys.containsKey(c))
			return keys.get(c);
		else
			return false;
	}
	
	//MOUSE
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pressed = true;
		lastPoint = new Point((int)(e.getX()/Game.scale),(int)(e.getY()/Game.scale));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	static boolean pressed = false;
	
	public static boolean isMouseDown() {
		return pressed;
	}
	
	public static Point getMousePosition() {
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		return new Point(x/(int)Game.scale,y/(int)Game.scale);
	}
}
