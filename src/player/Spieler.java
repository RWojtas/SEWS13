package player;

public class Spieler extends Mensch{
	private int geld;
	
	public Spieler(int geld, char geschlecht, int type) {
		super(geschlecht,type);
		this.geld = geld;
	}

	public int getGeld() {
		return geld;
	}

	public void setGeld(int geld) {
		this.geld = geld;
	}
	
}
