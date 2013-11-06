package main;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import objects.DiscoObject;
import player.*;


public class ASManager {
	public Human[] human;
	public GraphicManager graphicManager;
	
	public ASManager(GraphicManager graphicManager) {
		this.graphicManager = graphicManager;
	}
	
	public void updateComponents() {
		for(int i=0;i<human.length;i++) {
			human[i].stepNextPosition();
		}	
	}
	
	public void addComponents(JPanel panel) {
		human = new Human[1];
		human[0] = new AS('w', graphicManager.human.getImage(), BufferedImageLoader.scaleToScreenX(700), BufferedImageLoader.scaleToScreenY(400),1);
	    human[0].addMouseListener(new ASMouseListener());	   

	    for(Human obj : human)
			panel.add(obj);
	}
	
    public Human getComponentAt(int x, int y) {
		JLayeredPane clickedLayeredPane;
		JPanel clickedPanel;
	  	Human clickedObject = null;
	  	  
	  	clickedLayeredPane = GameView.layeredPane;
	  	
	  	for(int i=0;i<clickedLayeredPane.getComponentCount();i++) {
	  		if(clickedObject == null) {
		  		clickedPanel = (JPanel)clickedLayeredPane.getComponent(i);
		        try {
		  	  	    clickedObject = (Human)clickedPanel.getComponentAt(x, y);  
		  	  	} catch(Exception e) {
		  	  	    clickedObject = null;
		  	  	}	
		  	}
	  	}
	  	
	  	return clickedObject;
	}
    
    public boolean checkFreeCoordinate(int id, Coordinate coordinate) {
    	Human component = getComponentAt(coordinate.getXCoordinate(),coordinate.getYCoordinate());
    	if (component != null && component.hashCode() != id) 
			return false;
    	else
    		return true;
    }
	
	public boolean checkFreePosition(int id, Coordinate lo, Coordinate ro, Coordinate lu, Coordinate ru) {		
		if(!checkFreeCoordinate(id, lo))
			return false;
		if(!checkFreeCoordinate(id, ro))
			return false;
		if(!checkFreeCoordinate(id, lu))
			return false;
		if(!checkFreeCoordinate(id, ru))
			return false;
		
		return true;
	}
	
	class ASMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    System.out.println("mouseClicked");
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    
		  	AS clickedObject = (AS)e.getSource();  
		  	
		    /* TODO Raffael & Sebastian (& Nicolas)
		     * Aktion: NPC ansprechen/interagieren
		     */
		}

	    @Override
		public void mouseEntered(MouseEvent e) {
		    //Wird ausgelöst, wenn die Maus den aktiven Bereich des MouseListeners betritt
			  
		}

		@Override
		public void mouseExited(MouseEvent e) {
	        //Wird ausgelöst, wenn die Maus den aktiven Bereich des MouseListeners verlässt
			  
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt (egal wie lange der Klick andauert)
			  
	    }

	    @Override
		public void mouseReleased(MouseEvent e) {
		     //Wird ausgelöst, nachdem man einen Klick mit der Maus wieder loslässt
			  
	    }
	}
}
