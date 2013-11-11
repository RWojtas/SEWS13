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
  public static final int DISCO_OPEN_FROM = 23*60; //Minuten
  public static final int DISCO_CLOSE_AT = 4*60; //Minuten
  //public static final int DISCO_OPEN_FROM = 23*60; //Minuten
  //public static final int DISCO_CLOSE_AT = 23*60+3; //Minuten
  public Player player;
  public Statusbar statusbar;
  public boolean initialized = false;
  public boolean firstGame = true;
  public boolean menu = true;
  private int  player_money = 25;
  
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
	  firstGame = true;
	  long t1=0;
	  
	  while(true) {
		  System.out.print("");
		  if(menu) continue;
		  if(!initialized) {
			  if(!firstGame) {
				  resetGame();
			  }
			  //System.out.println(firstGame);
			  timestamp = System.nanoTime();
			  updateTimer = timestamp;
			  framesPerSecondTimer = timestamp; 
			  statusbar.initializeClock(DISCO_OPEN_FROM, DISCO_CLOSE_AT, ONE_SECOND/60);
			  initialized = true;
			  firstGame = false;
		  }
		  //Updates
		  if(!statusbar.isTimeOut()) {
			  asManager.updateComponents();
			  //System.out.println("___________________________");
			  //System.out.println("Performance Check Player:");
			  t1 = System.nanoTime();
			  player.stepNextPosition();
			  //System.out.println("Insgesamte Dauer:\nZeit in Nanosekunden: "+(System.nanoTime()-t1));
			  statusbar.updateBars(player);
			  statusbar.updateClock();
			  frames++;
			  player.decreaseStatusOverTime();
			  asManager.decreaseStatusForAll();
			  updateTimer += UPDATE_TIME_INTERVALL;
			  if(player.getActivityTimer()>0){
				  player.decActivityTimer();
			  } 
		  } else {
			  gameView.animateGameOverScreen();
		  }
		  
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
  
  public void resetGame() {
	  player.resetPlayer(player_money,'m', graphicManager.man01.getImage(), BufferedImageLoader.scaleToScreenX(800,true), BufferedImageLoader.scaleToScreenY(500,true),1);
	  gameView.resetGameView(asManager, doManager, player);
  }
  
  private GameLogic() {
    graphicManager = new GraphicManager();
    
    player = new Player(player_money,'m', graphicManager.man02.getImage(), BufferedImageLoader.scaleToScreenX(800,true), BufferedImageLoader.scaleToScreenY(500,true),1);
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
    statusbar = gameView.getStatusbar();
    
    Thread th = new Thread(this);
    th.start();
  }
  
  public boolean checkFreePosition(int id, Coordinate lo, Coordinate ro, Coordinate lu, Coordinate ru) {
	  if(!gameView.checkFreePosition(lo,ro,lu,ru))
		  return false;
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
	  if(statusbar != null)
		  statusbar.updateMusic(getMusicManager());
  }

  public boolean checkFreeCoordinate(int id, Coordinate coordinate) {
	  if(!gameView.checkFreeCoordinate(coordinate))
		  return false;
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
