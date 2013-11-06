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
  public JLabel test;
  
  
  public GameView(ASManager asManager, DiscoObjectManager doManager, Player player) {
	  deskResolution = new Dimension(500,10000);
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
      
      fps = new JLabel("0");
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
	  
	  test = new JLabel();
	  test.setOpaque(true);
	  test.setBackground(Color.black);
	  test.setVisible(false);
	  layer1.add(test);
  }
  
  public JPanel createLayerPanel() {
	  JPanel layer = new JPanel();
	  layer.setLayout(null);
	  layer.setBounds(0,0,(int)deskResolution.getWidth(),(int)deskResolution.getHeight());
	  layer.setOpaque(false);
	  return layer;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  	  System.out.println("mouseClicked!");
  	  //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
  	  //ohne mit gedrückter Maustaste die Position der Maus zu verändern
  	  
  	  player.setActivity(1);
  	  player.setTarget(e.getX()-player.getWidth()/2,e.getY()-player.getHeight()/2);
  	  
  	  
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
      int qubeSize = 50; 
  	  
  	  Coordinate lo = new Coordinate(e.getX(),e.getY());
  	  Coordinate ro = new Coordinate(e.getX(),e.getY()+qubeSize);;
  	  Coordinate lu = new Coordinate(e.getX()+qubeSize,e.getY());;
  	  Coordinate ru = new Coordinate(e.getX()+qubeSize,e.getY()+qubeSize);;
  	  test.setBounds(e.getX(),e.getY(),qubeSize,qubeSize);
  	  test.setVisible(true);
  	  System.out.println(GameLogic.getInstance().checkFreePosition(lo, ro, lu, ru));
  }

  @Override
  public void mouseReleased(MouseEvent e) {
	  //Wird ausgelöst, nachdem man einen Klick mit der Maus wieder loslässt
	  test.setVisible(false);
  }
}
