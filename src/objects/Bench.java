package objects;

import java.awt.image.BufferedImage;

import player.Position;

//created by Anna & Silke

public class Bench extends DiscoObject {
	
	
	public Bench(BufferedImage image, int x, int y) {
		super("Bench", image, x, y);
		accessible = true;
	}
	
	private int number = 8;
	
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
