import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gameview extends JPanel{
  public Graphicmanager graphicmanager;
  public int currentLevel;
  public static Dimension deskResolution;
//  public DiscoObject[] discoObject;
//  public Mensch[] mensch;
  
  //Ein Feld ist 20x20 px groß
  
  public Gameview(Graphicmanager graphicmanager) {
    this.graphicmanager = graphicmanager;
    setBackground(Color.black);
  
    currentLevel=1;
    
//    //hier werden die discoObject Objekte erstellt
//    discoObject = new DiscoObject[1];
//    discoObject[0] = new Bar();
//    
//    //hier werden die Mensch Objekte erstellt.
//    mensch = new Mensch[1];
//    mensch[0] = new NPC('w', 1);    
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    updateField(g);
    drawField(g);
  }
  
  public void updateField(Graphics g) {
	  
  }
  
  public void drawField(Graphics g) {
    g.setColor(Color.white);
//    //In der Folgenden Codezeile wird die Graphic vom Graphicmanager 
//    //geladen und an die Stelle x=20 und y=30 gezeichnet.
//    g.drawImage(graphicmanager.humanGraphic.getImage(),20,30,null);
  }
}