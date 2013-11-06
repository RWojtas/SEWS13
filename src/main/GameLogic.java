package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import music.MusicManager;
import player.*;


public class GameLogic implements KeyListener {
  public static GameLogic gameLogic;
  public static GameView gameView;
  public static GraphicManager graphicManager;
  public static ASManager asManager;
  public static DiscoObjectManager doManager;
  public static MusicManager musicManager;
  public static final long UPDATE_TIME_INTERVALL = 5000000;
  public static final long ONE_SECOND = 1000000000; 
  public Player player;

  public static GameLogic getInstance() {
	  if(gameLogic == null) {
		  gameLogic = new GameLogic();
	  }
	  return gameLogic;
  }
  
  public void start() {
	  long frames = 0;
	  long framesPerSecondTimer = System.nanoTime();
	  long updateTimer = System.nanoTime(); 
	  
	  while(true) {
		  
		  //Updates
		  if((System.nanoTime()-updateTimer) >= UPDATE_TIME_INTERVALL) {
			  asManager.updateComponents();
			  player.stepNextPosition();  
			  frames++;
			  updateTimer += UPDATE_TIME_INTERVALL;
		  }
		  
		  //FPS Berechnung 
	      if(System.nanoTime()-framesPerSecondTimer >= ONE_SECOND) {
	    	  gameView.fps.setText(""+frames);
	    	  frames = 0;
	    	  framesPerSecondTimer = System.nanoTime();
	      }
	  }
  }
  
  private GameLogic() {
    graphicManager = new GraphicManager();
    asManager = new ASManager(graphicManager);
    doManager = new DiscoObjectManager(graphicManager);
    player = new Player(100,'m', graphicManager.human.getImage(), BufferedImageLoader.scaleToScreenX(500), BufferedImageLoader.scaleToScreenY(400),0,0,1);
    //musicManager = new MusicManager();	// music manger
    
    gameView = new GameView(asManager, doManager, player);
    gameView.setTitle("Felse deine Feier");
    gameView.setUndecorated(true);
    gameView.setAlwaysOnTop(true);
    gameView.setResizable(false);
    gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameView.setVisible(true);
    gameView.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(graphicManager.mouse.getImage(), new Point(gameView.getX(), gameView.getY()), "mouse02"));
    gameView.addKeyListener(this);
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

  @Override
  public void keyPressed(KeyEvent arg0) {
	  if(arg0.getKeyCode() == 27) {
		  gameView.setVisible(false);
		  gameView.dispose();
	      System.exit(0);
	  };
  }

  @Override
  public void keyReleased(KeyEvent arg0) {
	
  }

  @Override
  public void keyTyped(KeyEvent arg0) {
	  
  }
  
  public static void main (String [] args) {
      GameLogic.getInstance().start();
  }
}
