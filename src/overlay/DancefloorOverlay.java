package overlay;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.BufferedImageLoader;
import main.GraphicManager;
import objects.Dancefloor;
import objects.DiscoObject;
import player.Player;

public class DancefloorOverlay extends Overlay{


	public Player player;
	public Dancefloor dancefloor;
	JLabel button;
	Act action;
	public DancefloorOverlay(GraphicManager graphicManager, Player player, String t) {
		super(graphicManager, t);
		this.player=player;
		
		button = new JLabel();
		button.setIcon(new ImageIcon(graphicManager.dancefloorButton.getImage(0,0)));
		button.setBounds(BufferedImageLoader.scaleToScreenX(700), BufferedImageLoader.scaleToScreenY(100), BufferedImageLoader.scaleToScreenX(275), BufferedImageLoader.scaleToScreenY(55));
		action = new Act(2, new ImageIcon(graphicManager.dancefloorButton.getImage(0,0)), new ImageIcon(graphicManager.dancefloorButton.getImage(0,1)));
	}
	public void setVisible(boolean on) {
		super.setVisible(on);
		if (action != null)
			if(on) enableActions();
			else disableActions();
	}
	
	private void enableActions() {
			button.addMouseListener(action);
	}
	
	private void disableActions() {
			button.removeMouseListener(action);
	}
	
	class Act extends MouseAdapter{
		ImageIcon i;
		ImageIcon h;
		int action;
		MouseEvent e;
		public Act(int action, ImageIcon i, ImageIcon h) {
			this.i = i;
			this.h = h;
			this.action = action;
		}
		@Override
		public void mouseClicked(final MouseEvent e) {
			this.e = e;
			player.setActivityTimer(1000);
			//if(player.getActivityTimer()==0){
			new Thread(new Runnable(){
				@Override
				public void run() {
					Dancefloor overlay = (Dancefloor)((JLabel) e.getSource()).getParent();
					while(player.getActivityTimer()>0) {						
						try {
							Thread.sleep(20);
						} catch (InterruptedException e1) {}
					}
					DiscoObject.setStatusES(player, action);
					((JLabel) e.getSource()).getParent().setVisible(false);
					((JLabel) e.getSource()).getParent().setEnabled(false);
					disableActions();
					player.setActivity(0);
					
					// Reset
					((JLabel) e.getSource()).setIcon(i);
				}
			}).start();;
				
			//}
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

