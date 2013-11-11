package main;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import objects.Bar;
import objects.DiscoObject;
import player.*;
import main.DiscoObjectManager;



public class ASManager {
	public AS[] human;
	public GraphicManager graphicManager;
	public Player player;
	public DiscoObjectManager doManager;
	public static GameLogic gameLogic;
	final static int as_cntr = 20;
	
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
				if(human[i].getActivity() == 0 ||  human[i].getActivity() == 4) {
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
					else if(human[i].getEnergy() >= 0.3 && human[i].getFun() < 0.6) {
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
			} else if(human[i].getActivity() == 6) {
				human[i].setDirection((GameLogic.getInstance().player.getDirection()+4)%8);
			}
			else {
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
			// int y = 450;//f.myRandom(60, 700);
			// int x = 1098;//f.myRandom(1100, 1200);
			int y = 290;
			int x = 50;
			human[i] = new AS(((i%2==0)?'m':'w'), ((i%2==0)?(((i%4==0))?graphicManager.man01.getImage():graphicManager.man02.getImage()):(((i%4)==1)?graphicManager.woman01.getImage():graphicManager.woman02.getImage())), BufferedImageLoader.scaleToScreenX(x,false), BufferedImageLoader.scaleToScreenY(y,false),1);
		    human[i].addMouseListener(new ASMouseListener());
		    human[i].setActivityTimer(f.myRandom(400,3000));
		    human[i].setActivity(-1);
		    human[i].setTarget(BufferedImageLoader.scaleToScreenX(f.myRandom(150, 1000),false), BufferedImageLoader.scaleToScreenY(f.myRandom(200, 768),false));
		}
	    for(Human obj : human)
			panel.add(obj);
	}
	
	public void resetComponents() {
		Functions f = new Functions();
		for(int i = 0; i < as_cntr; i++) {
			int y = 290;
			int x = 50;
			human[i].resetAS(((i%2==0)?'m':'w'), ((i%2==0)?(((i%4==0))?graphicManager.man01.getImage():graphicManager.man02.getImage()):(((i%4)==1)?graphicManager.woman01.getImage():graphicManager.woman02.getImage())), BufferedImageLoader.scaleToScreenX(x,false), BufferedImageLoader.scaleToScreenY(y,false),1);
		    human[i].setActivityTimer(f.myRandom(400,3000));
		    human[i].setActivity(-1);
		    human[i].setTarget(BufferedImageLoader.scaleToScreenX(f.myRandom(150, 1000),false), BufferedImageLoader.scaleToScreenY(f.myRandom(200, 768),false));
		}
	}
	
    public Human getComponentAt(int x, int y) {
		JLayeredPane clickedLayeredPane;
		JComponent clickedPanel;
	  	Human clickedObject = null;
	  	  
	  	clickedLayeredPane = GameView.layeredPane;
	  	
	  	if(clickedObject == null) {
	  		clickedPanel = (JComponent)clickedLayeredPane.getComponent(9);
	        try {
	  	  	    clickedObject = (Human)clickedPanel.getComponentAt(x, y);  
	  	  	} catch(Exception e) {
	  	  	    clickedObject = null;
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
	
	public void decreaseStatusForAll(){
		for(int i=0; i<as_cntr; i++){
			human[i].decreaseStatusOverTime();
		}
	}
	
	class ASMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
	    	int xPos;
		  	int yPos;
		  	
		  	final AS clickedObject = (AS)e.getSource();  
		   
		    xPos = clickedObject.getPosition().getX0() + e.getX();
		    yPos = clickedObject.getPosition().getY0() + e.getY();
		    
		    GameLogic.getInstance().gameView.setTarget(GameLogic.getInstance().player,xPos,yPos);

		    
		    clickedObject.setActivity(6);
		    clickedObject.setActivityTimer(2900);
		    clickedObject.setTarget(clickedObject.getPosition().getX0(), clickedObject.getPosition().getY0());
		    System.out.println(clickedObject.getActivity() + " " + clickedObject.getActivityTimer());
		    
		    
		    
		    new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					while(!GameLogic.getInstance().player.doActivity()) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
						i++;
						if (i>200) break;
					}
					GameLogic.getInstance().gameView.flirt.setVisible(true);
					GameLogic.getInstance().gameView.flirt.setAS(clickedObject);
					doManager.canClick = true;
				}
			}).start();
		   
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
	

