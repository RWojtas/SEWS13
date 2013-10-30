import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objects.*;

public class Gameview extends JFrame{
  public Graphicmanager graphicmanager;
  public int currentLevel;
  public static Dimension deskResolution;
  public DiscoObject[] discoObject;
  public Container c;
//  public Mensch[] mensch;
  
  //Ein Feld ist 20x20 px gross
  
  public Gameview(Graphicmanager graphicmanager) {
    this.graphicmanager = graphicmanager;
    setBackground(Color.CYAN);
    c = getContentPane();
  
    JPanel background = new JPanel();
    background.setBackground(Color.black);
    background.setLayout(null);
    c.add(background);
    
    currentLevel=1;
    
    //hier werden die discoObject Objekte erstellt
    discoObject = new DiscoObject[1];
    discoObject[0] = new Bar(graphicmanager.bg.getImage(),0,0);
    
    background.add(discoObject[0]);
    
    //hier werden die Mensch Objekte erstellt.
//    mensch = new Mensch[1];
//    mensch[0] = new NPC('w', 1);    
  }
}