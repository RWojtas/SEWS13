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
	
	/*
	0. BlauHohn
	1. RotOchsen
	2. GelbVögln
	3. SchwarzKatzerl
	4. ZitronenLimonade
	5. Eistee
	6. Cocktail
	7. Shot
	8. sitzen
	9. tanzen
	10. flirten
	11. auf Toilette gehen
	*/

}
