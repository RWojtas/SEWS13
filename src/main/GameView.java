package main;


// TEST TEST TEST 

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.*;

import overlay.BarOverlay;
import overlay.BenchOverlay;
import overlay.DJOverlay;
import overlay.DancefloorOverlay;
import overlay.FlirtOverlay;
import overlay.ToiletOverlay;
import main.Menu.MouseAction;
import music.MusicManager;
import objects.*;
import player.*;

public class GameView extends JFrame implements MouseListener {

    public GraphicManager graphicManager;
	public ASManager asManager;
	public DiscoObjectManager doManager;
	public Player player;
	public Container c;
	public static Dimension deskResolution;
	public static Dimension gameResolution;
	public int currentLevel;
	public JPanel background;
	public static JLayeredPane layeredPane;
	public JPanel layer1;
	public JPanel layer2;
	public JPanel layer3;
	public JPanel layer4;
	public JPanel layer5;
	public JLabel fps;
	
	public MusicManager musicManager;
	
	public Menu menu;
	
	public BarOverlay bar;
	public DJOverlay dj;
	public BenchOverlay bench;
	public DancefloorOverlay dancefloor;
	public FlirtOverlay flirt;
	public ToiletOverlay toilet;
	
	Statusbar sbar;
	JLabel statusb_bg;
	JLabel statusb_uhr;
	JLabel status_mtitle;
	JLabel status_genre;
	JLabel status_uhrzeit;
	JProgressBar energyBar;
	JProgressBar flirtBar;
	JProgressBar alcLevelBar;
	JProgressBar urineBar;
	JProgressBar funBar;
	JLabel energyLabel;
	JLabel flirtLabel;
	JLabel alcLevelLabel;
	JLabel urineLabel;
	JLabel funLabel;
	JLabel gameExit;
	JLabel moneyLabel;
	JLabel highscoreLabel;
	private JLabel musicSwitch;
	private JLabel gameOverLabel;
	private int currentAnimationProgress;
	private int endAnimationDuration;
	
	  
	public GameView(ASManager asManager, DiscoObjectManager doManager, Player player, GraphicManager graphicManager) {
		// Musik
		musicManager = new MusicManager();	
		deskResolution = Toolkit.getDefaultToolkit().getScreenSize();
		gameResolution = new Dimension(BufferedImageLoader.scaleToScreenX((int)(BufferedImageLoader.getStandardResolution().getWidth()),false)
									  ,BufferedImageLoader.scaleToScreenY((int)(BufferedImageLoader.getStandardResolution().getHeight()),false));
		setSize((int) deskResolution.getWidth(),
				(int) deskResolution.getHeight());
		c = getContentPane();
		currentLevel = 1;
		this.asManager = asManager;
		this.doManager = doManager;
		this.player = player;
		this.graphicManager = graphicManager;
		this.sbar = new Statusbar(graphicManager, musicManager);
		
		layeredPane = new JLayeredPane();
		layeredPane.addMouseListener(this);

		c.add(layeredPane);
		
	
		layer1 = createLayerPanel(); // Hoechste Schicht
		layer2 = createLayerPanel(); // Mittlere Schicht
		layer3 = createLayerPanel(); // Mittlere Schicht
		layer4 = createLayerPanel(); // Tiefste Schicht
		layer5 = createLayerPanel(); // Hintergrund Schicht

		layeredPane.add(layer1, 0); // Layer für Statusbar
		layeredPane.add(layer2, 1); // Layer für Overlay
		layeredPane.add(layer3, 2); // Layer für Human
		layeredPane.add(layer4, 3); // Layer für DiscoObject
		
		layeredPane.add(musicManager.getPanel());
		
		//Men�
		menu = new Menu(graphicManager, musicManager);
		layeredPane.add(menu, JLayeredPane.POPUP_LAYER);
		
		layer3.add(player);
		asManager.addComponents(layer3);
		doManager.addComponents(layer4);

		fps = new JLabel("FPS 0");
		fps.setOpaque(false);
		fps.setHorizontalAlignment(JLabel.RIGHT);
		fps.setFont(new Font("Dialog", Font.BOLD, 30));
		fps.setBounds((int) deskResolution.getWidth() - 300 - 3,
				(int) deskResolution.getHeight() - 30, 300, 30);
		fps.setVisible(false);

		layer1.add(fps);
		
		// Hintergrund
		JPanel bg = new JPanel();
		bg.setBackground(new Color(10,10,10));
		bg.setBounds(0,0,(int)(gameResolution.getWidth()),(int)(gameResolution.getHeight()));
		layer4.add(bg);
		
		//Overlays
		bar = new BarOverlay(graphicManager, player, "Die Bar");
		bar.setVisible(false);
		layeredPane.add(bar, JLayeredPane.POPUP_LAYER);
		
		dj = new DJOverlay(graphicManager, "Der DJ", musicManager, player);
		dj.setVisible(false);
		layeredPane.add(dj, JLayeredPane.POPUP_LAYER);
		
		bench = new BenchOverlay(graphicManager, player, "Die Bank");
		bench.setVisible(false);
		layeredPane.add(bench, JLayeredPane.POPUP_LAYER);
		
		toilet = new ToiletOverlay(graphicManager, player, "Die Toilette");
		toilet.setVisible(false);
		layeredPane.add(toilet, JLayeredPane.POPUP_LAYER);
		
		dancefloor = new DancefloorOverlay(graphicManager, player, "Die Tanzflaeche");
		dancefloor.setVisible(false);
		layeredPane.add(dancefloor, JLayeredPane.POPUP_LAYER);		
		
		flirt = new FlirtOverlay(graphicManager, player, "Das Flirten");
		flirt.setVisible(false);
		layeredPane.add(flirt, JLayeredPane.POPUP_LAYER);
		
		
		
		// Start: Statusbar
		/*
		 * @author Sebastian
		 */
		statusb_bg = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(270,false), 0,
				BufferedImageLoader.scaleToScreenX(270,false),
				BufferedImageLoader.scaleToScreenY(768,false), 
				graphicManager.statusBG.getImage());
		layer1.add(statusb_bg,1);

		statusb_uhr = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(233,false), BufferedImageLoader.scaleToScreenY(10,false),
				BufferedImageLoader.scaleToScreenX(223,false),
				BufferedImageLoader.scaleToScreenY(263,false),
				graphicManager.statusUhr.getImage());
		layer1.add(statusb_uhr,0);

		status_mtitle = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(65,false),
				BufferedImageLoader.scaleToScreenX(175,false),
				BufferedImageLoader.scaleToScreenY(30,false),
				"My Heart will go on", 
				16, 
				JLabel.LEFT,
				Font.BOLD);
		layer1.add(status_mtitle, 0);
		
		status_genre = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(95,false),
				BufferedImageLoader.scaleToScreenX(175,false),
				BufferedImageLoader.scaleToScreenY(30,false), 
				"Rock", 
				24, 
				JLabel.RIGHT,
				Font.BOLD);
		layer1.add(status_genre, 0);
		
		status_uhrzeit = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(140,false),
				BufferedImageLoader.scaleToScreenX(175,false),
				BufferedImageLoader.scaleToScreenY(60,false),
				"23:00", 
				80,
				JLabel.CENTER,
				Font.BOLD);
		layer1.add(status_uhrzeit, 0);
		
		moneyLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(303,false),
				BufferedImageLoader.scaleToScreenX(210,false),
				BufferedImageLoader.scaleToScreenY(30,false),
				"Geld: 0,00 Euro", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		moneyLabel.setForeground(Color.white);
		layer1.add(moneyLabel, 0);
		
		energyBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245,false), BufferedImageLoader.scaleToScreenY(360,false),
				BufferedImageLoader.scaleToScreenX(235,false),
				BufferedImageLoader.scaleToScreenY(25,false));
		energyBar.setValue(0);
		layer1.add(energyBar, 0);
		energyLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(335,false),
				BufferedImageLoader.scaleToScreenX(175,false),
				BufferedImageLoader.scaleToScreenY(30,false),
				"Energie", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(energyLabel, 0);
		
		funBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245,false), BufferedImageLoader.scaleToScreenY(425,false),
				BufferedImageLoader.scaleToScreenX(235,false),
				BufferedImageLoader.scaleToScreenY(25,false));
		funBar.setValue(0);
		layer1.add(funBar, 0);
		funLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(400,false),
				BufferedImageLoader.scaleToScreenX(175,false),
				BufferedImageLoader.scaleToScreenY(30,false),
				"Spass", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(funLabel, 0);
		
		urineBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245,false), BufferedImageLoader.scaleToScreenY(490,false),
				BufferedImageLoader.scaleToScreenX(235,false),
				BufferedImageLoader.scaleToScreenY(25,false));
		urineBar.setValue(0);
		layer1.add(urineBar, 0);
		urineLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(465,false),
				BufferedImageLoader.scaleToScreenX(175,false),
				BufferedImageLoader.scaleToScreenY(30,false),
				"Blase",
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(urineLabel, 0);
		
		alcLevelBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245,false), BufferedImageLoader.scaleToScreenY(555,false),
				BufferedImageLoader.scaleToScreenX(235,false),
				BufferedImageLoader.scaleToScreenY(25,false));
		alcLevelBar.setValue(0);
		layer1.add(alcLevelBar, 0);
		alcLevelLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(530,false),
				BufferedImageLoader.scaleToScreenX(175,false),
				BufferedImageLoader.scaleToScreenY(30,false),
				"Alkoholpegel", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(alcLevelLabel, 0);
		
		flirtBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245,false), BufferedImageLoader.scaleToScreenY(620,false),
				BufferedImageLoader.scaleToScreenX(235,false),
				BufferedImageLoader.scaleToScreenY(25,false));
		flirtBar.setValue(0);
		layer1.add(flirtBar, 0);
		flirtLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215,false), BufferedImageLoader.scaleToScreenY(595,false),
				BufferedImageLoader.scaleToScreenX(175,false),
				BufferedImageLoader.scaleToScreenY(30,false),
				"Liebaeugelei", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(flirtLabel, 0);
		
		gameExit = sbar.addExitButton((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(60,false), BufferedImageLoader.scaleToScreenY(710,false),
				BufferedImageLoader.scaleToScreenX(45,false),
				BufferedImageLoader.scaleToScreenY(45,false));
		layer1.add(gameExit, 0);
		
		musicSwitch = sbar.addMusicButton((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(60,false), BufferedImageLoader.scaleToScreenY(660,false),
				BufferedImageLoader.scaleToScreenX(45,false),
				BufferedImageLoader.scaleToScreenY(45,false));
		layer1.add(musicSwitch, 0);
		
		sbar.setBars(energyBar, urineBar, flirtBar, alcLevelBar, funBar);
		sbar.setLabels(status_mtitle, status_genre, status_uhrzeit, moneyLabel);
		sbar.updateBars(player);
		// End: Statusbar
		
		gameOverLabel = new JLabel();
		gameOverLabel.setOpaque(false);
		gameOverLabel.setVisible(false);
		currentAnimationProgress = 0;
		endAnimationDuration = 30;
		layer1.add(gameOverLabel,0);
		
		highscoreLabel = new JLabel();
		highscoreLabel.setOpaque(false);
		highscoreLabel.setVisible(false);
		highscoreLabel.setHorizontalAlignment(JLabel.CENTER);
		highscoreLabel.setVerticalAlignment(JLabel.CENTER);
		highscoreLabel.setFont(new Font("Arial",Font.BOLD,BufferedImageLoader.scaleToScreenX(100,false)));
		highscoreLabel.setForeground(Color.white);
		layer1.add(highscoreLabel,0);
	}
	
	public void resetGameView(ASManager asManager, DiscoObjectManager doManager, Player player) {
		this.asManager = asManager;
		this.doManager = doManager;
		this.player = player;
		
		asManager.resetComponents();
		doManager.resetComponents();

		fps.setOpaque(false);
		fps.setHorizontalAlignment(JLabel.RIGHT);
		fps.setFont(new Font("Dialog", Font.BOLD, 30));
		fps.setBounds((int) deskResolution.getWidth() - 300 - 3,
				(int) deskResolution.getHeight() - 30, 300, 30);
		
		//Overlays
		bar.setVisible(false);

		dj.setVisible(false);
		
		bench.setVisible(false);

		toilet.setVisible(false);
		
		dancefloor.setVisible(false);		
		
		flirt.setVisible(false);
		
		funBar.setValue(0);
		
		urineBar.setValue(0);
		
		alcLevelBar.setValue(0);
		
		flirtBar.setValue(0);
		
		sbar.setBars(energyBar, urineBar, flirtBar, alcLevelBar, funBar);
		sbar.setLabels(status_mtitle, status_genre, status_uhrzeit, moneyLabel);
		sbar.updateBars(player);
		// End: Statusbar
		
		gameOverLabel.setOpaque(false);
		gameOverLabel.setVisible(false);
		currentAnimationProgress = 0;
		endAnimationDuration = 30;
		
		highscoreLabel.setOpaque(false);
		highscoreLabel.setVisible(false);
		highscoreLabel.setHorizontalAlignment(JLabel.CENTER);
		highscoreLabel.setVerticalAlignment(JLabel.CENTER);
		highscoreLabel.setFont(new Font("Arial",Font.BOLD,BufferedImageLoader.scaleToScreenX(100,false)));
		highscoreLabel.setForeground(Color.white);
	}
	
	public void animateGameOverScreen() {
		if(currentAnimationProgress <= endAnimationDuration) {
			int animationSpeed = 1;
			
			BufferedImageLoader gameOverImage = graphicManager.gameOverImage;
			graphicManager.gameOverImage.reScaleImageSize(gameOverImage.getImageWidth()+animationSpeed*2, gameOverImage.getImageHeight()+animationSpeed*2);
			BufferedImage image = graphicManager.gameOverImage.getImage();		
			
			gameOverLabel.setIcon(new ImageIcon(image.getSubimage(0,0,image.getWidth(),image.getHeight())));
			gameOverLabel.setBounds(BufferedImageLoader.scaleToScreenX(300,false)-currentAnimationProgress,
									BufferedImageLoader.scaleToScreenY(300,false)-currentAnimationProgress,
									image.getWidth(),
									image.getHeight());
			
			highscoreLabel.setBounds(BufferedImageLoader.scaleToScreenX(100,false)-currentAnimationProgress,
									BufferedImageLoader.scaleToScreenY(500,false),
									BufferedImageLoader.scaleToScreenX(1000,false),
									BufferedImageLoader.scaleToScreenY(300,false));

			//Initialization
			if(currentAnimationProgress == 0) {
				highscoreLabel.setText(String.format("Punktestand: %d",Highscore.getInstance().getScore()));
				Highscore.getInstance().saveScore();
				gameOverLabel.setVisible(true);
				highscoreLabel.setVisible(true);
			}
			
			currentAnimationProgress+=animationSpeed;
		}	
	}
	
	public void fateGameOverLabel() {
		gameOverLabel.setVisible(false);
		gameOverLabel.setLocation(- gameOverLabel.getWidth(),- gameOverLabel.getHeight());
	}
	
	public boolean checkFreeCoordinate(Coordinate coordinate) {
		if(coordinate.getXCoordinate() > gameResolution.getWidth() || coordinate.getXCoordinate() < 0) {
			return false;
		}
		if(coordinate.getYCoordinate() > gameResolution.getHeight() || coordinate.getYCoordinate() < 0) {
			return false;
		}
		return true;
	}
	
	public boolean checkFreePosition(Coordinate lo, Coordinate ro, Coordinate lu, Coordinate ru) {
		if(!checkFreeCoordinate(lo)) 
			return false;
		if(!checkFreeCoordinate(ro)) 
			return false;
		if(!checkFreeCoordinate(lu)) 
			return false;
		if(!checkFreeCoordinate(ru)) 
			return false;
		
		return true;
	}
	
	public Statusbar getStatusbar() {
		return sbar;
	}
  
  public JPanel createLayerPanel() {
	  JPanel layer = new JPanel();
	  layer.setLayout(null);
	  layer.setBounds(BufferedImageLoader.scaleToScreenX(0, true),
			  BufferedImageLoader.scaleToScreenY(0, true),
			  (int)deskResolution.getWidth(),
			  (int)deskResolution.getHeight());
	  layer.setOpaque(false);
	  return layer;
  }
  
  public void setTarget(Human human, int x, int y) {
	  Coordinate target;
	  int xPos = x-human.getWidth()/2-(int)BufferedImageLoader.getWidthCorrection();
	  int yPos = y-human.getHeight()/2-(int)BufferedImageLoader.getHeightCorrection();
	  Coordinate lo = new Coordinate(xPos,yPos);
	  Coordinate ro = new Coordinate(xPos+human.getWidth(),yPos);
	  Coordinate lu = new Coordinate(xPos,yPos+human.getHeight());;
	  Coordinate ru = new Coordinate(xPos+human.getWidth(),yPos+human.getHeight());;

	  if(!GameLogic.getInstance().checkFreePosition(human.hashCode(),lo,ro,lu,ru)) {
		  if(GameLogic.getInstance().getComponentAt(x,y) == null) {
			  target = determineFreeCoordinate01(human, new Coordinate(xPos,yPos));
		  } else {
			  target = determineFreeCoordinate02(human, new Coordinate(xPos,yPos));
		  }
	  } else {
		  target = new Coordinate(xPos, yPos);
	  }
	  
	  human.setActivity(1);
	  human.setTarget(target.getXCoordinate(), target.getYCoordinate());  
  }
  
  public Coordinate determineFreeCoordinate02(Human human, Coordinate c) {
	  int MAX_LOOPS = 1000;
	  int loopCounter = 0; //counts loops and Position shifts
	  int xPos;
	  int yPos;
	  int differenceX;
	  int differenceY;
	  double slope;
	  Coordinate lo = null;
	  Coordinate ro = null;
	  Coordinate lu = null;
	  Coordinate ru = null;
	  boolean loFree;
	  boolean roFree;
	  boolean luFree;
	  boolean ruFree;
	  
	  xPos = c.getXCoordinate();
	  yPos = c.getYCoordinate();
	  
	  differenceX = Math.abs(human.getPosition().getX0()-xPos);
	  differenceY = Math.abs(human.getPosition().getY0()-yPos);
	  
	  do {
		  loopCounter++;
		  
		  if(differenceX > differenceY) {
			  slope = (double)differenceY/(double)differenceX;
			  if(human.getPosition().getX0() < c.getXCoordinate()) {
				  xPos--; 
			  } else {
				  xPos++;  
			  }
			  if(human.getPosition().getY0() < c.getYCoordinate()) {
				  yPos = c.getYCoordinate()-(int)((double)loopCounter*slope); 
				  
			  } else {
				  yPos = c.getYCoordinate()+(int)((double)loopCounter*slope); 		  
			  }
		  } else {
			  if(differenceX < differenceY) {
				  slope = (double)differenceX/(double)differenceY;   
				  if(human.getPosition().getX0() < c.getXCoordinate()) {
					  xPos = c.getXCoordinate()-(int)((double)loopCounter*slope);
				  } else {
					  xPos = c.getXCoordinate()+(int)((double)loopCounter*slope);
				  }
				  if(human.getPosition().getY0() < c.getYCoordinate()) {
					  yPos--;
				  } else {
					  yPos++;
				  }
			  } else {
				  if(differenceY == 0 && differenceX == 0) {
					  break;
				  } else {
					  slope = 1;
					  if(human.getPosition().getX0() < c.getXCoordinate()) {
						  xPos--;
					  } else {
						  xPos++;
					  }
					  if(human.getPosition().getY0() < c.getYCoordinate()) {
						  yPos--;
					  } else {
						  yPos++;
					  }
				  }
			  }
		  }
		  
		  //Debugging Method invoke
		  //printStep(c,xPos,yPos);
		  //Scanner s = new Scanner(System.in);
		  //s.nextLine();
		  //System.out.println();
		  
		  lo = new Coordinate(xPos,yPos);
		  ro = new Coordinate(xPos+human.getWidth(),yPos);
		  lu = new Coordinate(xPos,yPos+human.getHeight());
		  ru = new Coordinate(xPos+human.getWidth(),yPos+human.getHeight());
	  } while(!GameLogic.getInstance().checkFreePosition(human.hashCode(), lo, ro, lu, ru) && loopCounter <= MAX_LOOPS);  
	  return lo;
  }
  
  //Debugging Method
  public void printStep(Coordinate c, int xPos, int yPos) {
	  String as = "#", bs = "C", cs = "O";
	  String p;
	  int fieldsize = 50;
	  System.out.println("Coordinate: ("+c.getXCoordinate()+"|"+c.getYCoordinate()+") xPos: "+xPos+" yPos: "+yPos);
	  for(int a=-fieldsize;a<fieldsize;a++) {
		  for(int b=-fieldsize;b<fieldsize;b++) {
			  p = as;
			  if(a==0 && b==0) {
				  p = cs;  
			  }
			  if(a==yPos-c.getYCoordinate() && b==xPos-c.getXCoordinate()) {
				  p = bs;
			  }
			  System.out.print(p);
		  }  
		  System.out.println();
	  }
  }

  public Coordinate determineFreeCoordinate01(Human human, Coordinate c) {
	  final int MAX_LOOPS = 100;
	  int loopCounter = 0;
	  Coordinate lo;
	  Coordinate ro;
	  Coordinate lu;
	  Coordinate ru;
	  boolean loFree;
	  boolean roFree;
	  boolean luFree;
	  boolean ruFree;
	  do {
		  loopCounter++;
		  
		  lo = new Coordinate(c.getXCoordinate(),c.getYCoordinate());
		  ro = new Coordinate(c.getXCoordinate()+human.getWidth(),c.getYCoordinate());
		  lu = new Coordinate(c.getXCoordinate(),c.getYCoordinate()+human.getHeight());
		  ru = new Coordinate(c.getXCoordinate()+human.getWidth(),c.getYCoordinate()+human.getHeight());
		  
		  loFree = GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), lo);
		  roFree = GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), ro);
		  luFree = GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), lu);
		  ruFree = GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), ru);
				 
		  if(!loFree) {
			  if(luFree && roFree){
				if(human.getPosition().getX3() < lo.getXCoordinate()) {
					//dir u
					c = shiftTarget(0, c);
				} else {
					if(human.getPosition().getY3() < lo.getYCoordinate()) {
						//dir r
						c = shiftTarget(6, c);
					} else {
						//dir ur
						c = shiftTarget(7, c);
					}
				}	
			  } else {
				  if(luFree) {
					  //dir r
					  c = shiftTarget(6, c);
				  } else {
					  //dir u
					  c = shiftTarget(0, c);
				  }
			  }
		  } 
		  
		  if(!roFree) {
			  if(loFree && ruFree){
			      if(human.getPosition().getX2() > ro.getXCoordinate()) {
					  //dir u
			    	  c = shiftTarget(0, c);
				  } else {
					  if(human.getPosition().getY2() < ro.getYCoordinate()) {
						  //dir l
						  c = shiftTarget(2, c);
					  } else {
						  //dir ul
						  c = shiftTarget(1, c);
					  }
				  }
			   } else {
				   if(loFree) {
					   //dir l
					   c = shiftTarget(2, c);
				   } else {
					   //dir u
					   c = shiftTarget(0, c);
				   }
			   }
		  } 
		  
		  if(!luFree) {
			  if(loFree && ruFree){
			      if(human.getPosition().getX1() < lu.getXCoordinate()) {
					  //dir o
			    	  c = shiftTarget(4, c);
				  } else {
					  if(human.getPosition().getY1() > lu.getYCoordinate()) {
						  //dir r
						  c = shiftTarget(6, c);
					  } else{
						  //dir ro
						  c = shiftTarget(5, c);
					  }
				  }
				  
			   } else {
				   if(loFree) {
					   //dir o
					   c = shiftTarget(4, c);
				   } else {
					   //dir r
					   c = shiftTarget(6, c);
				   }
			   }
		  } 
		  
		  if(!ruFree) {
			  if(luFree && roFree){
			      if(human.getPosition().getX0() > ru.getXCoordinate()) {
			    	  //dir o
			    	  c = shiftTarget(4, c);
				  } else {
					  if(human.getPosition().getY0() > ru.getYCoordinate()) {
						  //dir l
						  c = shiftTarget(2, c);
					  } else {
						  //dir ol
						  c = shiftTarget(3, c);
					  }  
				  }
			   } else {
				   if(luFree) {
					   //dir l
					   c = shiftTarget(2, c);
				   } else {
					   //dir o
					   c = shiftTarget(4, c);
				   }
			   }
		  }
	  } while((c.getXCoordinate() != lo.getXCoordinate() || c.getYCoordinate() != lo.getYCoordinate()) && loopCounter <= MAX_LOOPS);  
	  return c;
  }
  
    /*
	 * direction: Richtung
	 * 0  - unten
	 * 1  - unten/links (diagonal)
	 * 2  - links
	 * 3  - links/oben (diagonal)
	 * 4  - oben
	 * 5  - oben/rechts (diagonal)
	 * 6  - rechts
	 * 7  - rechts/unten (diagonal)
	 */
  public Coordinate shiftTarget(int direction, Coordinate c) {
	  int shiftWide = 1;
	  
	  switch(direction) {
	  case 0: return new Coordinate(c.getXCoordinate()			,c.getYCoordinate()+shiftWide);
	  case 1: return new Coordinate(c.getXCoordinate()-shiftWide,c.getYCoordinate()+shiftWide);
	  case 2: return new Coordinate(c.getXCoordinate()-shiftWide,c.getYCoordinate());
	  case 3: return new Coordinate(c.getXCoordinate()-shiftWide,c.getYCoordinate()-shiftWide);
	  case 4: return new Coordinate(c.getXCoordinate()			,c.getYCoordinate()-shiftWide);
	  case 5: return new Coordinate(c.getXCoordinate()+shiftWide,c.getYCoordinate()-shiftWide);
	  case 6: return new Coordinate(c.getXCoordinate()+shiftWide,c.getYCoordinate());
	  case 7: return new Coordinate(c.getXCoordinate()+shiftWide,c.getYCoordinate()+shiftWide);
	  default: return c;
	  }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  	  System.out.println("mouseClicked!");
  	  //Wird ausgelöst, wenn man einen Klick mit der Maus ausführt 
  	  //ohne mit gedrückter Maustaste die Position der Maus zu verändern
  	  if(player.getActivityTimer() == 0) {
  	  setTarget(player,e.getX(),e.getY());
  	  }
  }

	@Override
	public void mouseEntered(MouseEvent e) {
		// Wird ausgelöst, wenn die Maus den aktiven Bereich des MouseListeners
		// betritt

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Wird ausgelöst, wenn die Maus den aktiven Bereich des MouseListeners
		// verlässt

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Wird ausgelöst, wenn man einen Klick mit der Maus ausführt (egal
		// wie lange der Klick andauert)

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Wird ausgelöst, nachdem man einen Klick mit der Maus wieder
		// loslässt

	}
}
