package main;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import music.MusicManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import player.Player;

public class Statusbar {
	GraphicManager graphicManager;
	MusicManager musicManager;
	JLabel gameExit;
	JLabel mswitch;
	JProgressBar energyBar;
	JProgressBar urineBar;
	JProgressBar flirtBar;
	JProgressBar alcLevelBar;
	JProgressBar funBar;
	JLabel status_mtitle;
	JLabel status_genre;
	JLabel status_uhrzeit;
	JLabel moneyLabel;
	
	public Statusbar(GraphicManager graphicManager, MusicManager musicManager) {
		this.graphicManager = graphicManager;
		this.musicManager = musicManager;
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
	
	public void updateMusic(MusicManager musicmanager) {
		this.status_mtitle.setText(musicmanager.getSongTitle());
		this.status_genre.setText(musicmanager.getSongCategory());
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
		
		moneyLabel.setText("Geld: "+new DecimalFormat("#000.00", DecimalFormatSymbols.getInstance(Locale.GERMAN)).format(player.getMoney())+" Euro");
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
	
	public void setUhrzeit(String text) {
		this.status_uhrzeit.setText(text);
	}
	
	public JLabel addMusicButton(int posX, int posY, int width, int height) {
		mswitch = new JLabel();
		Icon icon = new ImageIcon(
				graphicManager.speaker.getImage(0,0));
		Icon icon_hover = new ImageIcon(
				graphicManager.speaker.getImage(0,1));
		mswitch.setIcon(icon);
		mswitch.setBounds(posX,posY,width,height);
		MouseAction gameExit_l = new MouseAction('m', icon, icon_hover);
		mswitch.addMouseListener(gameExit_l);
		return mswitch;
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
				//System.exit(0);
				GameLogic.getInstance().menu = true;
				GameLogic.getInstance().gameView.menu.setVisible(true);
				GameLogic.getInstance().gameView.menu.setEnabled(true);
				((JLabel) e.getSource()).setIcon(standard);
				break;
			case 'm':
				musicManager.mute(!musicManager.isMute());
				((JLabel) e.getSource()).setIcon((((JLabel) e.getSource()).getIcon().equals(standard)) ? hover : standard);
				break;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(act != 'm') ((JLabel) e.getSource()).setIcon(hover);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(act != 'm') ((JLabel) e.getSource()).setIcon(standard);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
}
