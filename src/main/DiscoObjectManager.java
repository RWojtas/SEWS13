package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import objects.*;
import player.Coordinate;
import player.Human;
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
		
	    discoObject = new DiscoObject[7];
	    discoObject[0] = new Bar(graphicManager.bar.getImage(),BufferedImageLoader.scaleToScreenX(0),BufferedImageLoader.scaleToScreenY(-18));
	    discoObject[0].addMouseListener(new BarMouseListener());
	    discoObject[1] = new Toilet(graphicManager.wc.getImage(),BufferedImageLoader.scaleToScreenX(12),BufferedImageLoader.scaleToScreenY(270));
	    discoObject[1].addMouseListener(new ToiletMouseListener());
	    discoObject[2] = new DJ(graphicManager.dj.getImage(),BufferedImageLoader.scaleToScreenX(0),BufferedImageLoader.scaleToScreenY(428));
	    discoObject[2].addMouseListener(new DJMouseListener());
	    discoObject[3] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(480),BufferedImageLoader.scaleToScreenY(23));
	    discoObject[3].addMouseListener(new TableMouseListener());
	    discoObject[4] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(692),BufferedImageLoader.scaleToScreenY(23));
	    discoObject[4].addMouseListener(new TableMouseListener());
	    discoObject[5] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(902),BufferedImageLoader.scaleToScreenY(23));
	    discoObject[5].addMouseListener(new TableMouseListener());
	    discoObject[6] = new Dancefloor(graphicManager.dancefloor.getImage(),BufferedImageLoader.scaleToScreenX(274),BufferedImageLoader.scaleToScreenY(768-330));
	    discoObject[6].addMouseListener(new DancefloorMouseListener());
	    
	    for(DiscoObject obj : discoObject)
			panel.add(obj);
	}
	
	 public DiscoObject getComponentAt(int x, int y) {
		JLayeredPane clickedLayeredPane;
		JPanel clickedPanel;
	  	DiscoObject clickedObject = null;
	  	  
	  	clickedLayeredPane = GameView.layeredPane;
	  	
	  	for(int i=0;i<clickedLayeredPane.getComponentCount();i++) {
	  		if(clickedObject == null) {
		  		clickedPanel = (JPanel)clickedLayeredPane.getComponent(i);
		        try {
		  	  	    clickedObject = (DiscoObject)clickedPanel.getComponentAt(x, y);  
		  	  	} catch(Exception e) {
		  	  	    clickedObject = null;
		  	  	}	
		  	}
	  	}

	  	return clickedObject;
	}
	 
    public boolean checkFreeCoordinate(Coordinate coordinate) {
    	DiscoObject component = getComponentAt(coordinate.getXCoordinate(),coordinate.getYCoordinate());
		if (component != null && !component.getAccessible())
			return false;
		else
    		return true;
    }
	
	public boolean checkFreePosition(Coordinate lo, Coordinate ro, Coordinate lu, Coordinate ru) {		
		if(!checkFreeCoordinate(lo))
			return false;
		if(!checkFreeCoordinate(ro))
			return false;
		if(!checkFreeCoordinate(lu))
			return false;
		if(!checkFreeCoordinate(ru))
			return false;
		
		return true;
	}
	
	class BarMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    System.out.println("mouseClicked");
		    discoObject[0].openOverlay = true;
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		  	
		    Bar clickedObject = (Bar)e.getSource();
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
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
	
	class BenchMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    System.out.println("mouseClicked");
		    discoObject[7].openOverlay = true;
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		  	
		    Bench clickedObject = (Bench)e.getSource();
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
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
	
	class DancefloorMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    System.out.println("mouseClicked Dancefloor");
		    discoObject[6].openOverlay = true;
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    
		    Dancefloor clickedObject = (Dancefloor)e.getSource();
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
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
	
	class DJMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    System.out.println("mouseClicked");
		    discoObject[2].openOverlay = true;
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    
		    DJ clickedObject = (DJ)e.getSource();
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
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
	
	class TableMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    System.out.println("mouseClicked");
		    discoObject[0].openOverlay = true;
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    
		    Table clickedObject = (Table)e.getSource();   
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion*/
		     
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
	
	class ToiletMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    System.out.println("mouseClicked");
		    discoObject[1].openOverlay = true;
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    
		    Toilet clickedObject = (Toilet)e.getSource();
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
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
