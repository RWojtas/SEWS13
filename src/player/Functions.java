package player;

import java.util.Random;

public class Functions {
	//Random zufall = new Random();
	
	public int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
		//return  zufall.nextInt(high-low)+low;
	}
	
}
