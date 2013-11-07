package objects;

import java.awt.image.BufferedImage;

import player.Position;

public class Bar extends DiscoObject {

	private int[] drinkList = new int[8];
	private int waitingTime;
	
	public Bar(BufferedImage image, int x, int y) {
		super("Bar", image, x, y);
		accessible = false;
	}
	
}
