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
		panel.add(new AS('w', 1, graphicManager.human.getImage(), 500, 400));
	}
}
