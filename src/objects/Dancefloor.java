package objects;
import java.awt.image.BufferedImage;

import player.Player;

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
}
