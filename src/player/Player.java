package player;

import java.awt.image.BufferedImage;

public class Player extends Human{
	private int money;
	
	public Player(int money, char gender, BufferedImage image, int x, int y, int direction) {
		super("Player",gender,image,x,y,direction);
		this.type = type;
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
