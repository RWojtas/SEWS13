package objects;

import java.awt.image.BufferedImage;

//created by Anna & Silke

public class DJ extends DiscoObject {
	private int song;
	
	public DJ(BufferedImage image, int x, int y) {
		super("DJ", image, x, y);
		accessible = false;
	}
	
	public void changeSong(){
		
	}

}
