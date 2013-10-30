package objects;
import java.awt.image.BufferedImage;

import player.Spieler;

//created by Anna & Silke

public class Dancefloor extends DiscoObject {
	
	private int amount;
	private int maxamount;
	private boolean full = false;
	
	public Dancefloor(BufferedImage image, int x, int y) {
		super(image, x, y);
		accessible = true;
	}
	
	public void getClicked(Spieler s){
		s.tanzen();
	}
	
	public void checkFull(){
		if((maxamount-amount)==0){
			full=true;
		}
	}
}
