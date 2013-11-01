import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
public class Menu extends JLayeredPane {
	public Menu(GraphicManager graphicManager) {
		JLabel mouse = new JLabel();
		mouse.setVisible(true);
		mouse.setText("ads");
		mouse.setAlignmentX(LEFT_ALIGNMENT);
		mouse.setAlignmentY(TOP_ALIGNMENT);
		mouse.setBounds(0, 740, 100, 10);
		add(mouse, 100);
		
		JLabel start = new JLabel();
		start.setIcon(new ImageIcon(graphicManager.startMenueButtons[0].getImage()));
		start.setBounds(659-120, 275, 295, 56);
		start.addMouseListener(new StartAction(start, graphicManager, this));
		add(start, 100);
		
		JLabel menuBack = new JLabel();
		menuBack.setIcon(new ImageIcon(graphicManager.startMenueBG.getImage()));
		menuBack.setBounds(0, 0, 1366, 768);
		menuBack.setVisible(true);
		add(menuBack, 10);

		setBounds(0,0,1366,768);
		setVisible(true);
		addMouseMotionListener(new MouseMove(mouse));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(graphicManager.mouse.getImage(), new Point(this.getX(), this.getY()), "mouse01"));
	}
	
	class MouseMove implements MouseMotionListener {
		JLabel l;
		public MouseMove(JLabel l) {
			this.l = l;
		}

		@Override
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {
			l.setText("X:"+e.getX()+" Y:"+e.getY());
		}
		
	}
	
	class StartAction implements MouseListener {
		private JLabel a;
		private GraphicManager graphicManager;
		private JLayeredPane p;
		public StartAction(JLabel a, GraphicManager g, JLayeredPane p) {
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
			a.setIcon(new ImageIcon(graphicManager.startMenueButtons[1].getImage()));
		}

		@Override
		public void mouseExited(MouseEvent e) {
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