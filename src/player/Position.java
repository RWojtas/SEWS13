package player;

public class Position {
	private Coordinate lo; //Position links oben
	private Coordinate ro; //Position rechts oben
	private Coordinate lu; //Position links unten
	private Coordinate ru; //Position rechts unten
	
	//x0,y0 = lo
	//x1,y1 = ro
	//x2,y2 = lu
	//x3,y3 = ru
	public Position(int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3) {
		this.lo = new Coordinate(x0,y0);
		this.ro = new Coordinate(x1,y1);
		this.lu = new Coordinate(x2,y2);
		this.ru = new Coordinate(x3,y3);
	}

	public Coordinate getLo() {
		return lo;
	}
	
	public Coordinate getRo() {
		return ro;
	}

	public Coordinate getLu() {
		return lu;
	}

	public Coordinate getRu() {
		return ru;
	}
	
	public void setPosition(Coordinate lo, Coordinate ro, Coordinate lu, Coordinate ru) {
		this.lo = lo;
		this.ro = ro;
		this.lu = lu;
		this.ru = ru;
	}
	
	public void setPosition(int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3) {
		this.lo.setPosition(x0, y0);
		this.ro.setPosition(x1, y1);
		this.lu.setPosition(x2, y2);
		this.ru.setPosition(x3, y3);
	}
	
	public int getX0() {
		return this.lo.getXCoordinate();
	}
	
	public int getY0() {
		return this.lo.getYCoordinate();
	}
	
	public int getX1() {
		return this.ro.getXCoordinate();
	}
	
	public int getY1() {
		return this.ro.getYCoordinate();
	}
	
	public int getX2() {
		return this.lu.getXCoordinate();
	}
	
	public int getY2() {
		return this.lu.getYCoordinate();
	}
	
	public int getX3() {
		return this.ru.getXCoordinate();
	}
	
	public int getY3() {
		return this.ru.getYCoordinate();
	}
	
}
