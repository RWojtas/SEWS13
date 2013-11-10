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
	public int currentLevel;
	public JPanel background;
	public static JLayeredPane layeredPane;
	public JPanel layer1;
	public JPanel layer2;
	public JPanel layer3;
	public JPanel layer4;
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
	private JLabel musicSwitch;
	
	  
	public GameView(ASManager asManager, DiscoObjectManager doManager, Player player, GraphicManager graphicManager) {
		// Musik
		musicManager = new MusicManager();
		
		deskResolution = Toolkit.getDefaultToolkit().getScreenSize();
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

		layer1 = createLayerPanel(); // Höchste Schicht
		layer2 = createLayerPanel(); // Mittlere Schicht
		layer3 = createLayerPanel(); // Mittlere Schicht
		layer4 = createLayerPanel(); // Tiefste Schicht

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

		layer1.add(fps);
		
		// Hintergrund
		JPanel bg = new JPanel();
		bg.setBackground(new Color(10,10,10));
		bg.setBounds(0, 0, BufferedImageLoader.scaleToScreenX(1366), BufferedImageLoader.scaleToScreenY(768));
		layeredPane.add(bg, -1);
		
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
		
		dancefloor = new DancefloorOverlay(graphicManager, player,"Die Tanzfl�che");
		dancefloor.setVisible(false);
		layeredPane.add(bench, JLayeredPane.POPUP_LAYER);
		/*
		flirt = new FlirtOverlay(graphicManager, player, "Das Flirten");
		flirt.setVisible(false);
		layeredPane.add(bench, JLayeredPane.POPUP_LAYER);
		
		toilet = new ToiletOverlay(graphicManager, player, "Die Toilette");
		toilet.setVisible(false);
		layeredPane.add(bench, JLayeredPane.POPUP_LAYER);
		*/
		
		
		// Start: Statusbar
		statusb_bg = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(270), 0,
				BufferedImageLoader.scaleToScreenX(270),
				BufferedImageLoader.scaleToScreenY(768), 
				graphicManager.statusBG.getImage());
		layer1.add(statusb_bg,1);

		statusb_uhr = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(233), BufferedImageLoader.scaleToScreenY(10),
				BufferedImageLoader.scaleToScreenX(223),
				BufferedImageLoader.scaleToScreenY(263),
				graphicManager.statusUhr.getImage());
		layer1.add(statusb_uhr,0);

		status_mtitle = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(65),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"My Heart will go on", 
				16, 
				JLabel.LEFT,
				Font.BOLD);
		layer1.add(status_mtitle, 0);
		
		status_genre = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(95),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30), 
				"Rock", 
				24, 
				JLabel.RIGHT,
				Font.BOLD);
		layer1.add(status_genre, 0);
		
		status_uhrzeit = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(140),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(60),
				"23:00", 
				80,
				JLabel.CENTER,
				Font.BOLD);
		layer1.add(status_uhrzeit, 0);
		
		moneyLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(303),
				BufferedImageLoader.scaleToScreenX(210),
				BufferedImageLoader.scaleToScreenY(30),
				"Geld: 0,00 Euro", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		moneyLabel.setForeground(Color.white);
		layer1.add(moneyLabel, 0);
		
		energyBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(360),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(25));
		energyBar.setValue(0);
		layer1.add(energyBar, 0);
		energyLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(335),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Energie", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(energyLabel, 0);
		
		funBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(425),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(25));
		funBar.setValue(0);
		layer1.add(funBar, 0);
		funLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(400),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Spass", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(funLabel, 0);
		
		urineBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(490),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(25));
		urineBar.setValue(0);
		layer1.add(urineBar, 0);
		urineLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(465),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Blase",
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(urineLabel, 0);
		
		alcLevelBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(555),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(25));
		alcLevelBar.setValue(0);
		layer1.add(alcLevelBar, 0);
		alcLevelLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(530),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Alkoholpegel", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(alcLevelLabel, 0);
		
		flirtBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(620),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(25));
		flirtBar.setValue(0);
		layer1.add(flirtBar, 0);
		flirtLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(595),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Liebaeugelei", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(flirtLabel, 0);
		
		gameExit = sbar.addExitButton((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(60), BufferedImageLoader.scaleToScreenY(710),
				BufferedImageLoader.scaleToScreenX(45),
				BufferedImageLoader.scaleToScreenY(45));
		layer1.add(gameExit, 0);
		
		musicSwitch = sbar.addMusicButton((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(60), BufferedImageLoader.scaleToScreenY(660),
				BufferedImageLoader.scaleToScreenX(45),
				BufferedImageLoader.scaleToScreenY(45));
		layer1.add(musicSwitch, 0);
		
		sbar.setBars(energyBar, urineBar, flirtBar, alcLevelBar, funBar);
		sbar.setLabels(status_mtitle, status_genre, status_uhrzeit, moneyLabel);
		sbar.updateBars(player);
		// End: Statusbar
	}
 
	public Statusbar getStatusbar() {
		return sbar;
	}
  
  public JPanel createLayerPanel() {
	  JPanel layer = new JPanel();
	  layer.setLayout(null);
	  layer.setBounds(0,0,(int)deskResolution.getWidth(),(int)deskResolution.getHeight());
	  layer.setOpaque(false);
	  return layer;
  }
  
  public void setTarget(Human human, int x, int y) {
	  Coordinate target;
	  int xPos = x-human.getWidth()/2;
	  int yPos = y-human.getHeight()/2;
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
  	  
  	  setTarget(player,e.getX(),e.getY()); 
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
