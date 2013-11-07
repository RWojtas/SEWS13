package main;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import objects.DiscoObject;
import player.*;



public class ASManager {
	public AS[] human;
	public GraphicManager graphicManager;
	public DiscoObjectManager doManager;
	final static int as_cntr = 10;
	
	public ASManager(GraphicManager graphicManager, DiscoObjectManager doManager) {
		this.graphicManager = graphicManager;
		this.doManager = doManager;
	}
	
	
	public void updateComponents() {
		Functions f = new Functions();
		for(int i=0;i<human.length;i++) {
			if(human[i].getActivityTimer() == 0) {
				if(human[i].getActivity() == -1) {
					human[i].setActivity(1);
				}
				else if(human[i].getActivity() == 1 && human[i].doActivity()) {
					human[i].setActivity(0);
				}
				if(human[i].getActivity() == 0 || human[i].getActivity() == 2 || human[i].getActivity() == 4) {
					if(human[i].getUrine() > 0.9) {
						human[i].setActivityWithPos(5, doManager);
					}
					else if(human[i].getEnergy() < 0.3) {
						int c = f.myRandom(0, 1);
						if(c==0) {
							human[i].setActivityWithPos(12, doManager);
						} else { 
							human[i].setActivityWithPos(9, doManager);
						}
					}
					else if(human[i].getEnergy() > 0.5 && human[i].getFun() < 0.4) {
						human[i].setActivityWithPos(2, doManager);
					}
					else {
						int[] Liste = new int[5];
						for(int g = 0; g < Liste.length; g++) {
							Liste[g] = g;
						}		
						int d = f.myRandom(0, 5);
						if(Liste[d] == 3){
							human[i].setActivityWithPos(f.myRandom(11, 19), doManager);
						}
						else if(Liste[d] == 5) {
							human[i].setActivityWithPos(9, doManager);
						}
						else {
							human[i].setActivityWithPos(Liste[d], doManager);
						}
					}
				}
			} else {
				if(human[i].doActivity() || human[i].getActivity() == -1 ) {
					human[i].decActivityTimer();
					//  TO-DO  Aktion muss ausgef�hrt werden
				}
			}
			human[i].stepNextPosition();
		//	System.out.println("human["+i+"].getActivity()="+human[i].getActivity() + " Timer: " + human[i].getActivityTimer());
		}	
		
	}
	
	public void addComponents(JPanel panel) {
		Functions f = new Functions();
		human = new AS[as_cntr];
		for(int i = 0; i < as_cntr; i++) {
			int y = 450;//f.myRandom(60, 700);
			int x = 1098;//f.myRandom(1100, 1200);
			human[i] = new AS(((i%2==0)?'m':'w'), ((i%2==0)?(((i%4==0))?graphicManager.man01.getImage():graphicManager.man02.getImage()):(((i%4)==1)?graphicManager.woman01.getImage():graphicManager.woman02.getImage())), BufferedImageLoader.scaleToScreenX(x), BufferedImageLoader.scaleToScreenY(y),1);
		    human[i].addMouseListener(new ASMouseListener());
		    human[i].setActivityTimer(f.myRandom(400,3000));
		    human[i].setActivity(-1);
		    human[i].setTarget(BufferedImageLoader.scaleToScreenX(f.myRandom(150, 1000)), BufferedImageLoader.scaleToScreenY(f.myRandom(200, 768)));
		}
	    for(Human obj : human)
			panel.add(obj);

	}
	
    public Human getComponentAt(int x, int y) {
		JLayeredPane clickedLayeredPane;
		JComponent clickedPanel;
	  	Human clickedObject = null;
	  	  
	  	clickedLayeredPane = GameView.layeredPane;
	  	
	  	for(int i=0;i<clickedLayeredPane.getComponentCount();i++) {
	  		if(clickedObject == null) {
		  		clickedPanel = (JComponent)clickedLayeredPane.getComponent(i);
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
