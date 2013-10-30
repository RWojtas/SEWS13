import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import edu.dhbw.pse.music.MusicManager;

public class Gamelogic {
  public Gameview gameview;
  public Graphicmanager graphicmanager;
  public JFrame frame;
  public MusicManager musicmanager;
  
  public Gamelogic() {
    graphicmanager = new Graphicmanager();
    
    //musicmanager = new MusicManager();	// music manger
    
    gameview = new Gameview(graphicmanager);
    gameview.setTitle("Felse deine Feier");
    gameview.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
    gameview.setUndecorated(true);
    gameview.setAlwaysOnTop(true);
    gameview.setResizable(false);
    gameview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameview.setVisible(true);
    
    //frame.add(musicmanager.getPanel());	//add music panel
    
    //musicmanager.play(); //play music
  }

  public static void main (String [] args) {
    new Gamelogic();
  }
}