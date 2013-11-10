package overlay;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import objects.DiscoObject;
import player.Human;
import player.Player;
import main.BufferedImageLoader;
import main.GraphicManager;
import main.Highscore;

public class FlirtOverlay extends Overlay {
	
	public Player player;
	public Human human;
	JLabel buttons = new JLabel();
	Act actions = new Act();
	
	public FlirtOverlay(final GraphicManager graphicManager, Player player, String t) {
		super(graphicManager, t);
		this.player = player;
		
		buttons = new JLabel();
		buttons.setIcon(new ImageIcon(graphicManager.ButtonFlirten.getImage().getSubimage(BufferedImageLoader.scaleToScreenX(0), BufferedImageLoader.scaleToScreenY(60), BufferedImageLoader.scaleToScreenX(60),BufferedImageLoader.scaleToScreenY(60))));
		buttons.setBounds(BufferedImageLoader.scaleToScreenX(700+4%3*95), BufferedImageLoader.scaleToScreenY(100+4/3*180), BufferedImageLoader.scaleToScreenX(90), BufferedImageLoader.scaleToScreenY(176));
		add(buttons,JLayeredPane.POPUP_LAYER);
	}
	
	private void disableActions() {
		
			buttons.removeMouseListener(actions);
		
	}
	
	class Act extends MouseAdapter {
		ImageIcon i;
		ImageIcon h;
		int action;
		public Act(int action, ImageIcon i, ImageIcon h) {
			this.i = i;
			this.h = h;
			this.action = action;
		}
		public Act() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			DiscoObject.setStatusES(player, action);
			((JLabel) e.getSource()).getParent().setVisible(false);
			((JLabel) e.getSource()).getParent().setEnabled(false);
			disableActions();
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

}
