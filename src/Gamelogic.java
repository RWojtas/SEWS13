import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gamelogic {
  public Gameview gameview;
  public Graphicmanager graphicmanager;
  public JFrame frame;
  
  public Gamelogic() {
    graphicmanager = new Graphicmanager();
    
    gameview = new Gameview(graphicmanager);
    
    frame = new JFrame("Felse deine Feier");
    frame.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
    frame.setUndecorated(true);
    frame.setAlwaysOnTop(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.add(gameview);
  }

  public static void main (String [] args) {
    new Gamelogic();
  }
}