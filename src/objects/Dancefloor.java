package objects;
import player.Spieler;

//created by Anna & Silke

public class Dancefloor extends DiscoObject{

	private int accessible = 1;
	private int amount;
	private int maxamount;
	private boolean full = false;
	
	public void getClicked(Spieler s){
		s.tanzen();
	}
	
	public void checkfull(){
		if((maxamount-amount)==0){
			full=true;
		}
	}
}
