package objects;

import java.awt.image.BufferedImage;

//created by Anna & Silke

public class Table extends DiscoObject {

	public Table(BufferedImage image, int x, int y) {
		super("Table", image, x, y);
		accessible = false;
	}
}
