package objects;
import java.lang.Math;

public class Position {
	private int x;
	private int y;
	public Position(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getXPosition() {
		return x;
	}
	public int getYPosition() {
		return y;
	}
	public void setPosition(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public double distance(Position p) {
		return Math.sqrt((this.x-p.x)^2+(this.y-p.y)^2);
	}
}
