import java.awt.AWTEvent;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;




import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {
	public Menu(GraphicManager graphicManager) {
		JLabel menuBack = new JLabel();
		menuBack.setIcon(new ImageIcon(graphicManager.startMenueBG.getImage()));
		menuBack.setBounds(0, 0, 1366, 768);
		menuBack.setVisible(true);
		menuBack.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(graphicManager.mouse.getImage(), new Point(this.getX(),
			     this.getY()), "mouse01"));
		
		add(menuBack);
		
		JButton start = new JButton();
		start.setIcon(new ImageIcon(graphicManager.startMenueButtons[0].getImage()));
		start.setBounds(559, 275, 295, 56);
		start.setPressedIcon(new ImageIcon(graphicManager.startMenueButtons[1].getImage()));
		start.setBorderPainted(false);
		start.addMouseListener(new StartAction(start, graphicManager,this));
		add(start);
		
		
		
		setBounds(0,0,1366,768);
		setVisible(true);
	}
	
	class StartAction implements MouseListener {
		private JButton a;
		private GraphicManager graphicManager;
		private JPanel p;
		public StartAction(JButton a, GraphicManager g, JPanel p) {
			this.a = a;
			this.graphicManager = g;
			this.p = p;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Klick!");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("Enter!");
			p.setCursor(new Cursor(Cursor.HAND_CURSOR));
			a.setIcon(new ImageIcon(graphicManager.startMenueButtons[1].getImage()));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			p.setCursor(Cursor.getDefaultCursor());
			a.setIcon(new ImageIcon(graphicManager.startMenueButtons[0].getImage()));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static void main(String[] args) {
		GraphicManager graphicManager = new GraphicManager();
		
		JFrame gameView = new JFrame();
		
		gameView.setTitle("Felse deine Feier");
	    gameView.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
	    gameView.setUndecorated(true);
	    gameView.setAlwaysOnTop(true);
	    gameView.setResizable(false);
	    gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gameView.setVisible(true);
	    
	    gameView.add(new Menu(graphicManager));
	}
}