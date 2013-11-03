import java.awt.BorderLayout;

import javax.swing.JPanel;

import player.*;


public class ASManager {
	public Human[] human;
	public GraphicManager graphicManager;
	
	public ASManager(GraphicManager graphicManager) {
		this.graphicManager = graphicManager;
	}
	
	public void updateComponents() {
		
	}
	
	public void addComponents(JPanel panel) {
		panel.add(new AS('w', 1, graphicManager.human.getImage(), BufferedImageLoader.scaleToScreenX(500), BufferedImageLoader.scaleToScreenY(400),0,0));
	}
	
	public boolean checkFreePosition(Position ul, Position ur, Position dl, Position dr) {
		// for(int i=0;i<human.length;i++) { -> WAT für length?
			
			//Hier wird dann geprüft, ob eine Kolision mit einem Menschen entsteht.
		// }
		return true;
	}
}
