package objects;

import java.awt.image.BufferedImage;

import player.Position;

//created by Anna & Silke

public class Bench extends DiscoObject {
	
	
	public Bench(BufferedImage image, int x, int y) {
		super(image, x, y);
		accessible = true;
	}

}
