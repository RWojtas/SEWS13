package player;

import java.awt.image.BufferedImage;

public class Spieler extends Mensch{
	private int geld;
	
	public Spieler(int geld, char geschlecht, int type, BufferedImage image, int x, int y) {
		super(geschlecht,type,image,x,y);
		this.geld = geld;
	}

	public int getGeld() {
		return geld;
	}

	public void setGeld(int geld) {
		this.geld = geld;
	}
	
}
