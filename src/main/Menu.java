package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import music.MusicManager;

/**
 * @author Nicolas
 */
public class Menu extends JLayeredPane {
	GraphicManager graphicManager;
	StatistikModul Stats = new StatistikModul();
	StatistikOverlay Stats_show;

	JComponent score;
	JComponent impressum;

	JLabel start;
	JLabel high;
	JLabel impr;
	JLabel end;
	JLabel bg;
	JLabel speaker;

	MouseAction start_l;
	MouseAction high_l;
	MouseAction impr_l;
	MouseAction end_l;
	MouseAction speaker_l;

	JTextArea highscorelist;
	
	MusicManager musicManager;

	public Menu(final GraphicManager graphicManager, final MusicManager musicManager) {
		this.graphicManager = graphicManager;
		this.musicManager = musicManager;

		int left = BufferedImageLoader.scaleToScreenX(630 * 1366 / 1600,false);
		int top = BufferedImageLoader.scaleToScreenY(270 * 768 / 900,false);
		int width = BufferedImageLoader.scaleToScreenX(300,false);
		int height = BufferedImageLoader.scaleToScreenY(60,false);
		int height_gab = BufferedImageLoader.scaleToScreenY(height + 20,false);
		
		
		add(musicManager.getPanel());
		musicManager.play();
		
		final JLabel song = new JLabel(musicManager.getSongTitle() + " (" + musicManager.getSongCategory() + ")");
		song.setBounds(BufferedImageLoader.scaleToScreenX(200,true),BufferedImageLoader.scaleToScreenY(20,true),BufferedImageLoader.scaleToScreenX(200,false),BufferedImageLoader.scaleToScreenY(45,false));
		song.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					musicManager.next();
					song.setText(musicManager.getSongTitle() + " (" + musicManager.getSongCategory() + ")");
			}
		});
		
		speaker = new JLabel();
		Icon speaker_icon = new ImageIcon(graphicManager.speaker.getImage(0,
				0));
		speaker.setIcon(speaker_icon);
		speaker.setBounds(BufferedImageLoader.scaleToScreenX(140,true), BufferedImageLoader.scaleToScreenY(20,true), BufferedImageLoader.scaleToScreenX(45,false), BufferedImageLoader.scaleToScreenY(45,false));
		speaker.addMouseListener(new MouseAdapter() {
			Icon off = new ImageIcon(graphicManager.speaker
					.getImage(0, 1));
			Icon on = new ImageIcon(graphicManager.speaker
					.getImage(0, 0));

			@Override
			public void mouseClicked(MouseEvent e) {
					musicManager.mute(!musicManager.isMute());
//					((JLabel) e.getSource()).setIcon((((JLabel) e.getSource()).getIcon().equals(off)) ? on : off);
					speaker.setIcon(musicManager.isMute() ? off: on);
					GameLogic.getInstance().statusbar.mswitch.setIcon(musicManager.isMute() ? off: on);
			}
		});
		speaker.setVisible(true);
		
		add(song, JLayeredPane.DEFAULT_LAYER);
		add(speaker, JLayeredPane.DEFAULT_LAYER);
		
		start = new JLabel();
		Icon start_icon = new ImageIcon(
				graphicManager.startMenueButtons.getImage(0, 0));
		Icon start_icon_hover = new ImageIcon(
				graphicManager.startMenueButtons.getImage(0, 1));
		start.setIcon(start_icon);
		start.setBounds(left, top, width, height);
		start_l = new MouseAction('s', start_icon, start_icon_hover);
		add(start, JLayeredPane.DEFAULT_LAYER);

		high = new JLabel();
		Icon high_icon = new ImageIcon(
				graphicManager.startMenueButtons.getImage(0, 4));
		Icon high_icon_hover = new ImageIcon(
				graphicManager.startMenueButtons.getImage(0, 5));
		high.setIcon(high_icon);
		high.setBounds(left, top + height_gab, width, height);
		high_l = new MouseAction('h', high_icon, high_icon_hover);
		add(high, JLayeredPane.DEFAULT_LAYER);

		impr = new JLabel();
		Icon impr_icon = new ImageIcon(
				graphicManager.startMenueButtons.getImage(0, 2));
		Icon impr_icon_hover = new ImageIcon(
				graphicManager.startMenueButtons.getImage(0, 3));
		impr.setIcon(impr_icon);
		impr.setBounds(left, top + 2 * height_gab, width, height);
		impr_l = new MouseAction('i', impr_icon, impr_icon_hover);
		add(impr, JLayeredPane.DEFAULT_LAYER);

		end = new JLabel();
		Icon end_icon = new ImageIcon(
				graphicManager.startMenueButtons.getImage(0, 6));
		Icon end_icon_hover = new ImageIcon(
				graphicManager.startMenueButtons.getImage(0, 7));
		end.setIcon(end_icon);
		end.setBounds(left, top + 4 * height_gab, width, height);
		end_l = new MouseAction('e', end_icon, end_icon_hover);
		add(end, JLayeredPane.DEFAULT_LAYER);

		bg = new JLabel(new ImageIcon(graphicManager.startMenueBG.getImage()));
		bg.setBounds(0, 0, BufferedImageLoader.scaleToScreenX(1366,false), BufferedImageLoader.scaleToScreenY(768,false));
		add(bg, JLayeredPane.DEFAULT_LAYER);

		JLabel imp_pic = new JLabel(new ImageIcon(
				graphicManager.startImpressum.getImage()));
		impressum = makePopup("Impressum", imp_pic);
		impressum.setVisible(false);
		add(impressum, JLayeredPane.POPUP_LAYER);
			
		highscorelist = new JTextArea();
		highscorelist.setEditable(false);
		highscorelist.setBackground(new Color(0,0,0,0));
		highscorelist.setFocusable(false);
		highscorelist.setText(getHighscoreList());
		highscorelist.setFont(new Font("Ahorani", 0, BufferedImageLoader.scaleToScreenY(20,false)));
		highscorelist.setForeground(new Color(128, 0, 0));
		highscorelist.setHighlighter(null);
		
		score = makePopup("Bestenliste",highscorelist);
		score.setVisible(false);
		add(score, JLayeredPane.POPUP_LAYER);

		
		enableButtonsEvents();
		setBounds(BufferedImageLoader.scaleToScreenX(0,true),
				BufferedImageLoader.scaleToScreenY(0,true),
				BufferedImageLoader.scaleToScreenX(1366,false),
				BufferedImageLoader.scaleToScreenY(768,false));
		setOpaque(false);
		setVisible(true);
	}
	
	public String getHighscoreList() {
		ArrayList<Integer> hlist = Highscore.getInstance().readHighscoreFile();
		String list = "";
		int i = 1;
		for(Integer v : hlist) {
			list += String.format("%-5s",i+")")+v.toString()+"\n";
			i++;
			if(i>10) {
				break;
			}
		}
		return list;
	}

	private void disableButtonsEvents() {
		start.removeMouseListener(start_l);
		high.removeMouseListener(high_l);
		end.removeMouseListener(end_l);
		impr.removeMouseListener(impr_l);
	}

	private void enableButtonsEvents() {
		start.addMouseListener(start_l);
		high.addMouseListener(high_l);
		end.addMouseListener(end_l);
		impr.addMouseListener(impr_l);
	}
	
	public void setVisible(boolean on) {
		super.setVisible(on);
		if(on) enableButtonsEvents();
		else disableButtonsEvents();
		
		speaker.setIcon((musicManager.isMute()) ? new ImageIcon(graphicManager.speaker
				.getImage(0, 1)) : new ImageIcon(graphicManager.speaker
				.getImage(0, 0)));
	}

	private JComponent makePopup(String title, JComponent content) {
		JLayeredPane pan = (JLayeredPane) makePopup(title);
		content.setBounds(BufferedImageLoader.scaleToScreenX(30,false), 
				BufferedImageLoader.scaleToScreenY(100,false),
				BufferedImageLoader.scaleToScreenX(680,false),
				BufferedImageLoader.scaleToScreenY(300,false));
		pan.add(content, JLayeredPane.DEFAULT_LAYER);
		pan.moveToFront(content);
		return pan;
	}

	private JComponent makePopup(String title) {
		double screen_width = Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		double screen_height = Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		int cont_width = BufferedImageLoader.scaleToScreenX(700,false);
		int cont_height = BufferedImageLoader.scaleToScreenY(400,false);

		JLabel lab = new JLabel(new ImageIcon(
				graphicManager.startPopup.getImage()));
		lab.setBounds(0, 0, cont_width, cont_height);

		JLabel text = new JLabel();
		text.setText(title);
		text.setFont(new Font("Aharoni", 0, BufferedImageLoader.scaleToScreenY(48,false)));
		text.setForeground(new Color(128, 0, 0));
		text.setBounds(BufferedImageLoader.scaleToScreenX(35,false), BufferedImageLoader.scaleToScreenY(20,false), BufferedImageLoader.scaleToScreenX(300,false), BufferedImageLoader.scaleToScreenY(48,false));

		JLabel close = new JLabel();
		Icon close_icon = new ImageIcon(graphicManager.closeButtons.getImage(0,
				0));
		close.setIcon(close_icon);
		close.setBounds(BufferedImageLoader.scaleToScreenX(640,false), BufferedImageLoader.scaleToScreenY(10,false), BufferedImageLoader.scaleToScreenX(45,false), BufferedImageLoader.scaleToScreenY(45,false));
		close.addMouseListener(new MouseAdapter() {
			Icon icon_close = new ImageIcon(graphicManager.closeButtons
					.getImage(0, 1));
			Icon icon = new ImageIcon(graphicManager.closeButtons
					.getImage(0, 0));

			@Override
			public void mouseEntered(MouseEvent e) {
				((JLabel) e.getSource()).setIcon(icon_close);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JLabel) e.getSource()).setIcon(icon);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				((JComponent) e.getSource()).getParent().setVisible(false);
				enableButtonsEvents();
			}
		});

		JLayeredPane pan = new JLayeredPane();
		pan.setBounds((int) ((screen_width - cont_width) / 2),
				(int) ((screen_height - cont_height) / 2), cont_width,
				cont_height);
		pan.add(text, JLayeredPane.DEFAULT_LAYER);
		pan.add(close, JLayeredPane.DEFAULT_LAYER);
		pan.add(lab, JLayeredPane.DEFAULT_LAYER);
		pan.setVisible(true);
		
		return pan;
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
			case 's':
				GameLogic.getInstance().initialized = false;
				setVisible(false);
				disableButtonsEvents();	
				GameLogic.getInstance().menu = false;
				GameLogic.setMusicManager(musicManager);
				GameLogic.getInstance().updateMusic();
				break;
			case 'e':
				System.exit(0);
				break;
			case 'i':
				impressum.setVisible(true);
				disableButtonsEvents();
				break;
			case 'h':
				highscorelist.setText(getHighscoreList());
				score.setVisible(true);
				disableButtonsEvents();
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

//	public static void main(String[] args) {
//		GraphicManager graphicManager = new GraphicManager();
//
//		JFrame gameView = new JFrame();
//
//		gameView.setTitle("Felse deine Feier");
//		gameView.setSize(
//				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
//				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
//		gameView.setUndecorated(true);
//		gameView.setAlwaysOnTop(true);
//		gameView.setResizable(false);
//		gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		gameView.setVisible(true);
//		gameView.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//				graphicManager.mouse.getImage(),
//				new Point(gameView.getX(), gameView.getY()), "mouse02"));
//
//		JComponent c = new Menu(graphicManager);
//
//		GroupLayout layout = new GroupLayout(gameView.getContentPane());
//		gameView.getContentPane().setLayout(layout);
//		layout.setHorizontalGroup(layout.createParallelGroup(
//				javax.swing.GroupLayout.Alignment.LEADING).addComponent(c,
//				javax.swing.GroupLayout.DEFAULT_SIZE,
//				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
//				Short.MAX_VALUE));
//		layout.setVerticalGroup(layout.createParallelGroup(
//				javax.swing.GroupLayout.Alignment.LEADING)
//				.addComponent(
//						c,
//						javax.swing.GroupLayout.DEFAULT_SIZE,
//						(int) (Toolkit.getDefaultToolkit().getScreenSize()
//								.getHeight()), Short.MAX_VALUE));
//		gameView.pack();
//		gameView.setVisible(true);
//	}
}
