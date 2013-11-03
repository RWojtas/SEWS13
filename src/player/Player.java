package player;

import java.awt.image.BufferedImage;

public class Player extends Human{
	private int money;
	
	public Player(int money, char gender, int type, BufferedImage image, int x, int y, int size, int direction) {
		super(gender,type,image,x,y,size,direction);
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
