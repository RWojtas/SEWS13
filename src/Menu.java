import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Menu extends JLayeredPane {
	public Menu(GraphicManager graphicManager) {
		int left = 630*1366/1600;
		int top = 270*768/900;
		int width = 300;
		int height = 60;
		int height_gab = height + 20;
		
		setOpaque(true);

		
		JLabel start = new JLabel();
		Icon start_icon = new ImageIcon(graphicManager.startMenueButtons.getImage(0,0));
		Icon start_icon_hover = new ImageIcon(graphicManager.startMenueButtons.getImage(0,1));
		start.setIcon(start_icon);
		start.setBounds(left, top, width, height);
		start.addMouseListener(new MouseAction('s', start_icon, start_icon_hover));
		add(start, 100);

		JLabel high = new JLabel();
		Icon high_icon = new ImageIcon(graphicManager.startMenueButtons.getImage(0,4));
		Icon high_icon_hover = new ImageIcon(graphicManager.startMenueButtons.getImage(0,5));
		high.setIcon(high_icon);
		high.setBounds(left, top+height_gab, width, height);
		high.addMouseListener(new MouseAction('h', high_icon, high_icon_hover));
		add(high, 110);
		
		JLabel impr = new JLabel();
		Icon impr_icon = new ImageIcon(graphicManager.startMenueButtons.getImage(0,2));
		Icon impr_icon_hover = new ImageIcon(graphicManager.startMenueButtons.getImage(0,3));
		impr.setIcon(impr_icon);
		impr.setBounds(left, top+2*height_gab, width, height);
		impr.addMouseListener(new MouseAction('i', impr_icon, impr_icon_hover));
		add(impr, 102);
		
		JLabel end = new JLabel();
		Icon end_icon = new ImageIcon(graphicManager.startMenueButtons.getImage(0,6));
		Icon end_icon_hover = new ImageIcon(graphicManager.startMenueButtons.getImage(0,7));
		end.setIcon(end_icon);
		end.setBounds(left, top+4*height_gab, width, height);
		end.addMouseListener(new MouseAction('e', end_icon, end_icon_hover));
		add(end, 103);
		
		JLabel bg = new JLabel(new ImageIcon(graphicManager.startMenueBG.getImage()));
		bg.setBounds(0, 0, 1366, 768);
		add(bg, 10);
		
		
		setBounds(0,0,1366,768);
		setVisible(true);
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
			switch(act) {
				case 's':
					break;
				case 'e':
					System.exit(0);
					break;
				case 'i':
					break;
				case 'h':
					break;
			}
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
	    
	    gameView.add(new Menu(graphicManager));
	}
}