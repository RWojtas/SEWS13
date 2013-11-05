package objects;


import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import player.*;

/**
 * @author Anna
 * @author Silke
 */

public abstract class DiscoObject extends JLabel {

	protected Position position;
	protected boolean accessible;
	
	public DiscoObject(BufferedImage image, int x, int y) {
		position = new Position(x,y);
		setIcon(new ImageIcon(image.getSubimage(0,0,image.getWidth(),image.getHeight())));
		setBounds(x,y,image.getWidth(),image.getHeight());
	}
	
	public void moveObject(int x, int y) {
		position.setPosition(x, y);
		setLocation(position.getXPosition(),position.getYPosition());
	}
	
	//Highscore h = Highscore.getInstance();
	
	public void setStatusES(Person p, int aktivitaet, int number){
		
		if (aktivitaet == 3){
			switch (number){
				case '0': 	p.addEnergy(0.30);
							p.addUrine(0.40);
							p.removeAlcLevel(0.20);
							p.addFun(0.10);
							instance.setBonus(1000);
							break;
				case '1': 	p.addFlirt(0.20);
							p.addUrine(0.40);
							p.addFun(0.10);
							break;
				case '2':	p.addFun(0.20);
							p.addUrine(0.40);
							p.addFlirt(0.20);
							break;
				case '3':	p.removeAlcLevel(0.20);
							p.addEnergy(0.20);
							p.addUrine(0.40);
							p.addFun(0.1);
							break;
				
			}			
		}
		
	}
}
