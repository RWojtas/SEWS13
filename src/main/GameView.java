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
  public JLabel fps;
  public JPanel statusbar;
  
  
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

      layer1 = createLayerPanel();
      layer2 = createLayerPanel();
      layer3 = createLayerPanel();
      
      layeredPane.add(layer1, 0); 
      layeredPane.add(layer2, 1); 
      layeredPane.add(layer3, 2);
      
      layer2.add(player);
      asManager.addComponents(layer2);
      doManager.addComponents(layer3); 
      
      fps = new JLabel("0");
      fps.setOpaque(false);
      fps.setHorizontalAlignment(JLabel.RIGHT);
      fps.setFont(new Font("Dialog",Font.BOLD,30));
      fps.setBounds((int)deskResolution.getWidth()-300-3,(int)deskResolution.getHeight()-30,300,30);
     
      layer3.add(fps);
      
      // Statusbar
      statusbar = createLayerPanelStatusbar();
      layeredPane.add(statusbar, 3);
      
      GraphicManager graphicManager = new GraphicManager();
      JLabel Titel = new JLabel("Statusbar", JLabel.CENTER);
      Titel.setFont(new Font("Dialog",Font.BOLD,24));
      Titel.setBounds(0,0,200,100);
      
	  statusbar.add(Titel);  
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
   
  public JPanel createLayerPanelStatusbar() {
	  JPanel layer = new JPanel();
	  layer.setLayout(null);
	  layer.setBounds((int)deskResolution.getWidth()-200,0,200,(int)deskResolution.getHeight());
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
	  
  }

  @Override
  public void mouseReleased(MouseEvent e) {
	  //Wird ausgelöst, nachdem man einen Klick mit der Maus wieder loslässt
	  
  }
}
