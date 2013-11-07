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
	final static int as_cntr = 10;
	public ASManager(GraphicManager graphicManager) {
		this.graphicManager = graphicManager;
	}
	
	public void updateComponents() {
		for(int i=0;i<human.length;i++) {
			human[i].stepNextPosition();
		}	
	}
	
	public void addComponents(JPanel panel) {
		human = new Human[as_cntr];
		for(int i = 0; i < as_cntr; i++) {
			human[i] = new AS('w', graphicManager.human.getImage(), BufferedImageLoader.scaleToScreenX(200+(i*37)), BufferedImageLoader.scaleToScreenY(300+(i*28)),1);
		    human[i].addMouseListener(new ASMouseListener());
		}
	    for(Human obj : human)
			panel.add(obj);
	    human[9].setTarget(BufferedImageLoader.scaleToScreenX(1000), BufferedImageLoader.scaleToScreenY(500));
	    human[9].setActivity(1);
	    for(int i = 8; i >= 0; i--) {
	    human[i].setTarget(BufferedImageLoader.scaleToScreenX(500), BufferedImageLoader.scaleToScreenY(350));
	    human[i].setActivity(1);
	    }
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
    	if (component != null && 	component.hashCode() != id) 
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
