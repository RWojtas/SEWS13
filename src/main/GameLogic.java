package main;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import music.MusicManager;
import player.*;
import main.GameView;


public class GameLogic implements Runnable, KeyListener {
  public static GameLogic gameLogic;
  public static GameView gameView;
  public static GraphicManager graphicManager;
  public static ASManager asManager;
  public static DiscoObjectManager doManager;
  private static MusicManager musicManager;
  public static final long UPDATE_TIME_INTERVALL = 6000000; //Nanosekunden
  public static final long ONE_SECOND = 1000000000; //Nanosekunden
  public static final long FPS_DISPLAY_INTERVALL = 100000000; //Nanosekunden
  public Player player;
  public boolean menu = true;
  public Statusbar sbar;
  public boolean initialized = false;

  public static GameLogic getInstance() {
	  if(gameLogic == null) {
		  gameLogic = new GameLogic();
	  }
	  return gameLogic;
  }
  
  public void run() {
	  
	  long fps = 0;
	  long frames = 0;
	  long updateTimer = 0;
	  long framesPerSecondTimer = 0;
	  long timestamp = 0;
	  long sleepTime = 0;
	  
	  while(true) {
		  if(menu) continue;
		  if(!initialized) {  
			  timestamp = System.nanoTime();
			  updateTimer = timestamp;
			  framesPerSecondTimer = timestamp;
			  initialized = true;
		  }
		  
		  //Updates
		  asManager.updateComponents();
		  player.stepNextPosition();
		  sbar.updateBars(player);
		  frames++;
		  updateTimer += UPDATE_TIME_INTERVALL;
		  
		  //FPS Berechnung 
		  if(System.nanoTime()-framesPerSecondTimer >= FPS_DISPLAY_INTERVALL) {
			  fps = frames*(ONE_SECOND/(System.nanoTime()-framesPerSecondTimer));
	    	  gameView.fps.setText("FPS "+fps);   
	    	  frames = 0;
	    	  framesPerSecondTimer += FPS_DISPLAY_INTERVALL;
		  }
		  
		  timestamp = System.nanoTime();
		  
		  sleepTime = convertToMilliseconds(UPDATE_TIME_INTERVALL-(System.nanoTime()-updateTimer));
		  if(sleepTime > 0) {
			  try {
				  Thread.sleep(sleepTime);
			  } catch (InterruptedException e) {
				  e.printStackTrace();
			  }  
		  } 
	  }
  }
  
  private GameLogic() {
    graphicManager = new GraphicManager();
    
    player = new Player(100,'m', graphicManager.man01.getImage(), BufferedImageLoader.scaleToScreenX(800), BufferedImageLoader.scaleToScreenY(500),1);
    doManager = new DiscoObjectManager(graphicManager, this, player);
    asManager = new ASManager(graphicManager,doManager);
    
    gameView = new GameView(asManager, doManager, player, graphicManager);
    gameView.setTitle("Felse deine Feier");
    gameView.setUndecorated(true);
    gameView.setAlwaysOnTop(true);
    gameView.setResizable(false);
    gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameView.setVisible(true);
    gameView.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(graphicManager.mouse.getImage(), new Point(gameView.getX(), gameView.getY()), "mouse02"));
    gameView.addKeyListener(this);
    gameView.setVisible(true);
    sbar = gameView.getStatusbar();
    
    Thread th = new Thread(this);
    th.start();
  }
  
  public boolean checkFreePosition(int id, Coordinate lo, Coordinate ro, Coordinate lu, Coordinate ru) {
	  if(!asManager.checkFreePosition(id,lo,ro,lu,ru)) 
		  return false;
	  if(!doManager.checkFreePosition(lo,ro,lu,ru)) 
		  return false;
	  
	  return true;
  }
  
  public static MusicManager getMusicManager() {
	return musicManager;
  }

  public static void setMusicManager(MusicManager musicManager) {
	GameLogic.musicManager = musicManager;
  }
  
  public void updateMusic() {
	  sbar.updateMusic(getMusicManager());
  }

  public boolean checkFreeCoordinate(int id, Coordinate coordinate) {
	  if(!asManager.checkFreeCoordinate(id,coordinate)) 
		  return false;
	  if(!doManager.checkFreeCoordinate(coordinate)) 
		  return false;
	  
	  return true;
  }
  
  public JComponent getComponentAt(int x, int y) {
	  JComponent component = (JComponent)(asManager.getComponentAt(x, y));
	  if(component == null) {
		  component = (JComponent)doManager.getComponentAt(x, y); 
	  }
	  return component;
  }
  
  public long convertToMilliseconds(long nanoseconds) {
	  return nanoseconds/1000000;
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
  
  public static void main(String args[]) {	  
	  GameLogic.getInstance();
  }
}
