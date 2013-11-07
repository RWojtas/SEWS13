package main;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import player.Player;

public class Statusbar {
	GraphicManager graphicManager;
	JLabel gameExit;
	JProgressBar energyBar;
	JProgressBar urineBar;
	JProgressBar flirtBar;
	JProgressBar alcLevelBar;
	JProgressBar funBar;
	JLabel status_mtitle;
	JLabel status_genre;
	JLabel status_uhrzeit;
	JLabel moneyLabel;
	
	public Statusbar(GraphicManager graphicManager) {
		this.graphicManager = graphicManager;
	}
	
	public void setBars(JProgressBar energyBar,
	JProgressBar urineBar,
	JProgressBar flirtBar,
	JProgressBar alcLevelBar,
	JProgressBar funBar) {
		this.energyBar = energyBar;
		this.urineBar = urineBar;
		this.flirtBar = flirtBar;
		this.alcLevelBar = alcLevelBar;
		this.funBar = funBar;
	}
	
	public void setLabels(JLabel status_mtitle, JLabel status_genre, JLabel status_uhrzeit, JLabel moneyLabel) {
		this.status_genre = status_genre;
		this.status_mtitle = status_mtitle;
		this.status_uhrzeit = status_uhrzeit;
		this.moneyLabel = moneyLabel;
	}
	
	public void updateBars(Player player) {
		JProgressBar bars[] = {energyBar, urineBar, flirtBar, alcLevelBar, funBar};
		int werte[] = {(int)(player.getEnergy()*100),(int)(player.getUrine()*100),(int)(player.getFlirt()*100),(int)(player.getAlcLevel()*100),(int)(player.getFun()*100)};
		
		for(int i=0;i<bars.length;i++) {
			if(bars[i].getValue() < werte[i]) {
				bars[i].setValue(bars[i].getValue()+1);
			} else if(bars[i].getValue() > werte[i]) {
				bars[i].setValue(bars[i].getValue()-1);
			}
		}
		moneyLabel.setText("Geld: "+player.getMoney()+" Euro");
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
	
	public JProgressBar addProgressBar(int posX, int posY, int width, int height) {
		JProgressBar progressbar = new JProgressBar();
		progressbar.setMinimum(0);
		progressbar.setMaximum(100);
		progressbar.setStringPainted(false);
		progressbar.setBounds(posX,posY,width,height);
		progressbar.setBackground(Color.black);
		progressbar.setForeground(Color.white);
		progressbar.setBorderPainted(false);
		return progressbar;
	}
	
	public void setMTitle(String text) {
		this.status_mtitle.setText(text);
	}
	
	public void setGenre(String text) {
		this.status_genre.setText(text);
	}
	
	public void setUhrzeit(String text) {
		this.status_uhrzeit.setText(text);
	}
	
	public JLabel addExitButton(int posX, int posY, int width, int height) {
		gameExit = new JLabel();
		Icon gameExit_icon = new ImageIcon(
				graphicManager.statusBeenden.getImage(0,0));
		Icon gameExit_icon_hover = new ImageIcon(
				graphicManager.statusBeenden.getImage(0,1));
		gameExit.setIcon(gameExit_icon);
		gameExit.setBounds(posX,posY,width,height);
		MouseAction gameExit_l = new MouseAction('e', gameExit_icon, gameExit_icon_hover);
		gameExit.addMouseListener(gameExit_l);
		return gameExit;
	}
	
	class MouseAction implements MouseListener {
		private Icon standard;
		private Icon hover;
		private char act;

		public MouseAction(char act, Icon standard, Icon hover) {
			this.standard = standard;
			this.hover = hover;
			this.act = act;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			switch (act) {
			case 'e':
				System.exit(0);
				break;
			}
			((JLabel) e.getSource()).setIcon(standard);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			((JLabel) e.getSource()).setIcon(hover);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((JLabel) e.getSource()).setIcon(standard);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
}
