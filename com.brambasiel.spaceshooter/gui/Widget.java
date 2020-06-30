package gui;

public abstract class Widget {
	public int x;
	public int y;
	protected int w,h;
	
	public Widget(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void draw();
}
