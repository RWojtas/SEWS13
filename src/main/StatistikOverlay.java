package main;

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
import javax.swing.JTextField;


public class StatistikOverlay extends JLayeredPane {
	public StatistikOverlay(GraphicManager graphicManager, String title) {
		super();
		
		double screen_width = Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		double screen_height = Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		int cont_width = BufferedImageLoader.scaleToScreenX(700);
		int cont_height = BufferedImageLoader.scaleToScreenY(400);

		JLabel lab = new JLabel(new ImageIcon(
				graphicManager.startPopup.getImage()));
		lab.setBounds(0, 0, cont_width, cont_height);

		JLabel text = new JLabel();
		text.setText(title);
		text.setFont(new Font("Aharoni", 0, BufferedImageLoader.scaleToScreenY(48)));
		text.setForeground(new Color(128, 0, 0));
		text.setBounds(BufferedImageLoader.scaleToScreenX(35), BufferedImageLoader.scaleToScreenY(20), BufferedImageLoader.scaleToScreenX(300), BufferedImageLoader.scaleToScreenY(48));

		

		setBounds((int) ((screen_width - cont_width) / 2),
				(int) ((screen_height - cont_height) / 2), cont_width,
				cont_height);
		
		JTextField age = new JTextField();
		age.setForeground(new Color(128,0,0));
		age.setFont(new Font("Aharoni", Font.BOLD, 140));
		age.setBounds(10, 100, 140, 100);
		
		JLabel ok = new JLabel(new ImageIcon());
		ok.setBounds(10, 400, 200, 200);
		
		
		add(ok);
		add(age);
		add(text);
		add(lab);
		setVisible(true);
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
	gameView.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
			graphicManager.mouse.getImage(),
			new Point(gameView.getX(), gameView.getY()), "mouse02"));

	JComponent c = new StatistikOverlay(graphicManager, "as");

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
