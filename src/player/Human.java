package player;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class Human extends JLabel{
	// Attribute
	protected Position position;
	protected Position target;
	protected double flirt;
	protected double fun;
	protected double alcLevel;
	protected double urine;
	protected double energy;
	protected char gender;
	protected int type;
	protected int activity;
	protected int activityTimer;
	protected ImageIcon image;
	private int size;
	private int direction;

	/*
	 * Aktivit�tentabelle: 
	 * 0  - offen 
	 * 1  - gehen 
	 * 2  - tanzen 
	 * 3  - trinken 
	 * 4  - sitzen
	 * 5  - urinieren 
	 * 6  - reden 
	 * 7  - flirten 
	 * 8  - Musik w�nschen 
	 * 9  - ausruhen 
	 * 10 - im Koma liegen
	 */
	
	/*
	 * Direction: Richtung der Person
	 * 0  - unten
	 * 1  - unten/links (schr�g)
	 * 2  - links
	 * 3  - links/oben (schr�g)
	 * 4  - oben
	 * 5  - oben/rechts (schr�g)
	 * 6  - rechts
	 * 7  - rechts/unten (schr�g)
	 */

	// Constructor
	public Human(char gender, int type, BufferedImage image, int x, int y, int size, int direction) {
		this.size = size;
		this.position = new Position(x, y, x+size, y, x, y+size, x+size, y+size);
		this.target = new Position(0, 0, 0, 0, 0, 0, 0, 0);
		this.flirt = 0.5;
		this.fun = 0.5;
		this.alcLevel = 0;
		this.urine = 0.5;
		this.energy = 1;
		this.gender = gender;
		this.type = type;
		this.activity = 0;
		this.direction = direction;
		
		setIcon(new ImageIcon(image.getSubimage(0,0,image.getWidth(),image.getHeight())));
		setBounds(x,y,image.getWidth(),image.getHeight());
		setOpaque(false);
	}
	
	public void moveObject(int x, int y) {
		position.setPosition(x, y, x+this.size, y, x, y+this.size, x+this.size, y+this.size);
		setLocation(position.getX0(),position.getY0());
	}

	// START: GETTER + SETTER
	public char getGender(){
		return this.gender;
	}
	
	public int getType(){
		return this.type;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public int getXPosition() {
		return position.getX0();
	}

	public int getYPosition() {
		return position.getY0();
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(int x, int y, int direction) {
		if(direction == 0 || direction == 2 || direction == 4 || direction == 6)
			position.setPosition(x, y, x+this.size, y, x, y+this.size, x+this.size, y+this.size);
		// else
			// hier muss ne Formel f�r die Drehung hin, bei 45�, jemand nen Plan?
	}

	public void setTarget(int x, int y) {
		target.setPosition(x, y, x+this.size, y, x, y+this.size, x+this.size, y+this.size);
	}

	public double getFlirt() {
		return flirt;
	}

	public void setFlirt(double flirt) {
		this.flirt = flirt;
	}

	public double getFun() {
		return fun;
	}

	public void setFun(double fun) {
		this.fun = fun;
	}

	public double getAlcLevel() {
		return alcLevel;
	}

	public void setAlcLevel(double alcLevel) {
		this.alcLevel = alcLevel;
	}

	public double getUrine() {
		return urine;
	}

	public void setUrine(double urine) {
		this.urine = urine;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
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

	// START: AKTIVIT�TSMETHODEN
	public void dance() {
		this.setActivity(2);
		this.setActivityTimer(20);
	}

	public void drink() {
		this.setActivity(3);
		this.setActivityTimer(20);
	}

	public void urinate() {
		this.setActivity(5);
		this.setActivityTimer(20);
	}

	// START: getNextPos() - inkl. Wegfindealgorithmus
	public void getNextPosition() {
		int x = this.getXPosition();
		int y = this.getYPosition();

		if (this.getActivity() == 1) {
			if (this.position == this.target) {
				// TO-DO: Wegfinde-Algorithmus
				int xORy = Functions.myRandom(0, 1);
				switch (xORy) {
				case 0:
					if (x < target.getX0()) {
						x++;
					} else if (x > target.getX0()) {
						x--;
					}
					break;
				case 1:
					if (y < target.getY0()) {
						y++;
					} else if (y > target.getY0()) {
						y--;
					}
					break;
				}
				position.setPosition(x, y, x+this.size, y, x, y+this.size, x+this.size, y+this.size);
			}
		}
	}
	// END: getNextPos()

	// END: AKTIVIT�TSMETHODEN

}
