import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import objects.Bar;
import objects.DiscoObject;
import edu.dhbw.pse.music.MusicManager;

public class GameLogic {
  public GameView gameView;
  public GraphicManager graphicManager;
  public ASManager asManager;
  public DiscoObjectManager doManager;
  public MusicManager musicManager;
  
  
  public GameLogic() {
    graphicManager = new GraphicManager();
    asManager = new ASManager(graphicManager);
    doManager = new DiscoObjectManager(graphicManager);
    //musicManager = new MusicManager();	// music manger
    
    gameView = new GameView(asManager, doManager);
    gameView.setTitle("Felse deine Feier");
    gameView.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
    gameView.setUndecorated(true);
    gameView.setAlwaysOnTop(true);
    gameView.setResizable(false);
    gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameView.setVisible(true);
    
    //frame.add(musicManager.getPanel());	//add music panel
    
    //musicManager.play(); //play music
  }

  public static void main (String [] args) {
    new GameLogic();
  }
}