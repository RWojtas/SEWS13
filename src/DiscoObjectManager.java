import javax.swing.JPanel;
import objects.*;


public class DiscoObjectManager {
	public DiscoObject[] discoObject;
	public GraphicManager graphicManager;
	
	public DiscoObjectManager(GraphicManager graphicManager) {
		this.graphicManager = graphicManager;
	}
	
	public void updateComponents() {
		
	}
	
	public void addComponents(JPanel panel) {
	    discoObject = new DiscoObject[1];
	    discoObject[0] = new Bar(graphicManager.background.getImage(),0,0);
	    
		panel.add(discoObject[0]);
	}
}
