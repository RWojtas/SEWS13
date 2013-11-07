package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import main.Menu.MouseAction;
import objects.*;
import player.*;


public class GameView extends JFrame implements MouseListener {
  public ASManager asManager;
  public DiscoObjectManager doManager;
  public Player player;
  public Container c;
  public static Dimension deskResolution;
  public int currentLevel;
  public JPanel background;
  public static JLayeredPane layeredPane;
  public JPanel layer1;
  public JPanel layer2;
  public JPanel layer3;
  public JPanel layer4;
  public JLabel fps;
  public JPanel statusbar;
  
	/*
	 * Direction: Richtung
	 * 0  - unten
	 * 1  - unten/links (diagonal)
	 * 2  - links
	 * 3  - links/oben (diagonal)
	 * 4  - oben
	 * 5  - oben/rechts (diagonal)
	 * 6  - rechts
	 * 7  - rechts/unten (diagonal)
	 */
  
  public GameView(ASManager asManager, DiscoObjectManager doManager, Player player) {
	  deskResolution = Toolkit.getDefaultToolkit().getScreenSize();
	  setSize((int)deskResolution.getWidth(),(int)deskResolution.getHeight()); 
	  c = getContentPane();
	  currentLevel=1;
	  this.asManager = asManager;
      this.doManager = doManager;
      this.player = player;
    
      /*
      background = new JPanel();
      background.setBackground(Color.black);
      background.setSize((int)deskResolution.getWidth(),(int)deskResolution.getHeight()); 
      background.setLayout(null);
      c.add(background);
      */
      
      layeredPane = new JLayeredPane();
      layeredPane.addMouseListener(this);
      
      c.add(layeredPane);

      layer1 = createLayerPanel(); //Höchste Schicht
      layer2 = createLayerPanel(); //Mittlere Schicht
      layer3 = createLayerPanel(); //Mittlere Schicht
      layer4 = createLayerPanel(); //Tiefste Schicht
      
      layeredPane.add(layer1, 0); //Layer für Statusbar
      layeredPane.add(layer2, 1); //Layer für Overlay
      layeredPane.add(layer3, 2); //Layer für Human
      layeredPane.add(layer4, 3); //Layer für DiscoObject
      
      layer3.add(player);
      asManager.addComponents(layer3);
      doManager.addComponents(layer4); 
      
      fps = new JLabel("FPS: 0");
      fps.setOpaque(false);
      fps.setHorizontalAlignment(JLabel.RIGHT);
      fps.setFont(new Font("Dialog",Font.BOLD,30));
      fps.setBounds((int)deskResolution.getWidth()-300-3,(int)deskResolution.getHeight()-30,300,30);
     
      layer1.add(fps);
      
      // Statusbar
      JLabel Titel = new JLabel("Statusbar", JLabel.CENTER);
      Titel.setFont(new Font("Dialog",Font.BOLD,24));
      Titel.setBounds((int)deskResolution.getWidth()-200,0,200,(int)deskResolution.getHeight());
      
	  layer1.add(Titel);  
      // End: Statusbar
    
      // Temp
     // JLabel status = new JLabel(new ImageIcon(doManager.graphicManager.status.getImage()));
      // status.setBounds(BufferedImageLoader.scaleToScreenX(1366-272), 0, 272, 768);

      // layer1.add(status);
  	  
      // Temp Ende    
  }
  
  public JPanel createLayerPanel() {
	  JPanel layer = new JPanel();
	  layer.setLayout(null);
	  layer.setBounds(0,0,(int)deskResolution.getWidth(),(int)deskResolution.getHeight());
	  layer.setOpaque(false);
	  return layer;
  }
  
  public void setTarget(Human human, int x, int y) {
	  Coordinate target = new Coordinate(x,y);
	  Coordinate lo = new Coordinate(x,y);
	  Coordinate ro = new Coordinate(x+human.getWidth(),y);
	  Coordinate lu = new Coordinate(x,y+human.getHeight());;
	  Coordinate ru = new Coordinate(x+human.getWidth(),y+human.getHeight());;
	  
	  if(!GameLogic.getInstance().checkFreePosition(human.hashCode(),lo,ro,lu,ru))
		  target = determineFreeCoordinate(human, new Coordinate(x,y));
	  
	  player.setActivity(1);
	  player.setTarget(target.getXCoordinate(), target.getYCoordinate());  
  }
  
  //Noch in Arbeit
  public Coordinate determineFreeCoordinate(Human human, Coordinate c) {
	  final int MAX_LOOPS = 100;
	  int loopCounter = 0;
	  Coordinate lo;
	  Coordinate ro;
	  Coordinate lu;
	  Coordinate ru;
	  boolean loFree;
	  boolean roFree;
	  boolean luFree;
	  boolean ruFree;
	  
	  do {
		  lo = new Coordinate(c.getXCoordinate(),c.getYCoordinate());
		  ro = new Coordinate(c.getXCoordinate()+human.getWidth(),c.getYCoordinate());
		  lu = new Coordinate(c.getXCoordinate(),c.getYCoordinate()+human.getHeight());
		  ru = new Coordinate(c.getXCoordinate()+human.getWidth(),c.getYCoordinate()+human.getHeight());
		  
		  loFree = GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), lo);
		  roFree = GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), ro);
		  luFree = GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), lu);
		  ruFree = GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), ru);
				 
		  if(!loFree) {
			  System.out.println("!loFree");
			  if(luFree && roFree){
				if(human.getPosition().getX3() < lo.getXCoordinate()) {
					//dir u
					c = shiftTarget(0, c);
				} else {
					if(human.getPosition().getY3() < lo.getYCoordinate()) {
						//dir r
						c = shiftTarget(6, c);
					} else {
						//dir ur
						c = shiftTarget(7, c);
					}
				}	
			  } else {
				  if(luFree) {
					  //dir r
					  c = shiftTarget(6, c);
				  } else {
					  //dir u
					  c = shiftTarget(0, c);
				  }
			  }
		  } 
		  
		  if(!roFree) {
			  System.out.println("!roFree");
			  if(loFree && ruFree){
			      if(human.getPosition().getX2() > ro.getXCoordinate()) {
					  //dir u
			    	  c = shiftTarget(0, c);
				  } else {
					  if(human.getPosition().getY2() < ro.getYCoordinate()) {
						  //dir l
						  c = shiftTarget(2, c);
					  } else {
						  //dir ul
						  c = shiftTarget(1, c);
					  }
				  }
			   } else {
				   if(loFree) {
					   //dir l
					   c = shiftTarget(2, c);
				   } else {
					   //dir u
					   c = shiftTarget(0, c);
				   }
			   }
		  } 
		  
		  if(!luFree) {
			  System.out.println("!luFree");
			  if(loFree && ruFree){
			      if(human.getPosition().getX1() < lu.getXCoordinate()) {
					  //dir o
			    	  c = shiftTarget(4, c);
				  } else {
					  if(human.getPosition().getY1() > lu.getYCoordinate()) {
						  //dir r
						  c = shiftTarget(6, c);
					  } else{
						  //dir ro
						  c = shiftTarget(5, c);
					  }
				  }
				  
			   } else {
				   if(loFree) {
					   //dir o
					   c = shiftTarget(4, c);
				   } else {
					   //dir r
					   c = shiftTarget(6, c);
				   }
			   }
		  } 
		  
		  if(!ruFree) {
			  System.out.println("!ruFree");
			  if(luFree && roFree){
				  System.out.println("luFree && roFree");
			      if(human.getPosition().getX0() > ru.getXCoordinate()) {
			    	  System.out.println("human.getPosition().getX0() > ru.getXCoordinate()");
			    	  //dir o
			    	  c = shiftTarget(4, c);
			    	  System.out.println("Coordinate: "+c.getXCoordinate()+"|"+c.getYCoordinate());
				  } else {
					  if(human.getPosition().getY0() > ru.getYCoordinate()) {
						  System.out.println("human.getPosition().getY0() > ru.getYCoordinate()");
						  //dir l
						  c = shiftTarget(2, c);
					  } else {
						  System.out.println("diagonal");
						  //dir ol
						  c = shiftTarget(3, c);
					  }  
				  }
			   } else {
				   if(luFree) {
					   System.out.println("luFree");
					   //dir l
					   c = shiftTarget(2, c);
				   } else {
					   System.out.println("roFree");
					   //dir o
					   c = shiftTarget(4, c);
				   }
			   }
		  }
		  loopCounter++;
	  } while((c.getXCoordinate() != lo.getXCoordinate() || c.getYCoordinate() != lo.getYCoordinate()) && loopCounter <= MAX_LOOPS);  
	  return c;
  }
  
  public Coordinate shiftTarget(int direction, Coordinate c) {
	  int shiftWide = 1;
	  
	  switch(direction) {
	  case 0: return new Coordinate(c.getXCoordinate()			,c.getYCoordinate()+shiftWide);
	  case 1: return new Coordinate(c.getXCoordinate()-shiftWide,c.getYCoordinate()+shiftWide);
	  case 2: return new Coordinate(c.getXCoordinate()-shiftWide,c.getYCoordinate());
	  case 3: return new Coordinate(c.getXCoordinate()-shiftWide,c.getYCoordinate()-shiftWide);
	  case 4: return new Coordinate(c.getXCoordinate()			,c.getYCoordinate()-shiftWide);
	  case 5: return new Coordinate(c.getXCoordinate()+shiftWide,c.getYCoordinate()-shiftWide);
	  case 6: return new Coordinate(c.getXCoordinate()+shiftWide,c.getYCoordinate());
	  case 7: return new Coordinate(c.getXCoordinate()+shiftWide,c.getYCoordinate()+shiftWide);
	  default: return c;
	  }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  	  System.out.println("mouseClicked!");
  	  //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
  	  //ohne mit gedrückter Maustaste die Position der Maus zu verändern
  	  
  	  setTarget(player,e.getX()-player.getWidth()/2,e.getY()-player.getHeight()/2); 
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
