package objects;
import java.awt.image.BufferedImage;

import player.Player;
import player.Position;

//created by Anna & Silke

public class Dancefloor extends DiscoObject {
	
	private int amount;
	private int maxAmount;
	private boolean full = false;
	
	public Dancefloor(BufferedImage image, int x, int y) {
		super("Dancefloor", image, x, y);
		accessible = true;
	}
	
	public void getClicked(Player s){
		s.dance();
	}
	
	public void checkFull(){
		if((maxAmount-amount)==0){
			full=true;
		}
	}
	
	private int number = 9;
	
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
}
