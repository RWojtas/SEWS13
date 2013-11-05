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
	private int length;
	private int width;
	private int direction;

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
	
	/*
	 * Direction: Richtung der Person
	 * 0  - unten
	 * 1  - unten/links (schräg)
	 * 2  - links
	 * 3  - links/oben (schräg)
	 * 4  - oben
	 * 5  - oben/rechts (schräg)
	 * 6  - rechts
	 * 7  - rechts/unten (schräg)
	 */

	// Constructor
	public Human(char gender, int type, BufferedImage image, int x, int y, int length, int width, int direction) {
		this.length = length;
		this.width =  width;
		this.position = new Position(x, y, x+width, y, x, y+length, x+width, y+length);
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
	}
	
	public void moveObject(int x, int y) {
		position.setPosition(x, y, x+this.width, y, x, y+this.length, x+this.width, y+this.length);
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
		position.setPosition(x, y, x+this.width, y, x, y+this.length, x+this.width, y+this.length);
		// else
			// hier muss ne Formel für die Drehung hin, bei 45°, jemand nen Plan?
	}

	public void setTarget(int x, int y) {
		target.setPosition(x, y, x+this.width, y, x, y+this.length, x+this.width, y+this.length);
	}

	public double getFlirt() {
		return flirt;
	}

	public void setFlirt(double flirt) {
		this.flirt = flirt;
	}
	
	public void addFlirt(double flirt){
		this.flirt +=flirt;
	}
	
	public void removeFlirt(double flirt){
		this.flirt -=flirt;
	}

	public double getFun() {
		return fun;
	}

	public void setFun(double fun) {
		this.fun = fun;
	}
	
	public void addFun(double fun){
		this.fun +=fun;
	}
	
	public void removeFun(double fun){
		this.fun -=fun;
	}

	public double getAlcLevel() {
		return alcLevel;
	}

	public void setAlcLevel(double alcLevel) {
		this.alcLevel = alcLevel;
	}
	
	public void addAlcLevel(double alcLevel){
		this.alcLevel +=alcLevel;
	}
	
	public void removeAlcLevel(double alclevel){
		this.alclevel -=alclevel;
	}

	public double getUrine() {
		return urine;
	}

	public void setUrine(double urine) {
		this.urine = urine;
	}
	
	public void addUrine(double urine){
		this.urine +=urine;
	}
	
	public void removeUrine(double urine){
		this.urine -=urine;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}
	
	public void addEnergy(double energy){
		this.energy +=energy;
	}
	
	public void removeEnergy(double energy){
		this.energy -=energy;
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
			if (this.position != this.target) {
				if(x < this.target.getX0() && y < this.target.getY0()) {
					x++;
					y++;
					this.direction = 7;
				}
				else if(x > this.target.getX0() && y < this.target.getY0()){
					x--;
					y++;
					this.direction = 1;
				}
				else if( x < this.target.getX0() && y > this.target.getY0()) {
					x++;
					y--;
					this.direction = 5;
				}
				else if(x > this.target.getX0() && y > this.target.getY0()) {
					x--;
					y--;
					this.direction = 3;
				}
				else if(x > this.target.getX0()) {
					x--;
					this.direction = 2;
				}
				else if(x < this.target.getX0()) {
					x++;
					this.direction = 6;
				}
				else if(y < this.target.getY0()) {
					y++;
					this.direction = 0;
				}
				else if(y > this.target.getY0()) {
					y--;
					this.direction = 4;
				}
				// TO-DO: Wegfinde-Algorithmus
				/*int xORy = Functions.myRandom(0, 1);
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
				}*/
				position.setPosition(x, y, x+this.width, y, x, y+this.length, x+this.width, y+this.length);
			}
		}
	}
	// END: getNextPos()

	// END: AKTIVITÄTSMETHODEN

}
