package objects;

import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import player.*;
import main.Highscore;

/**
 * @author Anna
 * @author Silke
 */

public abstract class DiscoObject extends JLabel {

	protected Position position;
	protected boolean accessible;
	protected String type;
	protected static Highscore h = Highscore.getInstance();
	public boolean openOverlay = false;
	
	public DiscoObject(String type, BufferedImage image, int x, int y) {
		position = new Position(x,y);
		this.type = type;
		setIcon(new ImageIcon(image.getSubimage(0,0,image.getWidth(),image.getHeight())));
		setBounds(position.getXPosition(),position.getYPosition(),image.getWidth(),image.getHeight());
		setOpaque(false);
	}
	
	public void moveObject(int x, int y) {
		position.setPosition(x, y);
		setLocation(position.getXPosition(),position.getYPosition());
	}
	
	public int getPositionX() {
		return position.getXPosition();
	}

	public int getPositionY() {
		return position.getYPosition();
	}
	
	public boolean getAccessible() {
		return this.accessible;
	}
	
	public String getType() {
		return this.type;
	}
	
	/*
	/* 
 * Aktivitätentabelle:
 * 	0  - offen
 *  1  - gehen
 *  2  - tanzen
 *  3  - trinken
 *  4  - sitzen
 *  5  - urinieren
 *  6  - reden
 *  7  - flirten
 *  8  - Musik wünschen
 *  9  - ausruhen
 *  10 - im Koma liegen
 *  11 - BlauHohn 1,5 Euro
 *  12 - RotOchsen 2 Euro
 *  13 - GelbV�gln 2,5 Euro
 *  14 - SchwarzKatzerl 2 Euro
 *  15 - ZitronenLimonade 2,5 Euro
 *  16 - Eistee 2,5 Euro
 *  17 - Cocktail 3,5 Euro
 *  18 - Shot 2,5 Euro
	*/
	
	
	public static void setStatusES(Player p, int number){ 
			switch (number){
				case 11: 	p.addEnergy(0.30);
							p.addUrine(0.40);
							p.removeAlcLevel(0.20);
							p.addFun(0.10);
							p.removeMoney(1.5);
							h.setBonus(1);
							break;
				case 12: 	p.addFlirt(0.20);
							p.addUrine(0.40);
							p.addFun(0.10);
							p.removeMoney(2);
							h.setBonus(1);
							break;
				case 13:	p.addFun(0.20);
							p.addUrine(0.40);
							p.addFlirt(0.20);
							p.removeMoney(2.5);
							h.setBonus(1);
							break;
				case 14:	p.removeAlcLevel(0.20);
							p.addEnergy(0.20);
							p.addUrine(0.40);
							p.addFun(0.1);
							p.removeMoney(2);
							h.setBonus(1);
							break;
				case 15: 	p.addUrine(0.40);
							p.removeMoney(2.5);
							break;
				case 16:	p.addUrine(0.40);
							p.addEnergy(0.30);
							p.removeMoney(2.5);
							break;
				case 17:	p.addUrine(0.40);
							p.addAlcLevel(0.30);
							p.removeMoney(3.5);
							break;
				case 18:	p.addUrine(0.40);
							p.addAlcLevel(0.30);
							p.removeMoney(2.5);
							break;
				case 4:		p.addEnergy(0.40);
							break;
				case 2:		p.removeEnergy(0.25);
							p.addFun(0.3);
							break;
				case 7: 	p.addFlirt(30);
							p.addFun(30);
							break;
				case 5	:	p.setUrine(0);
							break;
				
			}	
	}
			
		public void setStatusAS (Human p, int number){
				switch (number){
					case 0: 	p.addEnergy(0.30);
								p.addUrine(0.40);
								p.removeAlcLevel(0.20);
								p.addFun(0.10);
								break;
					case 1: 	p.addFlirt(0.20);
								p.addUrine(0.40);
								p.addFun(0.10);
								break;
					case 2:		p.addFun(0.20);
								p.addUrine(0.40);
								p.addFlirt(0.20);
								break;
					case 3:		p.removeAlcLevel(0.20);
								p.addEnergy(0.20);
								p.addUrine(0.40);
								p.addFun(0.1);
								break;
					case 4: 	p.addUrine(0.40);
								break;
					case 5:		p.addUrine(0.40);
								p.addEnergy(0.30);
								break;
					case 6:		p.addUrine(0.40);
								p.addAlcLevel(0.30);
								break;
					case 7:		p.addUrine(0.40);
								p.addAlcLevel(0.30);
								break;
					case 8:		p.addEnergy(0.40);
								break;
					case 9:		p.removeEnergy(0.25);
								p.addFun(0.3);
								break;
					case 10: 	p.addFlirt(30);
								p.addFun(30);
								break;
					case 11:	p.setUrine(0);
								break;
					
				}	
			
		}
	
	
}
