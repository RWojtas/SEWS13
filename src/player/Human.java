package player;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import main.GameLogic;


public abstract class Human extends JLabel{
	// Attribute
	public int graphicState;
	protected Position position;		// Aktuelle Position des Human-Objektes
	protected Position target;			// Ziel-Position des Human-Objektes
	protected double flirt;				// Spielfaktoren... entsprechend der Variablennamen
	protected double fun;
	protected double alcLevel;
	protected double urine;
	protected double energy;
	protected char gender;				// Geschlecht -> F�r gewisse Logik wichtig (Jungs flirten keine Jungs an)
	protected String type;					// Typ -> wichtig f�r die Grafiken
	protected int activity;				// Aktivit�t, die gerade ausgef�hrt wird (siehe unten: Aktivit�tstabelle)
	protected int activityTimer;		// Wie lang eine Aktivit�t (noch) dauert
	protected ImageIcon image;
	private int height;					// H�he des Spielers (Sicht von oben)
	private int width;					// Breite des Spielers (Sicht von oben)
	private int direction;				// Richtung in die der Spieler gerade schaut -> wichtig f�r weitere Bewegung

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
	public Human(String type, char gender, BufferedImage image, int x, int y, int height, int width, int direction) {
		this.height = height;
		this.width =  width;
		this.position = new Position(x, y, x+width, y, x, y+height, x+width, y+height);
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
		
		graphicState = 0;
		setIcon(new ImageIcon(image.getSubimage(0,0,image.getWidth(),image.getHeight())));
		setBounds(x,y,image.getWidth(),image.getHeight());
		setOpaque(false);
	}
	
	public void moveObject(int x, int y) {
		position.setPosition(x, y, x+this.width, y, x, y+this.height, x+this.width, y+this.height);
		setLocation(position.getX0(),position.getY0());
	}
	
	public void setImage(BufferedImage image) {
		setIcon(new ImageIcon(image.getSubimage(0,0,image.getWidth(),image.getHeight())));
		setBounds(position.getX0(),position.getY0(),image.getWidth(),image.getHeight());
	}

	// START: GETTER + SETTER
	public char getGender(){
		return this.gender;
	}
	
	public String getType(){
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
		position.setPosition(x, y, x+this.width, y, x, y+this.height, x+this.width, y+this.height);
	}

	public void setTarget(int x, int y) {
		target.setPosition(x, y, x+this.width, y, x, y+this.height, x+this.width, y+this.height);
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
		this.alcLevel -=alclevel;
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
	
	public void setDirection(int dir) {
		this.direction = dir;
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
	
	public boolean checkFreePosition(int x, int y) {		// �berpr�ft ob eine gewisse Koordinate besetzt ist oder nicht. 
		GameLogic gl = GameLogic.getInstance();				// Gibt eine entsprechende Antwort in From von "false" oder "true".
		return gl.checkFreePosition(new Coordinate(x,y), new Coordinate(x+width, y), new Coordinate(x,y+height), new Coordinate(x+width, y+height));
	}
	
	public Coordinate ausDirzuCoo(int dir) { 			// Diese Methode erstellt aus der Richtung eines Menschen
		int x = this.getPosition().getX0();				// die n�chste Position, in die er hingehen w�rde. 
		int y = this.getPosition().getY0();				// Diese Koordinate wird dann zur�ckgegeben.
		switch(dir) {
		case 0: 
			++y;									
			break;
		case 1:											
			++y;
			x--;
			break;
		case 2:
			x--;
			break;
		case 3:
			x--;
			y--;
			break;
		case 4:
			y++;
			break;
		case 5:
			x++;
			y--;
			break;
		case 6:
			x++;
			break;
		case 7:
			x++;
			y++;
			break;
		}
		return new Coordinate(x,y);
	}
	
	
	public boolean check(int dir, int cnt) {
		cnt++;
		dir = dir%8;
		Coordinate Coo = ausDirzuCoo(dir);										// Diese Methode �berpr�ft anhand der Richtung, die �bergeben wird, die n�chste Koordinate und schaut,  
		if(checkFreePosition(Coo.getXCoordinate(),Coo.getYCoordinate())){		// ob diese frei ist. Falls ja, wird die Richtung des Menschen entsprechend gesetzt.
			this.direction = dir;												// Falls diese Koordinate nicht frei ist, ruft sich die Methode selber erneut auf und pr�ft die n�chste Richtung
		}																		// Sind alle 8 Richtungen einmal durchgepr�ft, gibt die Methode false zur�ck.
		else {																	// Der Integer cnt z�hlt sich bei jedem Durchlauf um einen hoch und schaut somit, ob alle Richtungen gepr�ft worden sind.
			if(cnt <= 8) {														
				if(!(check(dir+1, cnt))) {
					return false;
				}
			}
			else 
				return false;
		}
		return true;	
	}

	// START: getNextPos() - inkl. Wegfindealgorithmus
	public void stepNextPosition() {													//Diese Methode setzt die n�chste Position des Menschen 
		int x = this.getXPosition();												//mit Hilfe der weiter oben erkl�rten Methoden.
		int y = this.getYPosition();												//Der Fall, dass sich der Mensch nicht bewegt, ist abgefangen.
		boolean rcheck = false;
		Coordinate newPos = new Coordinate(x, y);
		
		if (this.getActivity() == 1) {												
			if (this.position != this.target) {										
				if(x < this.target.getX0() && y < this.target.getY0()) {			//Wenn die aktuelle x Position und y Position kleiner als die des Ziel sind
						rcheck = this.check(7,0);									//wird die Methode check(7,0) aufgerufen. Die 7 steht f�r die Richtung unten rechts. 
				}																	//Alle Richtungen mit entsprechenden Werten (0-7) sind am Anfang des Dokuments aufgelistet.
				else if(x > this.target.getX0() && y < this.target.getY0()){
						rcheck = this.check(1,0);
				}
				else if( x < this.target.getX0() && y > this.target.getY0()) {
						rcheck = this.check(5,0);
				}
				else if(x > this.target.getX0() && y > this.target.getY0()) {
						rcheck = this.check(3,0);
				}
				else if(x > this.target.getX0()) {
						rcheck = this.check(2,0);
				}
				else if(x < this.target.getX0()) {
						rcheck = this.check(6,0);
				}
				else if(y < this.target.getY0()) {
						rcheck = this.check(0,0);
				}
				else if(y > this.target.getY0()) {
						rcheck = this.check(4,0);
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
				if(rcheck) {
					newPos = ausDirzuCoo(this.direction);
				}
				moveObject(newPos.getXCoordinate(), newPos.getYCoordinate()); // Die neue Position wird explizit gesetzt.
			}
		}
	}
	// END: getNextPos()

	// END: AKTIVIT�TSMETHODEN

}
