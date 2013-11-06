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
		human[0] = new AS('w', 1, graphicManager.human.getImage(), BufferedImageLoader.scaleToScreenX(700), BufferedImageLoader.scaleToScreenY(400),0,0,1);
	    human[0].addMouseListener(new ASMouseListener());	   

	    for(Human obj : human)
			panel.add(obj);
	}
	
    public Human getComponentAt(int x, int y) {
		JLayeredPane clickedLayeredPane;
		JPanel clickedPanel;
	  	Human clickedObject = null;
	  	  
	  	clickedLayeredPane = GameView.layeredPane;
	  	  
	  	clickedPanel = (JPanel)clickedLayeredPane.getComponent(0);
	  	  
	  	try {
	  		clickedObject = (Human)clickedPanel.getComponentAt(x, y);  
	  	} catch(Exception e) {
	  		clickedObject = null;
	  	}
	  	  
	  	if(clickedObject == null) {
	  		clickedPanel = (JPanel)clickedLayeredPane.getComponent(1);
	        try {
	  	  	    clickedObject = (Human)clickedPanel.getComponentAt(x, y);  
	  	  	} catch(Exception e) {
	  	  	    clickedObject = null;
	  	  	}	
	  	}
	  	  		
	  	if(clickedObject == null) {
	  	    clickedPanel = (JPanel)clickedLayeredPane.getComponent(2);
	  	    try {
	  		    clickedObject = (Human)clickedPanel.getComponentAt(x, y);  
	  		} catch(Exception e) {
	  		     clickedObject = null;
	  		}
	    }
	  	return clickedObject;
	}
	
	public boolean checkFreePosition(Coordinate lo, Coordinate ro, Coordinate lu, Coordinate ru) {
		Human component = getComponentAt(lo.getXCoordinate(),lo.getYCoordinate());
		if (component != null) 
			return false;
		
		component = getComponentAt(ro.getXCoordinate(),ro.getYCoordinate());
		if (component != null)
			return false;
		
		component = getComponentAt(lu.getXCoordinate(),lu.getYCoordinate());
		if (component != null)
			return false;
		
		component = getComponentAt(ru.getXCoordinate(),ru.getYCoordinate());
		if (component != null)
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
