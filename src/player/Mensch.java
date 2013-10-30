package player;

public abstract class Mensch {
	// Attribute
	private Position pos;
	private Position target;
	private double flirt;
	private double spaﬂ;
	private double alkpegel;
	private double blase;
	private double energie;
	private char geschlecht;
	private int type;
	private int activity;
	private int activityTimer;

	/*
	 * Aktivit‰tentabelle: 
	 * 0  - offen 
	 * 1  - gehen 
	 * 2  - tanzen 
	 * 3  - trinken 
	 * 4  - sitzen
	 * 5  - urinieren 
	 * 6  - reden 
	 * 7  - flirten 
	 * 8  - Musik w¸nschen 
	 * 9  - ausruhen 
	 * 10 - im Koma liegen
	 */

	// Constructor
	public Mensch(char geschlecht, int type) {
		this.pos = new Position(0, 0);
		this.target = new Position(0, 0);
		this.flirt = 0.5;
		this.spaﬂ = 0.5;
		this.alkpegel = 0;
		this.blase = 0.5;
		this.energie = 1;
		this.geschlecht = geschlecht;
		this.type = type;
		this.activity = 0;
	}

	// START: GETTER + SETTER
	public char getGeschlecht(){
		return this.geschlecht;
	}
	
	public int getType(){
		return this.type;
	}
	
	
	public int getXPos() {
		return pos.getXPosition();
	}

	public int getYPos() {
		return pos.getYPosition();
	}

	public void setPos(int x, int y) {
		pos.setPosition(x, y);
	}

	public void setTarget(int x, int y) {
		target.setPosition(x, y);
	}

	public double getFlirt() {
		return flirt;
	}

	public void setFlirt(double flirt) {
		this.flirt = flirt;
	}

	public double getSpaﬂ() {
		return spaﬂ;
	}

	public void setSpaﬂ(double spaﬂ) {
		this.spaﬂ = spaﬂ;
	}

	public double getAlkpegel() {
		return alkpegel;
	}

	public void setAlkpegel(double alkpegel) {
		this.alkpegel = alkpegel;
	}

	public double getBlase() {
		return blase;
	}

	public void setBlase(double blase) {
		this.blase = blase;
	}

	public double getEnergie() {
		return energie;
	}

	public void setEnergie(double energie) {
		this.energie = energie;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public void setActivityTimer(int timer) {
		this.activityTimer = timer;
	}

	public int getActivityTimer() {
		return this.activityTimer;
	}

	public void decActivityTimer() {
		this.activityTimer--;
	}

	public void incActivityTimer() {
		this.activityTimer++;
	}

	// END: GETTER + SETTER

	// START: AKTIVITƒTSMETHODEN
	public void tanzen() {
		this.setActivity(2);
		this.setActivityTimer(20);
	}

	public void trinken() {
		this.setActivity(3);
		this.setActivityTimer(20);
	}

	public void urinieren() {
		this.setActivity(5);
		this.setActivityTimer(20);
	}

	// START: getNextPos() - inkl. Wegfindealgorithmus
	public void getNextPos() {
		int x = this.getXPos();
		int y = this.getYPos();

		if (this.getActivity() == 1) {
			if (this.pos == this.target) {
				// TO-DO: Wegfinde-Algorithmus
				int xORy = Funktionen.myRandom(0, 1);
				switch (xORy) {
				case 0:
					if (x < target.getXPosition()) {
						x++;
					} else if (x > target.getXPosition()) {
						x--;
					}
					break;
				case 1:
					if (y < target.getYPosition()) {
						y++;
					} else if (y > target.getYPosition()) {
						y--;
					}
					break;
				}
				pos.setPosition(x, y);
			}
		}
	}
	// END: getNextPos()

	// END: AKTIVITƒTSMETHODEN

}
