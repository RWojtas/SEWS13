package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import objects.*;


public class GameView extends JFrame implements MouseListener {
  public ASManager asManager;
  public DiscoObjectManager doManager;
  public Container c;
  public static Dimension deskResolution;
  public int currentLevel;
  public JPanel background;
  public JLayeredPane layeredPane;
  public JPanel layer1;
  public JPanel layer2;
  public JPanel layer3;
  
  public GameView(ASManager asManager, DiscoObjectManager doManager) {
	  deskResolution = Toolkit.getDefaultToolkit().getScreenSize();
	  setSize((int)deskResolution.getWidth(),(int)deskResolution.getHeight()); 
	  c = getContentPane();
	  currentLevel=1;
	  this.asManager = asManager;
      this.doManager = doManager;
    
      /*
      background = new JPanel();
      background.setBackground(Color.black);
      background.setSize((int)deskResolution.getWidth(),(int)deskResolution.getHeight()); 
      background.setLayout(null);
      c.add(background);
      */
      
      layeredPane = new JLayeredPane();
      layeredPane.addMouseListener(this);

      layer1 = createLayerPanel();
      layer2 = createLayerPanel();
      layer3 = createLayerPanel();
      
      layeredPane.add(layer1, 0); 
      layeredPane.add(layer2, 1); 
      layeredPane.add(layer3, 2);
    
      c.add(layeredPane);
      
      asManager.addComponents(layer2);
      doManager.addComponents(layer3); 
    
      // Temp
      JLabel status = new JLabel(new ImageIcon(doManager.graphicManager.status.getImage()));
      status.setBounds(BufferedImageLoader.scaleToScreenX(1366-272), 0, 272, 768);
  
      //Graphics2D g2d = (Graphics2D)status.getGraphics();
      //g2d.rotate(Math.toRadians(45), 272,768);

      layer1.add(status);
      // Temp Ende    
  }
  
  public JPanel createLayerPanel() {
	  JPanel layer = new JPanel();
	  layer.setLayout(null);
	  layer.setBounds(0,0,(int)deskResolution.getWidth(),(int)deskResolution.getHeight());
	  layer.setOpaque(false);
	  return layer;
  }
  
  //Eine Methode die dazu verwendet werden kann, einen globalen MouseListener zu bauen
  //Gibt das oberste Objekt zurück, das angeklickt wurde.
  public DiscoObject getClickedObject(int x, int y) {
	  JPanel clickedPanel;
  	  DiscoObject clickedObject = null;
  	  
  	  clickedPanel = (JPanel)layeredPane.getComponent(0);
  	  try {
  		clickedObject = (DiscoObject)clickedPanel.getComponentAt(x, y);  
  	  } catch(Exception e) {
  		clickedObject = null;
  	  }
  	  
  	  if(clickedObject == null) {
  		clickedPanel = (JPanel)layeredPane.getComponent(1);
  		try {
  	  	  clickedObject = (DiscoObject)clickedPanel.getComponentAt(x, y);  
  	  	} catch(Exception e) {
  	  	  clickedObject = null;
  	  	}
  		
  	  }
  	  		
  	  if(clickedObject == null) {
  	    clickedPanel = (JPanel)layeredPane.getComponent(2);
  	    try {
  		  clickedObject = (DiscoObject)clickedPanel.getComponentAt(x, y);  
  		} catch(Exception e) {
  		 clickedObject = null;
  		}
  	  }
  	  return clickedObject;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  	  System.out.println("mouseClicked!");
  	  //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
  	  //ohne mit gedrückter Maustaste die Position der Maus zu verändern


  	  /* TODO Raffel & Sebastian
  	   * Wenn (Position ist Frei?)
  	   * Dann (starte Wegfindungsalgorithmus)
  	   * Ansonsten (tuhe nichts/Gib stör Signal aus).
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
