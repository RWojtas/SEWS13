package player;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class Mensch extends JLabel{
	// Attribute
	protected Position position;
	protected Position target;
	protected double flirt;
	protected double spaﬂ;
	protected double alkpegel;
	protected double blase;
	protected double energie;
	protected char geschlecht;
	protected int type;
	protected int activity;
	protected int activityTimer;
	protected ImageIcon image;

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
	public Mensch(char geschlecht, int type, BufferedImage image, int x, int y) {
		this.position = new Position(x, y);
		this.target = new Position(0, 0);
		this.flirt = 0.5;
		this.spaﬂ = 0.5;
		this.alkpegel = 0;
		this.blase = 0.5;
		this.energie = 1;
		this.geschlecht = geschlecht;
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
	public char getGeschlecht(){
		return this.geschlecht;
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
		int x = this.getXPosition();
		int y = this.getYPosition();

		if (this.getActivity() == 1) {
			if (this.position == this.target) {
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
				position.setPosition(x, y);
			}
		}
	}
	// END: getNextPos()

	// END: AKTIVITƒTSMETHODEN

}
