package objects;

import java.awt.image.BufferedImage;

import player.Position;

public class Bar extends DiscoObject {

	private int[] drinkList = new int[8];
	private int waitingTime;
	
	public Bar(BufferedImage image, int x, int y) {
		super("Bar", image, x, y);
		accessible = true;
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
