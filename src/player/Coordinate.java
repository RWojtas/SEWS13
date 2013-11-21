package player;
import java.lang.Math;

/**
 * @author Raffael & Sebastian
 */

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getXCoordinate() {
		return x;
	}
	
	public int getYCoordinate() {
		return y;
	}
	
	public void setPosition(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public double distance(Coordinate c) {
		return Math.sqrt(Math.pow((this.x-c.x),2)+Math.pow((this.y-c.y),2));
	}
}
