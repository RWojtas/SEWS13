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

	/*
	 * Aktivitätentabelle: 
	 * 0  - offen 
	 * 1  - gehen 
	 * 2  - tanzen 
	 * 3  - trinken 
	 * 4  - sitzen
	 * 5  - urinieren 
	 * 6  - reden 
	 * 7  - flirten 
	 * 8  - Musik wünschen 
	 * 9  - ausruhen 
	 * 10 - im Koma liegen
	 */

	// Constructor
	public Human(char gender, int type, BufferedImage image, int x, int y) {
		this.position = new Position(x, y);
		this.target = new Position(0, 0);
		this.flirt = 0.5;
		this.fun = 0.5;
		this.alcLevel = 0;
		this.urine = 0.5;
		this.energy = 1;
		this.gender = gender;
		this.type = type;
		this.activity = 0;
		
		setIcon(new ImageIcon(image.getSubimage(0,0,image.getWidth(),image.getHeight())));
		setBounds(x,y,image.getWidth(),image.getHeight());
	}
	
	public void moveObject(int x, int y) {
		position.setPosition(x, y);
		setLocation(position.getXPosition(),position.getYPosition());
	}

	// START: GETTER + SETTER
	public char getGender(){
		return this.gender;
	}
	
	public int getType(){
		return this.type;
	}
	
	
	public int getXPosition() {
		return position.getXPosition();
	}

	public int getYPosition() {
		return position.getYPosition();
	}

	public void setPosition(int x, int y) {
		position.setPosition(x, y);
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

	// START: AKTIVITÄTSMETHODEN
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
				position.setPosition(x, y);
			}
		}
	}
	// END: getNextPos()

	// END: AKTIVITÄTSMETHODEN

}
