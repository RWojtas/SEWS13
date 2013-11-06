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
	public JPanel statusbar;

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

		// Statusbar

		JLabel statusb_bg = new JLabel();
		Icon statusb_bg_icon = new ImageIcon(graphicManager.statusBG.getImage());
		statusb_bg.setIcon(statusb_bg_icon);
		statusb_bg.setBounds((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(270), 0,
				BufferedImageLoader.scaleToScreenX(270),
				BufferedImageLoader.scaleToScreenY(768));
		layer1.add(statusb_bg,1);

		JLabel statusb_uhr = new JLabel();
		Icon statusb_uhr_icon = new ImageIcon(graphicManager.statusUhr.getImage());
		statusb_uhr.setIcon(statusb_uhr_icon);
		statusb_uhr.setBounds((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(233), BufferedImageLoader.scaleToScreenY(10),
				BufferedImageLoader.scaleToScreenX(223),
				BufferedImageLoader.scaleToScreenY(263));
		layer1.add(statusb_uhr,0);

		/*
		 * JPanel StatusbarBG = new JPanel(); StatusbarBG.setLayout(null);
		 * StatusbarBG
		 * .setBounds((int)deskResolution.getWidth()-200,0,200,(int)deskResolution
		 * .getHeight()); float[] hsbvals = Color.RGBtoHSB(50, 50, 50, null);
		 * StatusbarBG
		 * .setBackground(Color.getHSBColor(hsbvals[0],hsbvals[1],hsbvals[2]));
		 * layer1.add(StatusbarBG);
		 */

		JLabel Titel = new JLabel("My Heart will go on", JLabel.LEFT);
		Titel.setFont(new Font("Aharoni", Font.BOLD, 16));
		Titel.setForeground(Color.white);
		Titel.setBounds((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(65),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30));
		layer1.add(Titel, 0);
		
		JLabel Genre = new JLabel("Rock", JLabel.RIGHT);
		Genre.setFont(new Font("Aharoni", Font.BOLD, 24));
		Genre.setForeground(Color.white);
		Genre.setBounds((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(95),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(30));
		layer1.add(Genre, 0);
		
		JLabel Uhrzeit = new JLabel("23:05", JLabel.CENTER);
		Uhrzeit.setFont(new Font("Aharoni", Font.BOLD, 80));
		Uhrzeit.setForeground(Color.white);
		Uhrzeit.setBounds((int) deskResolution.getWidth()
				- BufferedImageLoader.scaleToScreenX(215), BufferedImageLoader.scaleToScreenY(140),
				BufferedImageLoader.scaleToScreenX(175),
				BufferedImageLoader.scaleToScreenY(60));
		layer1.add(Uhrzeit, 0);
		// End: Statusbar

		// Temp
		// JLabel status = new JLabel(new
		// ImageIcon(doManager.graphicManager.status.getImage()));
		// status.setBounds(BufferedImageLoader.scaleToScreenX(1366-272), 0,
		// 272, 768);

		// layer1.add(status);

		// Temp Ende
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
