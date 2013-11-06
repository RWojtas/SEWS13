package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import music.MusicManager;

/**
 * @author Nicolas
 */
public class Menu extends JLayeredPane {
	GraphicManager graphicManager;

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

	public Menu(final GraphicManager graphicManager) {
		this.graphicManager = graphicManager;

		int left = 630 * 1366 / 1600;
		int top = 270 * 768 / 900;
		int width = 300;
		int height = 60;
		int height_gab = height + 20;
		
		final MusicManager musicManager = new MusicManager();
		add(musicManager.getPanel());
		musicManager.play();
		
		JLabel speaker = new JLabel();
		Icon speaker_icon = new ImageIcon(graphicManager.speaker.getImage(0,
				0));
		speaker.setIcon(speaker_icon);
		speaker.setBounds(140, 20, 45, 45);
		speaker.addMouseListener(new MouseAdapter() {
			Icon off = new ImageIcon(graphicManager.speaker
					.getImage(0, 1));
			Icon on = new ImageIcon(graphicManager.speaker
					.getImage(0, 0));

			@Override
			public void mouseClicked(MouseEvent e) {
					musicManager.mute(!musicManager.isMute());
					((JLabel) e.getSource()).setIcon((((JLabel) e.getSource()).getIcon().equals(off)) ? on : off);
			}
		});
		speaker.setVisible(true);
		
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
		bg.setBounds(0, 0, 1366, 768);
		add(bg, JLayeredPane.DEFAULT_LAYER);

		JLabel imp_pic = new JLabel(new ImageIcon(
				graphicManager.startImpressum.getImage()));
		impressum = makePopup("Impressum", imp_pic);
		impressum.setVisible(false);
		add(impressum, JLayeredPane.POPUP_LAYER);

		// TODO alle anzeigen!
		ArrayList<Integer> hlist = Highscore.getInstance().readHighscoreFile();
		String bester_stand = (hlist.size() > 0) ? hlist.get(hlist.size() - 1)
				.toString() : " - keine Daten - ";
		JLabel best = new JLabel("Bester Punktestand: " + bester_stand);
		best.setFont(new Font("Ahorani", 0, 30));
		best.setForeground(new Color(128, 0, 0));
		score = makePopup("Bestenliste",best);
		score.setVisible(false);
		add(score, JLayeredPane.POPUP_LAYER);

		
		enableButtonsEvents();
		setBounds(0, 0, 1366, 768);
		setOpaque(false);
		setVisible(true);
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

	private JComponent makePopup(String title, JComponent content) {
		JLayeredPane pan = (JLayeredPane) makePopup(title);
		content.setBounds(10, 80, 680, 300);
		pan.add(content, JLayeredPane.DEFAULT_LAYER);
		pan.moveToFront(content);
		return pan;
	}

	private JComponent makePopup(String title) {
		double screen_width = Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		double screen_height = Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		int cont_width = 700;
		int cont_height = 400;

		JLabel lab = new JLabel(new ImageIcon(
				graphicManager.startPopup.getImage()));
		lab.setBounds(0, 0, cont_width, cont_height);

		JLabel text = new JLabel();
		text.setText(title);
		text.setFont(new Font("Aharoni", 0, 48));
		text.setForeground(new Color(128, 0, 0));
		text.setBounds(35, 20, 300, 48);

		JLabel close = new JLabel();
		Icon close_icon = new ImageIcon(graphicManager.closeButtons.getImage(0,
				0));
		close.setIcon(close_icon);
		close.setBounds(640, 10, 45, 45);
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
				// TODO start game! ;)
				break;
			case 'e':
				System.exit(0);
				break;
			case 'i':
				impressum.setVisible(true);
				disableButtonsEvents();
				break;
			case 'h':
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

	public static void main(String[] args) {
		GraphicManager graphicManager = new GraphicManager();

		JFrame gameView = new JFrame();

		gameView.setTitle("Felse deine Feier");
		gameView.setSize(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		gameView.setUndecorated(true);
		gameView.setAlwaysOnTop(true);
		gameView.setResizable(false);
		gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameView.setVisible(true);
		gameView.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(graphicManager.mouse.getImage(), new Point(gameView.getX(), gameView.getY()), "mouse02"));

		JComponent c = new Menu(graphicManager);

		GroupLayout layout = new GroupLayout(gameView.getContentPane());
		gameView.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(c,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(
						c,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						(int) (Toolkit.getDefaultToolkit().getScreenSize()
								.getHeight()), Short.MAX_VALUE));
		gameView.pack();
		gameView.setVisible(true);
	}
}