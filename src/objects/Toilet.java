package objects;

import java.awt.image.BufferedImage;

//created by Anna & Silke

public class Toilet extends DiscoObject {
	
	private boolean full;
	private int maxAmount;
	private int amount;
	
	public Toilet(BufferedImage image, int x, int y) {
		super(image, x, y);
		accessible = false;
	}
}
