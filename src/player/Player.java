package player;

import java.awt.image.BufferedImage;

import main.Highscore;

/**
 * 
 * @author Raffael & Sebastian
 *
 */

public class Player extends Human{
	private double money;
	
	
	public Player(double money, char gender, BufferedImage image, int x, int y, int direction) {
		super("Player",gender,image,x,y,direction);
		this.type = type;
		this.money = money;
	}
	
	public void resetPlayer(double money, char gender, BufferedImage image, int x, int y, int direction) {
		super.resetHuman("Player",gender,image,x,y,direction);
		this.type = type;
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	public void removeMoney(double money){
		this.money -= money;		
	}
	
	
}
