package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import objects.*;
import objects.Position;
import player.*;




public class DiscoObjectManager {
	public DiscoObject[] discoObject;
	public GraphicManager graphicManager;
	public static Dimension deskResolution;
	public Player player;
	public Bar bar;
	GameLogic gameLogic;
	public boolean canClick = true;
	
	public DiscoObjectManager(GraphicManager graphicManager, GameLogic gameLogic, Player player) {
		deskResolution = Toolkit.getDefaultToolkit().getScreenSize();
		this.graphicManager = graphicManager;
		this.player = player;
		this.gameLogic = gameLogic;
	}
	
	public void updateComponents() {
		
	}
	
	public void addComponents(JPanel panel) {
		
	    discoObject = new DiscoObject[14];
	    discoObject[0] = new Bar(graphicManager.bar.getImage(),BufferedImageLoader.scaleToScreenX(0,false),BufferedImageLoader.scaleToScreenY(0,false));
	    discoObject[0].addMouseListener(new BarMouseListener());
	    discoObject[1] = new Toilet(graphicManager.wc.getImage(),BufferedImageLoader.scaleToScreenX(920-20,false),BufferedImageLoader.scaleToScreenY(475-30,false));
	    discoObject[1].addMouseListener(new ToiletMouseListener());
	    discoObject[2] = new DJ(graphicManager.dj.getImage(),BufferedImageLoader.scaleToScreenX(0,false),BufferedImageLoader.scaleToScreenY(410,false));
	    discoObject[2].addMouseListener(new DJMouseListener());
	    discoObject[3] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(518,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[3].addMouseListener(new TableMouseListener());
	    discoObject[4] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(730,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[4].addMouseListener(new TableMouseListener());
	    discoObject[5] = new Table(graphicManager.table.getImage(),BufferedImageLoader.scaleToScreenX(940,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[5].addMouseListener(new TableMouseListener());
	    discoObject[6] = new Dancefloor(graphicManager.dancefloor.getImage(),BufferedImageLoader.scaleToScreenX(200,false),BufferedImageLoader.scaleToScreenY(400,false));
	    discoObject[6].addMouseListener(new DancefloorMouseListener());
	    discoObject[7] = new Bench(graphicManager.bench.getImage(),BufferedImageLoader.scaleToScreenX(480,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[7].addMouseListener(new BenchMouseListener());
	    discoObject[8] = new Bench(graphicManager.bench.getImage(),BufferedImageLoader.scaleToScreenX(616,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[8].addMouseListener(new BenchMouseListener());
	    discoObject[9] = new Bench(graphicManager.bench.getImage(),BufferedImageLoader.scaleToScreenX(692,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[9].addMouseListener(new BenchMouseListener());
	    discoObject[10] = new Bench(graphicManager.bench.getImage(),BufferedImageLoader.scaleToScreenX(828,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[10].addMouseListener(new BenchMouseListener());
	    discoObject[11] = new Bench(graphicManager.bench.getImage(),BufferedImageLoader.scaleToScreenX(902,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[11].addMouseListener(new BenchMouseListener());
	    discoObject[12] = new Bench(graphicManager.bench.getImage(),BufferedImageLoader.scaleToScreenX(1038,false),BufferedImageLoader.scaleToScreenY(23,false));
	    discoObject[12].addMouseListener(new BenchMouseListener());
	    discoObject[13] = new Carpet(null, graphicManager.carpet.getImage(),BufferedImageLoader.scaleToScreenX(0,false),BufferedImageLoader.scaleToScreenY(250,false));
	    
	    for(DiscoObject obj : discoObject)
			panel.add(obj);
	}
	
	public DiscoObject getComponentAt(int x, int y) {
		JLayeredPane clickedLayeredPane;
		JComponent clickedPanel;
	  	DiscoObject clickedObject = null;
	  	  
	  	clickedLayeredPane = GameView.layeredPane;
	  	
	  	for(int i=0;i<clickedLayeredPane.getComponentCount();i++) {
	  		if(clickedObject == null) {
		  		clickedPanel = (JComponent)clickedLayeredPane.getComponent(i);
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
	    	if (!canClick) return;
	    	canClick = false;
	    	
		    System.out.println("mouseClicked!!!");
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		  	int xPos;
		  	int yPos;
		    
		    Bar clickedObject = (Bar)e.getSource();
		   
		    xPos = clickedObject.getPositionX() + e.getX();
		    yPos = clickedObject.getPositionY() + e.getY();
		    
		    GameLogic.getInstance().gameView.setTarget(GameLogic.getInstance().player,xPos, yPos);
		    
		    //bar.openOverlay=true;
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
		     */
		    final int x = clickedObject.getX()+clickedObject.getWidth()/2;
		    final int y = clickedObject.getY()+clickedObject.getHeight()+10;
		    
		    gameLogic.gameView.setTarget(player,x,y);
		    
		    new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					while(!player.doActivity()) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
						i++;
						if (i>200) break;
					}
					gameLogic.gameView.bar.setVisible(true);
					canClick = true;
				}
			}).start();
		}

	    @Override
		public void mouseEntered(MouseEvent e) {
	    	System.out.println("Bar mouseClicked!!!");
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
	    	if (!canClick) return;
		    System.out.println("mouseClickedBench");
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		  	
		    Bench clickedObject = (Bench)e.getSource();
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
		     */
		    final int x = clickedObject.getX()+clickedObject.getWidth()+10;
		    final int y = clickedObject.getY()+clickedObject.getHeight()/2;
		    
		    
		    gameLogic.gameView.setTarget(player,x,y);
		    new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					while(!player.doActivity()) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
						i++;
						if (i>200) break;
					}
					gameLogic.gameView.bench.setVisible(true);
					canClick = true;
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
	
	class DancefloorMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	if (!canClick) return;
		    System.out.println("mouseClicked Dancefloor");
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    
		    Dancefloor clickedObject = (Dancefloor)e.getSource();
		    //gameLogic.gameView.setTarget(player,clickedObject.getX()+e.getX(),clickedObject.getY()+e.getY()); 
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
		     */
		    
		    final int x = clickedObject.getX()+e.getX();
		    final int y = clickedObject.getY()+e.getY();
		    
		    
		    gameLogic.gameView.setTarget(player,x,y);
		    new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					while(!player.doActivity()) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
						i++;
						if (i>200) break;
					}
					gameLogic.gameView.dancefloor.setVisible(true);
					canClick = true;
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
	
	class DJMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	if (!canClick) return;
	    	canClick = false;
		    System.out.println("mouseClicked");
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    
		    DJ clickedObject = (DJ)e.getSource();
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
		     */
		    final int x = clickedObject.getX()+clickedObject.getWidth()+10;
		    final int y = clickedObject.getY()+clickedObject.getHeight()/2;
		    
		    
		    gameLogic.gameView.setTarget(player,x,y);
		    new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					while(!player.doActivity()) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
						i++;
						if (i>200) break;
					}
					gameLogic.gameView.dj.setVisible(true);
					canClick = true;
				}
			}).start();
		    canClick = true;
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
	    	if (!canClick) return;
		    System.out.println("mouseClicked");
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    int xPos;
		    int yPos;
		    
		    Table clickedObject = (Table)e.getSource();   
		    
		    xPos = clickedObject.getPositionX() + e.getX();
		    yPos = clickedObject.getPositionY() + e.getY();
		    
		    GameLogic.getInstance().gameView.setTarget(GameLogic.getInstance().player,xPos, yPos);
		    
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
	
	class ToiletMouseListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    if (!canClick) return;
	    	canClick = false;
		    System.out.println("mouseClicked");
		    //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
		    //ohne mit gedrückter Maustaste die Position der Maus zu verändern
		    
		    Toilet clickedObject = (Toilet)e.getSource();
		    
		    /* TODO
		  	 * Entsprechender Overlayaufruf bzw. Aktion
		     */
		    final int x = clickedObject.getX()-10;
		    final int y = clickedObject.getY()+100;
		    
		    
		    gameLogic.gameView.setTarget(player,x,y);
		    new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					while(!player.doActivity()) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
						i++;
						if (i>200) break;
					}
					gameLogic.gameView.toilet.setVisible(true);
					canClick = true;
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
