package objects;

import javax.swing.JLabel;

//created by Anna & Silke

public abstract class Drink extends JLabel {
	
	protected float price;
	protected char name;
	protected boolean bonus;
	
	public abstract void drinkBought(boolean clickedByPlayer);
	
}
