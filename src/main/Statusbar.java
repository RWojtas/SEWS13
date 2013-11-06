package main;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.ASManager.ASMouseListener;
import main.Menu.MouseAction;
import main.GameView;
import player.AS;
import player.Human;

public class Statusbar {
	GraphicManager graphicManager;
	
	public Statusbar(GraphicManager graphicManager) {
		this.graphicManager = graphicManager;
	}
	
	public JLabel addBackground() {
		return new JLabel();
	}
	
}
