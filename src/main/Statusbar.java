package main;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

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
	
	public JLabel addLabel(int posX, int posY, int width, int height, BufferedImage image) {
		JLabel label = new JLabel();
		Icon label_icon = new ImageIcon(image);
		label.setIcon(label_icon);
		label.setBounds(posX, posY,
				width,
				height);
		return label;
	}
	
	public JLabel addLabel(int posX, int posY, int width, int height, String text, int textsize, int textalignment, int fontart) {
		JLabel label = new JLabel(text, textalignment);
		label.setFont(new Font("Aharoni", fontart, textsize));
		label.setForeground(Color.white);
		label.setBounds(posX, posY,
				width,
				height);
		return label;
	}
	
}
