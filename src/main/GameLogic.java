package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import music.MusicManager;
import player.*;


public class GameLogic {
  public static GameLogic gameLogic;
  public static GameView gameView;
  public static GraphicManager graphicManager;
  public static ASManager asManager;
  public static DiscoObjectManager doManager;
  public static MusicManager musicManager;

  public static GameLogic getInstance() {
	  if(gameLogic == null) {
		  gameLogic = new GameLogic();
	  }
	  return gameLogic;
  }
  
  public void start() {
	  long frames = 0;
	  long lastTimeChecked = System.nanoTime();
	  
	  while(true) {
		  asManager.human[0].stepNextPosition();
		  frames++;
	      if(System.nanoTime()-lastTimeChecked >= 1000000000) {
	    	  gameView.fps.setText(""+frames);
	    	  frames = 0;
	    	  lastTimeChecked = System.nanoTime();
	      }
	  }
  }
  
  private GameLogic() {
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
    //gameView.addMouseListener(new GameViewMouseListener());
    //gameView.add(musicManager.getPanel());	//add music panel
    
    //musicManager.play(); //play music
    
  }
  
  public boolean checkFreePosition(Coordinate lo, Coordinate ro, Coordinate lu, Coordinate ru) {
	  
	  if(!asManager.checkFreePosition(lo,ro,lu,ru)) 
		  return false;
	  if(!doManager.checkFreePosition(lo,ro,lu,ru)) 
		  return false;
	  
	  return true;
  }

  public static void main (String [] args) {
      GameLogic.getInstance().start();
  }

}
