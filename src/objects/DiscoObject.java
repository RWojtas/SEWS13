package objects;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import overlay.BarOverlay;
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
	public static BarOverlay barOverlay;
	
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
				case 11: 	if((p.getMoney()-1.5)>0){
								p.removeMoney(1.5);
								if((p.getEnergy()+0.30)<1)p.addEnergy(0.3);
								else p.setEnergy(1);
					
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								
								if((p.getAlcLevel()-0.20)>0)p.removeAlcLevel(0.2);
								else p.setAlcLevel(0);
								
								if((p.getFun()+0.10)<1)p.addFun(0.1);
								else p.setFun(1);
								h.setBonus(1);
							}


							
							
							break;
				case 12: 	if((p.getMoney()-2)>0){
								p.removeMoney(2);
								if((p.getFlirt()+0.20)<1)p.addFlirt(0.2);
								else p.setFlirt(1);
					
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								
								if((p.getAlcLevel()+0.20)<1)p.addAlcLevel(0.2);
								else p.setAlcLevel(1);
								
								if((p.getFun()+0.10)<1)p.addFun(0.1);
								else p.setFun(1);
								h.setBonus(1);
							}
							break;
				case 13:	if((p.getMoney()-2)>0){
								p.removeMoney(2);
								if((p.getFlirt()+0.20)<1)p.addFlirt(0.2);
								else p.setEnergy(1);
					
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								
								if((p.getFun()+0.20)<1)p.addFun(0.2);
								else p.setFun(1);
								h.setBonus(1);
							}
							break;
				case 14:	if((p.getMoney()-2)>0){
								p.removeMoney(2);
								if((p.getEnergy()+0.20)<1)p.addFlirt(0.2);
								else p.setFlirt(1);
					
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								
								if((p.getAlcLevel()-0.20)>0)p.removeAlcLevel(0.2);
								else p.setAlcLevel(0);
								
								if((p.getFun()+0.10)<1)p.addFun(0.1);
								else p.setFun(1);
								h.setBonus(1);
							}
							break;
				case 15: 	if((p.getMoney()-2.5)>0){
								p.removeMoney(2.5);
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
							}
							break;
				case 16:	if((p.getMoney()-2.5)>0){
								p.removeMoney(2.5);
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								if((p.getEnergy()+0.3)<1) p.addEnergy(0.3);
								else p.setEnergy(1);
							}
							break;
				case 17:	if((p.getMoney()-3.5)>0){
								p.removeMoney(3.5);
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								if((p.getAlcLevel()+0.3)<1) p.addAlcLevel(0.3);
								else p.setAlcLevel(1);
							}
							break;
				case 18:	if((p.getMoney()-2.5)>0){
								p.removeMoney(2.5);
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								if((p.getAlcLevel()+0.4)<1) p.addAlcLevel(0.4);
								else p.setAlcLevel(1);
							}
							break;
				case 4:		if((p.getEnergy()+0.4)<1) p.addEnergy(0.4);
							else p.setEnergy(1);
							break;
				case 2:		if((p.getEnergy()-0.25)>0) p.removeEnergy(0.25);
							else p.setEnergy(0);
							if((p.getFun()+0.3)<1) p.addFun(0.3);
							else p.setFun(1);
							break;
				case 7: 	if((p.getFlirt()+0.3)>0) p.addFlirt(0.3);
							else p.setFlirt(1);
							if((p.getFun()+0.3)<1) p.addFun(0.3);
							else p.setFun(1);
							break;
				case 5	:	p.setUrine(0);
							break;
				
			}	
	}
			
		public void setStatusAS (Human p, int number){
				switch (number){
					case 11: 	if((p.getEnergy()+0.30)<1)p.addEnergy(0.3);
								else p.setEnergy(1);
					
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								
								if((p.getAlcLevel()-0.20)>0)p.removeAlcLevel(0.2);
								else p.setAlcLevel(0);
								
								if((p.getFun()+0.10)<1)p.addFun(0.1);
								else p.setFun(1);
								break;
					case 12: 	if((p.getFlirt()+0.20)<1)p.addFlirt(0.2);
								else p.setFlirt(1);
					
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								
								if((p.getAlcLevel()+0.20)<1)p.addAlcLevel(0.2);
								else p.setAlcLevel(1);
								
								if((p.getFun()+0.10)<1)p.addFun(0.1);
								else p.setFun(1);	
								break;
					case 13:	if((p.getFlirt()+0.20)<1)p.addFlirt(0.2);
								else p.setEnergy(1);
					
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								
								if((p.getFun()+0.20)<1)p.addFun(0.2);
								else p.setFun(1);
								break;
					case 14:	if((p.getEnergy()+0.20)<1)p.addFlirt(0.2);
								else p.setFlirt(1);
					
								if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								
								if((p.getAlcLevel()-0.20)>0)p.removeAlcLevel(0.2);
								else p.setAlcLevel(0);
								
								if((p.getFun()+0.10)<1)p.addFun(0.1);
								else p.setFun(1);
								h.setBonus(1);
								break;
					case 15: 	if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								break;
					case 16:	if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								if((p.getEnergy()+0.3)<1) p.addEnergy(0.3);
								else p.setEnergy(1);
								break;
					case 17:	if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								if((p.getAlcLevel()+0.3)<1) p.addAlcLevel(0.3);
								else p.setAlcLevel(1);
								break;
					case 18:	if((p.getUrine()+0.4)<1)p.addUrine(0.40);
								else p.setUrine(1);
								if((p.getAlcLevel()+0.4)<1) p.addAlcLevel(0.4);
								else p.setAlcLevel(1);
								break;
					case 4:		if((p.getEnergy()+0.4)<1) p.addEnergy(0.4);
								else p.setEnergy(1);
								break;
					case 2:		if((p.getEnergy()-0.25)>0) p.removeEnergy(0.25);
								else p.setEnergy(0);
								if((p.getFun()+0.3)<1) p.addFun(0.3);
								else p.setFun(1);
								break;
					case 7: 	if((p.getFlirt()+0.3)>0) p.addFlirt(0.3);
								else p.setFlirt(1);
								if((p.getFun()+0.3)<1) p.addFun(0.3);
								else p.setFun(1);
								break;
					case 5	:	p.setUrine(0);
								break;
			
		}
	}	
}
