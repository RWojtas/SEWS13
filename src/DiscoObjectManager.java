import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import objects.*;
import player.Position;


public class DiscoObjectManager {
	public DiscoObject[] discoObject;
	public GraphicManager graphicManager;
	public static Dimension deskResolution;
	
	public DiscoObjectManager(GraphicManager graphicManager) {
		deskResolution = Toolkit.getDefaultToolkit().getScreenSize();
		this.graphicManager = graphicManager;
	}
	
	public void updateComponents() {
		
	}
	
	public void addComponents(JPanel panel) {
		
		//Folgende Codezeilen sind nur ein Beispiel und können gern geloescht werden!
	    discoObject = new DiscoObject[7];
	    //discoObject[0] = new Bar(graphicManager.background.getImage(),0,0);
	    discoObject[0] = new Bar(graphicManager.bar.getImage(),BufferedImageLoader.scaleToScreenX(0),BufferedImageLoader.scaleToScreenY(-18));
	    discoObject[1] = new Toilet(graphicManager.wc.getImage(),BufferedImageLoader.scaleToScreenX(12),BufferedImageLoader.scaleToScreenY(270));
	    discoObject[2] = new DJ(graphicManager.dj.getImage(),BufferedImageLoader.scaleToScreenX(0),BufferedImageLoader.scaleToScreenY(428));
	    discoObject[3] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(480),BufferedImageLoader.scaleToScreenY(23));
	    discoObject[4] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(692),BufferedImageLoader.scaleToScreenY(23));
	    discoObject[5] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(902),BufferedImageLoader.scaleToScreenY(23));
	    discoObject[6] = new Table(graphicManager.dancefloor.getImage(),BufferedImageLoader.scaleToScreenX(274),BufferedImageLoader.scaleToScreenY(768-330));
	    
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
