package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

public class BasicMath {
	static Random r;
	
	public static Dimension toDimension(int x, int y) {
		return new Dimension(x,y);
	}
	
	static void setup() {
		r = new Random();
	}
	
	public static int randInt(int max) {
		return r.nextInt(max);
	}
	public static int randInt(int min, int max) {
		return (int)lerp((float)min,(float)max,r.nextFloat());
	}
	
	public static float randFloat(float max) {
		return r.nextFloat()*max;
	}
	public static float randFloat(float min, float max) {
		return lerp(min,max,r.nextFloat());
	}
	
	public static float lerp(float point1, float point2, float alpha)
	{
	    return point1 + alpha * (point2 - point1);
	}

	public static float getAngle(int x, int y, int xx, int yy) {
	    float angle = (float) java.lang.Math.toDegrees(java.lang.Math.atan2(yy - y, xx - x));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	public static boolean pointInRect(Point p,int x, int y, int w, int h) {
		if (p.x > x && p.x < x+w && p.y > y && p.y < y+h) {
			return true;
		}
		return false;
	}
	
	public static boolean rectInRect(Point l1, Point r1, Point l2, Point r2) 
	{ 
	    // If one rectangle is on left side of other 
	    if (l1.x > r2.x || l2.x > r1.x) 
	        return false;
	  
	    // If one rectangle is above other 
	    if (l1.y < r2.y || l2.y < r1.y) 
	        return false; 
	  
	    return true; 
	} 
	
	public static Color blend(Color x, Color y, float blending) {

		float inverse_blending = 1 - blending;
	
		float red =   x.getRed()   * blending   +   y.getRed()   * inverse_blending;
		float green = x.getGreen() * blending   +   y.getGreen() * inverse_blending;
		float blue =  x.getBlue()  * blending   +   y.getBlue()  * inverse_blending;
	
		//note that if i pass float values they have to be in the range of 0.0-1.0 
		//and not in 0-255 like the ones i get returned by the getters.
		return new Color (clamp(red / 255f,0,1),clamp(green / 255f,0,1),clamp(blue / 255f,0,1));
	}

	public static float clamp(float v, float min, float max) {
		if (v < min) {
			return min;
		}
		if (v > max) {
			return max;
		}
		return v;
	}
}
