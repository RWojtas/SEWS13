import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import music.MusicManager;
import player.*;

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
    gameView.setUndecorated(true);
    gameView.setAlwaysOnTop(true);
    gameView.setResizable(false);
    gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameView.setVisible(true);
    gameView.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(graphicManager.mouse.getImage(), new Point(gameView.getX(), gameView.getY()), "mouse02"));
    
    //gameView.add(musicManager.getPanel());	//add music panel
    
    //musicManager.play(); //play music
  }
  
  public boolean checkFreePosition(Position ul, Position ur, Position dl, Position dr) {
	  
	  if(!asManager.checkFreePosition(ul, ur, dl, dr)) 
		  return false;
	  if(!doManager.checkFreePosition(ul, ur, dl, dr)) 
		  return false;
	  
	  return true;
  }

  public static void main (String [] args) {
    new GameLogic();
  }
}