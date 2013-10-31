import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import objects.*;

public class GameView extends JFrame{
  public GraphicManager graphicManager;
  public int currentLevel;
  public static Dimension deskResolution;
  public ASManager asManager;
  public DiscoObjectManager doManager;
  public Container c;
//  public Human[] mensch;
  
  //Ein Feld ist 20x20 px gross
  
  public GameView(ASManager asManager, DiscoObjectManager doManager) {
    c = getContentPane();
    currentLevel=1;
    this.asManager = asManager;
    this.doManager = doManager;
  
    JPanel background = new JPanel();
    background.setBackground(Color.black);
    background.setLayout(null);
    
    asManager.addComponents(background);
    doManager.addComponents(background);
    
    // Temp
    JLabel st = new JLabel(new ImageIcon(doManager.graphicManager.status.getImage()));
    st.setBounds(1366-272, 0, 272, 768);
    background.add(st);
    // Temp Ende
    
    c.add(background);
  }
}