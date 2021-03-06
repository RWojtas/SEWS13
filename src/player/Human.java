package player;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import main.BufferedImageLoader;
import main.GameLogic;

/**
 * @author Raffael & Sebastian
 * @grafischeUmsetzung Philip & Nicolas
 * 
 * alle Mensch...
 */

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
	protected ImageIcon images[] = new ImageIcon[8];
	protected int height;					// H�he des Spielers (Sicht von oben)
	protected int width;					// Breite des Spielers (Sicht von oben)
	protected int direction;				// Richtung in die der Spieler gerade schaut -> wichtig f�r weitere Bewegung
	protected int new_direction;
	protected int old_direction;
	protected int wegfinde_cnt;
	protected boolean has_moved = true;
	protected int wegfindetrouble = 0;
	protected int troublecnt;

	/*
	 * Aktivit�tentabelle: 
	 * -1 - stehen (beim Start nötig)
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
	public Human(String type, char gender, BufferedImage image, int x, int y, int direction) {
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
		this.height = BufferedImageLoader.scaleToScreenX(60,false);
		this.width =  BufferedImageLoader.scaleToScreenY(60,false);
		
		this.position = new Position(x, y, x+width, y, x, y+height, x+width, y+height);
		
		for(int i=0;i<8;i++) {
			images[i] = new ImageIcon(image.getSubimage(0,i*width,width,height));
		};
		
		setIcon(images[0]);
		setBounds(x,y,width,height);
		setOpaque(false);
	}
	
	public void resetHuman(String type, char gender, BufferedImage image, int x, int y, int direction) {
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
		this.height = BufferedImageLoader.scaleToScreenX(60,false);
		this.width =  BufferedImageLoader.scaleToScreenY(60,false);
		
		this.position = new Position(x, y, x+width, y, x, y+height, x+width, y+height);
		
		for(int i=0;i<8;i++) {
			images[i] = new ImageIcon(image.getSubimage(0,i*width,width,height));
		};
		
		setIcon(images[0]);
		setBounds(x,y,width,height);
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
	
	public Position getTarget() {
		return target;
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
		setIcon(images[dir]);
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
	
	//public long t2;
	public boolean checkFreePosition(int x, int y) {		// �berpr�ft ob eine gewisse Koordinate besetzt ist oder nicht. 
		GameLogic gl = GameLogic.getInstance();				// Gibt eine entsprechende Antwort in Form von "false" oder "true".
		//if(type.equals("Player")) {
			//t2 = System.nanoTime();
			//boolean b = gl.checkFreePosition(this.hashCode(), new Coordinate(x,y), new Coordinate(x+width, y), new Coordinate(x,y+height), new Coordinate(x+width, y+height));
			//System.out.println("checkFreePosition Dauer:\nZeit in Nanosekunden: "+(System.nanoTime()-t2));
			//return b;
		//} else {
			return gl.checkFreePosition(this.hashCode(), new Coordinate(x,y), new Coordinate(x+width, y), new Coordinate(x,y+height), new Coordinate(x+width, y+height));
		//}
		
	}
	
	public Coordinate ausDirzuCoo(int dir) { 			// Diese Methode erstellt aus der Richtung eines Menschen
		int x = this.getPosition().getX0();				// die n�chste Position, in die er hingehen w�rde. 
		int y = this.getPosition().getY0();				// Diese wird dann als Koordinate zur�ckgegeben.
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
			y--;
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
		if((dir<0)) dir=7;
		if((dir>7)) dir=0;
		Coordinate Coo = ausDirzuCoo(dir);										// Diese Methode �berpr�ft anhand der Richtung, die �bergeben wird, die n�chste Koordinate und schaut,  
		if(checkFreePosition(Coo.getXCoordinate(),Coo.getYCoordinate())){		// ob diese frei ist. Falls ja, wird die Richtung des Menschen entsprechend gesetzt.
			setDirection(dir);												// Falls diese Koordinate nicht frei ist, ruft sich die Methode selber erneut auf und pr�ft die n�chste Richtung
		}																		// Sind alle 8 Richtungen einmal durchgepr�ft, gibt die Methode false zur�ck.
		else {																	// Der Integer cnt z�hlt sich bei jedem Durchlauf um einen hoch und schaut somit, ob alle Richtungen gepr�ft worden sind.
			if (cnt <= 8) {
				if ((this.target.getY0() > this.position.getY0())
						&& (this.target.getX0() < this.position.getX0())) {
					if (!check(--dir, cnt)) {
						return false;
					}
				} else {
					if (!check(++dir, cnt)) {
						return false;
					}
				}
				/*
				 * if((dir == 4) && !check(3,7) && !check(2,7) && (this.position.getY0()-1 == this.target.getY0())) {
				 * }
				 * 
				 * else if((this.target.getY0() < this.position.getY0()) &&
				 * (this.target.getX0() < this.position.getX0())) {
				 * if(!check(++dir,cnt)) { return false; } } else
				 * if((this.target.getY0() > this.position.getY0()) &&
				 * (this.target.getX0() > this.position.getX0())) {
				 * if(!check(++dir,cnt)) { return false; } } else
				 * if((this.target.getY0() < this.position.getY0()) &&
				 * (this.target.getX0() > this.position.getX0())) {
				 * if(!check(++dir,cnt)) { return false; } } else
				 * if(this.target.getY0() == this.position.getY0())
				 * if(!check(++dir,cnt)) { return false; } } else
				 * if(this.target.getX0() == this.position.getX0()) {
				 * if(!check(--dir,cnt)) { return false; } }
				 */
			}
		}
		return true;
	}
	
	/*
	// START: stepNextPos() - inkl. Wegfindealgorithmus
		public void stepNextPosition() { //Diese Methode setzt die nï¿½chste Position des Menschen
			// System.out.println("stepNextPosition()");
			int x = this.getXPosition();												//mit Hilfe der weiter oben erklï¿½rten Methoden.
			int y = this.getYPosition();												//Der Fall, dass sich der Mensch nicht bewegt, ist abgefangen.
			boolean rcheck = false;
			Coordinate newPos = new Coordinate(x, y);
			
			if (this.getActivity() != 0 && this.getActivity() != -1 ) {												
				if (this.position != this.target) {
					if(x < this.target.getX0() && y < this.target.getY0()) {			//Wenn die aktuelle x Position und y Position kleiner als die des Ziel sind
							rcheck = this.check(7,0);									//wird die Methode check(7,0) aufgerufen. Die 7 steht fï¿½r die Richtung unten rechts. 
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
					if((this.old_direction==4) && (this.direction==0)) {
						wegfindetrouble = true;
					}
					if(wegfindetrouble == true) {
						rcheck=false;
						Coordinate Coo = ausDirzuCoo(2);										// Diese Methode ï¿½berprï¿½ft anhand der Richtung, die ï¿½bergeben wird, die nï¿½chste Koordinate und schaut,  
						if(!(checkFreePosition(Coo.getXCoordinate(),Coo.getYCoordinate()))) {		// ob diese frei ist. Falls ja, wird die Richtung des Menschen entsprechend gesetzt.
							Coo = ausDirzuCoo(4);										// Diese Methode ï¿½berprï¿½ft anhand der Richtung, die ï¿½bergeben wird, die nï¿½chste Koordinate und schaut,  
							if(checkFreePosition(Coo.getXCoordinate(),Coo.getYCoordinate())) {
								setDirection(4);											// Falls diese Koordinate nicht frei ist, ruft sich die Methode selber erneut auf und prï¿½ft die nï¿½chste Richtung
								newPos = ausDirzuCoo(this.direction);
							}
						} else {
							setDirection(2);
							newPos = ausDirzuCoo(this.direction);
							Coo = ausDirzuCoo(1);										// Diese Methode ï¿½berprï¿½ft anhand der Richtung, die ï¿½bergeben wird, die nï¿½chste Koordinate und schaut,  
							if(checkFreePosition(Coo.getXCoordinate(),Coo.getYCoordinate())) {
								wegfindetrouble=false;
							}
						}
					}
					
					if(rcheck) {
						newPos = ausDirzuCoo(this.direction);
					}
					this.old_direction = this.direction;
					moveObject(newPos.getXCoordinate(), newPos.getYCoordinate()); // Die neue Position wird explizit gesetzt.
					//System.out.println("Aktuell: x:"+x+" y:"+y+" Neu: x:"+newPos.getXCoordinate()+" y:"+newPos.getYCoordinate()+" Target: x:"+this.target.getX0()+" y:"+this.target.getY0()+ " " +this.direction);
				}
			}
		}
		// END: getNextPos()
		*/

	/* Neuer Wegfindealgo - verbessert die Perfomance, da nur maximal 2 Richtungen pro Durchgang überprüft werden 
	 * 					  - allerdings werden die Personen dadurch logischerweise langsamer, wenn ein alternativer Weg genommen wird
	 * 					  - ist ein Kompromiss*/
	// START: stepNextPos() - inkl. Wegfindealgorithmus
	public void stepNextPosition() { //Diese Methode setzt die n�chste Position des Menschen
		/* Debugausgaben
		 * System.out.println("##########");
		 * System.out.println("# Start: stepNextPosition()");
		 * System.out.println("# Spieler: "+this.getType());
		 */
		int x = this.getXPosition();												//mit Hilfe der weiter oben erkl�rten Methoden.
		int y = this.getYPosition();												//Der Fall, dass sich der Mensch nicht bewegt, ist abgefangen.
		Coordinate newPos = new Coordinate(x, y);
		
		if (this.getActivity() != 0 && this.getActivity() != -1 ) {
			/* Debugausgabe
				System.out.println("# Aktivität != 0 && != -1 -> Laufbereit");
				System.out.println("# Aktuelle Position: "+this.position.getX0()+","+this.position.getY0()+","+this.position.getX1()+","+this.position.getY1()+","+this.position.getX2()+","+this.position.getY2()+","+this.position.getX3()+","+this.position.getY3());
				System.out.println("#              Ziel: "+this.target.getX0()+","+this.target.getY0()+","+this.target.getX1()+","+this.target.getY1()+","+this.target.getX2()+","+this.target.getY2()+","+this.target.getX3()+","+this.target.getY3());
			*/
			if (this.position.getX0() != this.target.getX0() || this.position.getY0() != this.target.getY0()) {
				/* Debugausgabe
					System.out.println("# aktuelle Position != Target -> LAUFEN!");
					System.out.println("# wegfinde_cnt: "+this.wegfinde_cnt);
				*/
				if(this.wegfindetrouble == 0) { // in manchen Fällen tritt ein "Trouble" ein, dass die Positionen hin und her wechseln (dann zittert der Mensch schön), ist dieser Fall eingetreten, wird der Teil übersprungen
					if(this.has_moved) {
						this.has_moved = false;
						this.wegfinde_cnt = 0;
						if(x < this.target.getX0() && y < this.target.getY0()) {			//Wenn die aktuelle x Position und y Position kleiner als die des Ziel sind
							// Debugausgabe
							// System.out.println("# Direction: 7");
							this.direction = 7;//wird die Methode check(7,0) aufgerufen. Die 7 steht f�r die Richtung unten rechts.
						}																	//Alle Richtungen mit entsprechenden Werten (0-7) sind am Anfang des Dokuments aufgelistet.
						else if(x > this.target.getX0() && y < this.target.getY0()){
							System.out.println("# Direction: 1");
							this.direction = 1;	
						}
						else if( x < this.target.getX0() && y > this.target.getY0()) {
							System.out.println("# Direction: 5");
							this.direction = 5;	
						}
						else if(x > this.target.getX0() && y > this.target.getY0()) {
							System.out.println("# Direction: 3");
							this.direction = 3;		
						}
						else if(x > this.target.getX0()) {
							System.out.println("# Direction: 2");
							this.direction = 2;	
						}
						else if(x < this.target.getX0()) {
							System.out.println("# Direction: 6");
							this.direction = 6;	
						}
						else if(y < this.target.getY0()) {
							System.out.println("# Direction: 0");
							this.direction = 0;	
						}
						else if(y > this.target.getY0()) {
							System.out.println("# Direction: 4");
							this.direction = 4;	
						}
						this.new_direction = this.direction;
						// Debugausgabe
						// System.out.println("# has_moved -> new_direction: "+this.direction);
					} else {
						
						boolean richtung = false;
						if(this.direction != 3 && this.direction != 4 && this.direction != 7)
							richtung = true;
						switch(wegfinde_cnt) {
							case 1:
								if(richtung)
									this.new_direction = this.direction+1;
								else
									this.new_direction = this.direction-1;
								break;
							case 2:
								if(richtung)
									this.new_direction = this.direction+2;
								else
									this.new_direction = this.direction-2;
								break;
							case 3:
								if(richtung)
									this.new_direction = this.direction-1;
								else
									this.new_direction = this.direction+1;
								break;
							case 4:
								if(richtung)
									this.new_direction = this.direction-2;
								else
									this.new_direction = this.direction+2;
								break;
							case 5:
								if(richtung)
									this.new_direction = this.direction+3;
								else
									this.new_direction = this.direction-3;
								break;
							case 6:
								if(richtung)
									this.new_direction = this.direction+4;
								else
									this.new_direction = this.direction-4;
								break;
							case 7:
								if(richtung)
									this.new_direction = this.direction-3;
								else
									this.new_direction = this.direction+3;
								break;
							default:
								this.wegfinde_cnt = 0;
						}
						int tmp = this.new_direction+4;
						if(tmp>7) tmp = tmp-8; // dir=8 geht schließlich nicht
						if(tmp == this.old_direction) {
							this.new_direction = this.old_direction;
							if(this.old_direction == 2 || this.old_direction == 6)
								this.wegfindetrouble = 2;
							else
								this.wegfindetrouble = 1;
							this.troublecnt = 0;
						}
						if(this.new_direction>7) this.new_direction = this.new_direction-8;
						if(this.new_direction<0) this.new_direction = this.new_direction+8;
					}
				} else { // so bei Trouble wird das ganze etwas anders gehändelt
					// es wird dafür gesorgt, dass der Spieler nicht wieder die entgegengesetzte Richtung einschlägt
					// sondern solang eine Richtung weiter, bis eine andere Richtung zum Ziel, außer die Entgegengesetzte frei ist
					// der Mensch läuft dadurch außen herum
					boolean trouble_out = false;
					int ziel_direction;
					if(x < this.target.getX0() && y < this.target.getY0()) {			//Wenn die aktuelle x Position und y Position kleiner als die des Ziel sind
						ziel_direction = 7;
					}																	//Alle Richtungen mit entsprechenden Werten (0-7) sind am Anfang des Dokuments aufgelistet.
					else if(x > this.target.getX0() && y < this.target.getY0()){
						ziel_direction = 1;	
					}
					else if( x < this.target.getX0() && y > this.target.getY0()) {
						ziel_direction = 5;	
					}
					else if(x > this.target.getX0() && y > this.target.getY0()) {
						ziel_direction = 3;		
					}
					else if(x > this.target.getX0()) {
						ziel_direction = 2;	
					}
					else if(x < this.target.getX0()) {
						ziel_direction = 6;	
					}
					else if(y < this.target.getY0()) {
						ziel_direction = 0;	
					}
					else if(y > this.target.getY0()) {
						ziel_direction = 4;	
					} else {
						ziel_direction = 4;
						this.wegfindetrouble = 0;
						// Debugausgabe
						// System.out.println("############################################################################ SOLLTE NICHT EINTRETEN!!!");
					}
					if(this.wegfindetrouble == 1) { // zwei verschiedene TroubleFälle (Links/Rechts und Oben/Unten)
						// Debugausgabe:
						// System.out.println("# TROUBLE: 1");
						/* if(ziel_direction == 4 || ziel_direction == 0) {
							this.wegfindetrouble = 2;
						} */
						if(ziel_direction < 4 ) {
							ziel_direction = 0;
							Coordinate coo = ausDirzuCoo(ziel_direction);
							trouble_out = checkFreePosition(coo.getXCoordinate(),coo.getYCoordinate());
						} else {
							ziel_direction = 4;
							Coordinate coo = ausDirzuCoo(ziel_direction);
							trouble_out = checkFreePosition(coo.getXCoordinate(),coo.getYCoordinate());
						}
					}
					/*	this.troublecnt++;
						System.out.println("# TROUBLECNT: "+this.troublecnt);
					 } else */
					if(this.wegfindetrouble == 2) {
						System.out.println("# TROUBLE: 2");
						if(ziel_direction < 4) {
							ziel_direction = 6;
							Coordinate coo = ausDirzuCoo(ziel_direction);
							trouble_out = checkFreePosition(coo.getXCoordinate(),coo.getYCoordinate());
						} else {
							ziel_direction = 2;
							Coordinate coo = ausDirzuCoo(ziel_direction);
							trouble_out = checkFreePosition(coo.getXCoordinate(),coo.getYCoordinate());
						}
					}
						/* this.troublecnt++;
						if(this.troublecnt > 1000) {
							this.wegfindetrouble = 0;
						} */
					// }
					if(trouble_out) { // Juhu, Trouble zu Ende, sobald außen herum ;-)
						this.wegfindetrouble = 0;
						this.new_direction = ziel_direction;
					} else {
						this.direction = this.old_direction;
						Coordinate coo = ausDirzuCoo(this.direction);
						if(!checkFreePosition(coo.getXCoordinate(),coo.getYCoordinate())) {
							this.wegfindetrouble = 0;
							System.out.println("Eingekesselt? Schnell raus hier!");
						}
					}
				}
				
				Coordinate coo = ausDirzuCoo(this.new_direction);
				if(checkFreePosition(coo.getXCoordinate(),coo.getYCoordinate())) {
					has_moved = true;
					System.out.println("# Position frei!");
				} else {
					wegfinde_cnt++;
					has_moved = false;
					System.out.println("# Position belegt!");
				}
				
				if(has_moved) {
					/* Debugausgaben
					 * System.out.println("# Setze neue Position!");
					 * System.out.println("# Alte Position: "+this.position.getX0()+","+this.position.getY0());
					 */
					setDirection(this.new_direction);
					newPos = ausDirzuCoo(this.direction);
					this.old_direction = new_direction;
					moveObject(newPos.getXCoordinate(), newPos.getYCoordinate()); // Die neue Position wird explizit gesetzt.
				}
				
				//System.out.println("Aktuell: x:"+x+" y:"+y+" Neu: x:"+newPos.getXCoordinate()+" y:"+newPos.getYCoordinate()+" Target: x:"+this.target.getX0()+" y:"+this.target.getY0()+ " " +this.direction);
			}
			/* Debugausgaben
				System.out.println("#      Old Direction: "+this.old_direction);
				System.out.println("# Aktuelle Direction: "+this.direction);
				System.out.println("# Aktuelle Position: "+this.position.getX0()+","+this.position.getY0());
				System.out.println("#              Ziel: "+this.target.getX0()+","+this.target.getY0());
			*/
		}
		// Debugausgabe
		// System.out.println("# Ende: stepNextPosition()");
	}
	// END: stepNextPos() */

	public boolean doActivity() { // Soll die ausgewählte Aktivität ausgeführt werden? JA, wenn Zielposition gleich der aktuellen ist
		if((position.getX0() == target.getX0())  && (position.getY0() == target.getY0())) {
			return true;
		}
		return false;
	}
	
	public void decreaseStatusOverTime(){ // Stati, die sich über die Zeit ändern, wenn keine Aktivität ausgeführt wird
		if(activity==0||activity==1)
		{
			this.alcLevel=this.alcLevel-0.00001;
			this.flirt=this.flirt-0.00001;
			this.fun=this.fun-0.000009;
			this.energy = this.energy - 0.00001;
		}
	}
	
	// END: AKTIVIT�TSMETHODEN
	
}
