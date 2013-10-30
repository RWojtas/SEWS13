package objects;

import java.awt.image.BufferedImage;

public class Bar extends DiscoObject {

	private Drink[] drinkList;
	private int waitingTime;
	
	public Bar(BufferedImage image, int x, int y) {
		super(image, x, y);
		accessible = true;
	}

}
