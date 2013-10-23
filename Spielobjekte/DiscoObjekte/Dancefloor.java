

public class Dancefloor extends DiscoObjects{

	private accessible = 1;
	private int amount;
	private int maxamount;
	private boolean full = 0;
	
	public void dance(){
		
	}
	
	public void checkfull(){
		if((maxamount-amount)==0){
			full=1;
		}
	}
}
