import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Menu extends JLayeredPane {
	public Menu(GraphicManager graphicManager) {
		int left = 640*1366/1600;
		int top = 270*768/900;
		int width = 300;
		int height = 60;
		
		JLabel start = new JLabel();
		Icon start_icon = new ImageIcon(graphicManager.startMenueButtons.getImage(0,0));
		Icon start_icon_hover = new ImageIcon(graphicManager.startMenueButtons.getImage(1,0));
		start.setIcon(start_icon);
		start.setBounds(left, top, width, height);
		start.addMouseListener(new MouseAction(start_icon, start_icon_hover));
		add(start, 100);
		
		JLabel end = new JLabel();
		Icon end_icon = new ImageIcon(graphicManager.startMenueButtons.getImage(2,0));
		Icon end_icon_hover = new ImageIcon(graphicManager.startMenueButtons.getImage(3,0));
		end.setIcon(start_icon);
		end.setBounds(left, top, width, height);
		end.addMouseListener(new MouseAction(end_icon, end_icon_hover));
		add(end, 100);
		
		add(new JLabel("test"));
		
		setBounds(0,0,1366,768);
		setVisible(true);
	}
	
	class MouseAction implements MouseListener {
		private Icon standard;
		private Icon hover;
		
		public MouseAction(Icon standard, Icon hover) {
			this.standard = standard;
			this.hover = hover;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO start game
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
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
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
	    gameView.add(new JLabel("test"));
	    gameView.add(new Menu(graphicManager));
	}
}