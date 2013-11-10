package objects;

import java.awt.image.BufferedImage;

public class Carpet extends DiscoObject {

	public Carpet(String type, BufferedImage image, int x, int y) {
		super(type, image, x, y);
		accessible = true;
	}

}
