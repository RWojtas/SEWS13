import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import objects.*;

public class GameView extends JFrame{
  public GraphicManager graphicManager;
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
      status.setBounds(DiscoObjectManager.scaleToScreenX(1366-272), 0, DiscoObjectManager.scaleToScreenX(272), DiscoObjectManager.scaleToScreenY(768));
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
}