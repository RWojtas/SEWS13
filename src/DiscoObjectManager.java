import javax.swing.JPanel;

import objects.*;
import player.Position;


public class DiscoObjectManager {
	public DiscoObject[] discoObject;
	public GraphicManager graphicManager;
	
	public DiscoObjectManager(GraphicManager graphicManager) {
		this.graphicManager = graphicManager;
	}
	
	public void updateComponents() {
		
	}
	
	public void addComponents(JPanel panel) {
		
		//Folgende Codezeilen sind nur ein Beispiel und können gern geloescht werden!
	    discoObject = new DiscoObject[7];
	    //discoObject[0] = new Bar(graphicManager.background.getImage(),0,0);
	    discoObject[0] = new Bar(graphicManager.bar.getImage(),0,-18);
	    discoObject[1] = new Toilet(graphicManager.wc.getImage(),12,270);
	    discoObject[2] = new DJ(graphicManager.dj.getImage(),0,428);
	    discoObject[3] = new Table(graphicManager.table.getImage(),480,23);
	    discoObject[4] = new Table(graphicManager.table.getImage(),692,23);
	    discoObject[5] = new Table(graphicManager.table.getImage(),902,23);
	    discoObject[6] = new Table(graphicManager.dancefloor.getImage(),274,768-330);
	    
	    for(DiscoObject obj : discoObject)
			panel.add(obj);
	}
	
	public boolean checkFreePosition(Position ul, Position ur, Position dl, Position dr) {
		for(int i=0;i<discoObject.length;i++) {
			//Hier wird dann geprüft, ob eine Kolision mit einem DiskoObjekt entsteht.
		}
		return true;
	}
}
