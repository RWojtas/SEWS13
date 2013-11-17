package overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import player.Player;
import main.BufferedImageLoader;
import main.GraphicManager;
import music.MusicManager;

public class Overlay extends JLayeredPane {
	protected GraphicManager graphicManager;
	private JLabel title;
	protected JLabel close;
	private JLabel bg;
	
	public Overlay(final GraphicManager graphicManager, String t) {
		this.graphicManager = graphicManager;
		
		double screen_width = Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		double screen_height = Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		int cont_width = BufferedImageLoader.scaleToScreenX(1000,false);
		int cont_height = BufferedImageLoader.scaleToScreenY(650,false);

		bg = new JLabel(new ImageIcon(
				graphicManager.popup1000x600.getImage()));
		bg.setBounds(0, 0, cont_width, cont_height);
		
		title = new JLabel();
		title.setText(t);
		title.setFont(new Font("Aharoni", 0, BufferedImageLoader.scaleToScreenY(48,false)));
		title.setForeground(new Color(128, 0, 0));
		title.setBounds(BufferedImageLoader.scaleToScreenX(35,false), BufferedImageLoader.scaleToScreenY(20,false), 
				BufferedImageLoader.scaleToScreenX(700,false), BufferedImageLoader.scaleToScreenY(48,false));
		
		close = new JLabel();
		Icon close_icon = new ImageIcon(graphicManager.closeButtons.getImage(0,
				0));
		close.setIcon(close_icon);
		close.setBounds(cont_width - BufferedImageLoader.scaleToScreenX(60,false), BufferedImageLoader.scaleToScreenY(10,false), 
				BufferedImageLoader.scaleToScreenX(45,false), BufferedImageLoader.scaleToScreenY(45,false));
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
			}
		});
		
		add(title, JLayeredPane.DEFAULT_LAYER);
		add(close, JLayeredPane.DEFAULT_LAYER);
		add(bg, JLayeredPane.DEFAULT_LAYER);
		
		setBounds((int) ((screen_width - cont_width) / 2),
				(int) ((screen_height - cont_height) / 2), cont_width,
				cont_height);
		setVisible(true);
	}
	
	class A extends MouseAdapter {
		ImageIcon i;
		ImageIcon h;
		public A(ImageIcon i, ImageIcon h) {
			this.i = i;
			this.h = h;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			((JLabel) e.getSource()).setIcon(h);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			((JLabel) e.getSource()).setIcon(i);
		}
	}
	
	
//	public static void main(String[] args) {
//		GraphicManager graphicManager = new GraphicManager();
//		MusicManager m = new MusicManager();
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
//		JComponent c = new DJOverlay(graphicManager, "DJ", m);
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
