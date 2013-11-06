package main;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import overlay.BarOverlay;
import main.Menu.MouseAction;
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

	public GameView(ASManager asManager, DiscoObjectManager doManager,
			Player player, GraphicManager graphicManager) {
		deskResolution = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) deskResolution.getWidth(),
				(int) deskResolution.getHeight());
		c = getContentPane();
		currentLevel = 1;
		this.asManager = asManager;
		this.doManager = doManager;
		this.player = player;
		this.graphicManager = graphicManager;
		this.sbar = new Statusbar(graphicManager);

		/*
		 * background = new JPanel(); background.setBackground(Color.black);
		 * background
		 * .setSize((int)deskResolution.getWidth(),(int)deskResolution.
		 * getHeight()); background.setLayout(null); c.add(background);
		 */

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

		layer3.add(player);
		asManager.addComponents(layer3);
		doManager.addComponents(layer4);

		fps = new JLabel("0");
		fps.setOpaque(false);
		fps.setHorizontalAlignment(JLabel.RIGHT);
		fps.setFont(new Font("Dialog", Font.BOLD, 30));
		fps.setBounds((int) deskResolution.getWidth() - 300 - 3,
				(int) deskResolution.getHeight() - 30, 300, 30);

		layer1.add(fps);

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
		
		energyBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(335),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(30));
		energyBar.setValue(0);
		layer1.add(energyBar, 0);
		energyLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(310),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Energie", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(energyLabel, 0);
		
		funBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(405),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(30));
		funBar.setValue(0);
		layer1.add(funBar, 0);
		funLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(380),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Spass", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(funLabel, 0);
		
		urineBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(475),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(30));
		urineBar.setValue(0);
		layer1.add(urineBar, 0);
		urineLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(450),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Blase",
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(urineLabel, 0);
		
		alcLevelBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(545),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(30));
		alcLevelBar.setValue(0);
		layer1.add(alcLevelBar, 0);
		alcLevelLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(520),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Alkoholpegel", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(alcLevelLabel, 0);
		
		flirtBar = sbar.addProgressBar((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(245), BufferedImageLoader.scaleToScreenY(615),
				BufferedImageLoader.scaleToScreenX(235),
				BufferedImageLoader.scaleToScreenY(30));
		flirtBar.setValue(0);
		layer1.add(flirtBar, 0);
		flirtLabel = sbar.addLabel((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(590),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30),
				"Liebaeugelei", 
				24,
				JLabel.LEFT,
				Font.ITALIC);
		layer1.add(flirtLabel, 0);
		
		gameExit = sbar.addExitButton((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(250), BufferedImageLoader.scaleToScreenY(660),
				BufferedImageLoader.scaleToScreenX(240),
				BufferedImageLoader.scaleToScreenY(49));
		layer1.add(gameExit, 0);
		
		sbar.setBars(energyBar, urineBar, flirtBar, alcLevelBar, funBar);
		sbar.setLabels(status_mtitle, status_genre, status_uhrzeit);
		sbar.updateBars(player);
		// End: Statusbar
	}
	
	

	public JPanel createLayerPanel() {
		JPanel layer = new JPanel();
		layer.setLayout(null);
		layer.setBounds(0, 0, (int) deskResolution.getWidth(),
				(int) deskResolution.getHeight());
		layer.setOpaque(false);
		return layer;
	}

	public void setTarget(Human human, int x, int y) {
		Coordinate lo = new Coordinate(x, y);
		Coordinate ro = new Coordinate(x + human.getWidth(), y);
		Coordinate lu = new Coordinate(x, y + human.getHeight());
		;
		Coordinate ru = new Coordinate(x + human.getWidth(), y
				+ human.getHeight());
		;

		// while(true) {
		if (!GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), lo)) {

		}

		if (!GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), ro)) {

		}

		if (!GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), lu)) {

		}

		if (!GameLogic.getInstance().checkFreeCoordinate(human.hashCode(), ru)) {

		}
		// }

		player.setActivity(1);
		player.setTarget(x, y);
	}

	public Coordinate determineFreePosition(int x, int y) {
		Coordinate freePosition = null;
		return freePosition;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked!");
		// Wird ausgelöst, wenn man einen Klick mit der Maus ausführt
		// ohne mit gedrückter Maustaste die Position der Maus zu verändern

		// setTarget(human,e.getX()-player.getWidth()/2,e.getY()-player.getHeight()/2);
		player.setActivity(1);
		player.setTarget(e.getX() - player.getWidth() / 2,
				e.getY() - player.getHeight() / 2);
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
