
//created by Anna & Silke

public class Toilet extends DiscoObjects {
	
	private int accessible = 0;
	private boolean full;
	private int maxAmount;
	private int amount;
	
	
	public void goToilet(Mensch p){
		//Überprüfung, ob der Spieler oder ein NPC die Methode aufruft
		if(p=NPC&(amount == maxamount)){
			p.waiting();
		}
		else popup();
	}
	public pop(){
		//Eine Funktion, die dem Hauptspieler sagt, dass die Toilette besetzt ist.
	}

}
