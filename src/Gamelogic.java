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
    gameview.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
    
    frame = new JFrame();
    frame.setTitle("Felse deine Feier");
    frame.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
    frame.setUndecorated(true);
    frame.setAlwaysOnTop(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    
    //frame.add(musicmanager.getPanel());	//add music panel
    
    frame.add(gameview);
    
    //musicmanager.play(); //play music
  }

  public static void main (String [] args) {
    new Gamelogic();
  }
}