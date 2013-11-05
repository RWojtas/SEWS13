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
	protected Highscore h = Highscore.getInstance();
	
	public DiscoObject(BufferedImage image, int x, int y) {
		position = new Position(x,y);
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
	
	/*
	1. BlauHohn
	2. RotOchsen
	3. GelbVögln
	4. SchwarzKatzerl
	5. ZitronenLimonade
	6. Eistee
	7. Cocktail
	8. Shot
	9. sitzen
	10. tanzen
	11. flirten
	12. auf Toilette gehen
	*/
	
	
	public void setStatusES(Human p, int aktivitaet, int number){
		
		if (aktivitaet == 3){
			switch (number){
				case 0: 	p.addEnergy(0.30);
							p.addUrine(0.40);
							p.removeAlcLevel(0.20);
							p.addFun(0.10);
							h.setBonus(1);
							break;
				case 1: 	p.addFlirt(0.20);
							p.addUrine(0.40);
							p.addFun(0.10);
							h.setBonus(1);
							break;
				case 2:		p.addFun(0.20);
							p.addUrine(0.40);
							p.addFlirt(0.20);
							h.setBonus(1);
							break;
				case 3:		p.removeAlcLevel(0.20);
							p.addEnergy(0.20);
							p.addUrine(0.40);
							p.addFun(0.1);
							h.setBonus(1);
							break;
				case 4: 	p.removeAlcLevel(0.20);
							p.addEnergy(0.20);
							p.addUrine(0.40);
							p.addFun(0.10);
							h.setBonus(1);
							break;
				case 5: 	p.addUrine(0.40);
							break;
				case 6:		p.addUrine(0.40);
							p.addEnergy(0.30);
							break;
				case 7:		p.addUrine(0.40);
							p.addAlcLevel(0.30);
							break;
				case 8:		p.addUrine(0.40);
							p.addAlcLevel(0.30);
							break;
				case 9:		p.addEnergy(0.40);
							break;
				case 10 :	p.removeEnergy(0.25);
							p.addFun(0.3);
							break;
				case 11: 	p.addFlirt(30);
							p.addFun(30);
							break;
				case 12:	p.setUrine(0);
							break;
				
			}	
		}
	}
			
		public void setStatusAS (Human p, int aktivitaet, int number){
			if (aktivitaet == 3){
				switch (number){
					case 0: 	p.addEnergy(0.30);
								p.addUrine(0.40);
								p.removeAlcLevel(0.20);
								p.addFun(0.10);
								h.setBonus(1);
								break;
					case 1: 	p.addFlirt(0.20);
								p.addUrine(0.40);
								p.addFun(0.10);
								h.setBonus(1);
								break;
					case 2:		p.addFun(0.20);
								p.addUrine(0.40);
								p.addFlirt(0.20);
								h.setBonus(1);
								break;
					case 3:		p.removeAlcLevel(0.20);
								p.addEnergy(0.20);
								p.addUrine(0.40);
								p.addFun(0.1);
								h.setBonus(1);
								break;
					case 4: 	p.removeAlcLevel(0.20);
								p.addEnergy(0.20);
								p.addUrine(0.40);
								p.addFun(0.10);
								h.setBonus(1);
								break;
					case 5: 	p.addUrine(0.40);
								break;
					case 6:		p.addUrine(0.40);
								p.addEnergy(0.30);
								break;
					case 7:		p.addUrine(0.40);
								p.addAlcLevel(0.30);
								break;
					case 8:		p.addUrine(0.40);
								p.addAlcLevel(0.30);
								break;
					case 9:		p.addEnergy(0.40);
								break;
					case 10 :	p.removeEnergy(0.25);
								p.addFun(0.3);
								break;
					case 11: 	p.addFlirt(30);
								p.addFun(30);
								break;
					case 12:	p.setUrine(0);
								break;
					
				}	
			}
			
		}
	
	
}
