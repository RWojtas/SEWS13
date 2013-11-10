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
		accessible = false;
	}
	
	private int number = 11;
	
}
