package objects;

import java.awt.image.BufferedImage;
import player.*;

//created by Anna & Silke

public class Toilet extends DiscoObject {
	
	private boolean full;
	private int maxAmount;
	private int amount;
	
	public Toilet(BufferedImage image, int x, int y) {
		super("Toilet", image, x, y);
		accessible = true;
	}
	
	private int number = 11;
	
	/*
	0. BlauHohn
	1. RotOchsen
	2. GelbVögln
	3. SchwarzKatzerl
	4. ZitronenLimonade
	5. Eistee
	6. Cocktail
	7. Shot
	8. sitzen
	9. tanzen
	10. flirten
	11. auf Toilette gehen
	*/
	
	/*public void goToToilet(Human p, int aktivitaet){
		p.setStatusES(p,aktivitaet,number);
	}*/
}
