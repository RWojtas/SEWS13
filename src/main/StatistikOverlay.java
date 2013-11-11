package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class StatistikOverlay extends JLayeredPane {
	private int gender;
	private int age_years;

	GraphicManager graphicManager;

	public StatistikOverlay(GraphicManager gm, final StatistikModul stats) {
		super();
		this.graphicManager = gm;

		String title = "Willkommen!";

		double screen_width = Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		double screen_height = Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		int cont_width = BufferedImageLoader.scaleToScreenX(700,false);
		int cont_height = BufferedImageLoader.scaleToScreenY(400,false);

		JLabel lab = new JLabel(new ImageIcon(
				graphicManager.startPopup.getImage()));
		lab.setBounds(0, 0, cont_width, cont_height);

		JLabel an = new JLabel("Bitte mach ein paar statistische Angaben:");
		an.setFont(new Font("Aharoni", 0, BufferedImageLoader
				.scaleToScreenY(22,false)));
		an.setForeground(new Color(128, 0, 0));
		an.setBounds(30, 60, cont_width - 60, 100);

		JLabel text = new JLabel(title);
		text.setFont(new Font("Aharoni", 0, BufferedImageLoader
				.scaleToScreenY(48,false)));
		text.setForeground(new Color(128, 0, 0));
		text.setBounds(BufferedImageLoader.scaleToScreenX(35,true),
				BufferedImageLoader.scaleToScreenY(20,true),
				BufferedImageLoader.scaleToScreenX(300,false),
				BufferedImageLoader.scaleToScreenY(48,false));

		setBounds((int) ((screen_width - cont_width) / 2),
				(int) ((screen_height - cont_height) / 2), cont_width,
				cont_height);

		JLabel age_lab = new JLabel();
		age_lab.setBounds(260, 170, 300, 100);
		age_lab.setForeground(new Color(128, 0, 0));
		age_lab.setFont(new Font("Aharoni", Font.BOLD, 90));
		age_lab.setText("Jahre");

		JTextField age = new JTextField();
		age.setForeground(new Color(128, 0, 0));
		age.setFont(new Font("Aharoni", Font.BOLD, 140));
		age.setBounds(90, 160, 140, 100);
		age.setText("");
		age.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				JTextField f = ((JTextField) e.getSource());

				// maximal 2 Zeichen
				if (f.getText().length() > 2)
					f.setText(f.getText().substring(0, 2));
				try {
					age_years = Integer.parseInt(f.getText());
				} catch (NumberFormatException ex) {
					f.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		JLabel ok_m = new JLabel(new ImageIcon(
				graphicManager.statMaennlich.getImage(0, 0)));
		ok_m.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				((JLabel) e.getSource()).setIcon(new ImageIcon(
						graphicManager.statMaennlich.getImage(0, 1)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JLabel) e.getSource()).setIcon(new ImageIcon(
						graphicManager.statMaennlich.getImage(0, 0)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				((JComponent) e.getSource()).getParent().setVisible(false);
				gender = 1;
				stats.saveStatsFile_first(gender, age_years);
			}
		});
		ok_m.setBounds(10, 280, 320, 128);

		JLabel ok_w = new JLabel(new ImageIcon(
				graphicManager.statWeiblich.getImage(0, 0)));
		ok_w.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				((JLabel) e.getSource()).setIcon(new ImageIcon(
						graphicManager.statWeiblich.getImage(0, 1)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((JLabel) e.getSource()).setIcon(new ImageIcon(
						graphicManager.statWeiblich.getImage(0, 0)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				((JComponent) e.getSource()).getParent().setVisible(false);
				gender = 0;
				stats.saveStatsFile_first(gender, age_years);
			}
		});
		ok_w.setBounds(370, 280, 320, 128);

		add(an);
		add(age_lab);
		add(ok_m);
		add(ok_w);
		add(age);
		add(text);
		add(lab);
		setVisible(true);
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
//		StatistikOverlay c = new StatistikOverlay(graphicManager);
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
//		
//		System.out.println(c.getAge()+""+ c.getGender());
//	}
	
	public int getAge() {
		return age_years;
	}
	
	public int getGender() {
		return gender;
	}
}
