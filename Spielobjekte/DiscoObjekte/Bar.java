package edu.dhbw.pse.music;

public class Bar extends DiscoObjects {

	Drinks drinklist = new Drinks[];
	int waitingtime;
	accessible = 0;
	
	//Vielleicht wird Mensch p durch ein Attribut in der Klasse Mensch ersetzt
	buydrink(key k, Mensch p){
		boolean clickedbyplayer;
		if(p=Spieler){
			clickedbyplayer=1;
		}
		else clickedbyplayer=0;
		Drinks[k].drinkbought(clickedbyplayer);
	}
}
