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
	    discoObject[0] = new Bar(graphicManager.bar.getImage(), scaleToScreenX(0),scaleToScreenY(-18));
	    discoObject[1] = new Toilet(graphicManager.wc.getImage(),scaleToScreenX(12),scaleToScreenY(270));
	    discoObject[2] = new DJ(graphicManager.dj.getImage(),scaleToScreenX(0),scaleToScreenY(428));
	    discoObject[3] = new Table(graphicManager.table.getImage(),scaleToScreenX(480),scaleToScreenY(23));
	    discoObject[4] = new Table(graphicManager.table.getImage(),scaleToScreenX(692),scaleToScreenY(23));
	    discoObject[5] = new Table(graphicManager.table.getImage(),scaleToScreenX(902),scaleToScreenY(23));
	    discoObject[6] = new Table(graphicManager.dancefloor.getImage(),scaleToScreenX(274),scaleToScreenY(768-330));
	    
	    for(DiscoObject obj : discoObject)
			panel.add(obj);
	}
	
	public boolean checkFreePosition(Position ul, Position ur, Position dl, Position dr) {
		for(int i=0;i<discoObject.length;i++) {
			//Hier wird dann geprüft, ob eine Kolision mit einem DiskoObjekt entsteht.
		}
		return true;
	}
	
	private int scaleToScreenX(double v) {
		return (int) (v*deskResolution.getWidth()/1366);
	}
	
	private int scaleToScreenY(double v) {
		return (int) (v*deskResolution.getHeight()/768);
	}
}
