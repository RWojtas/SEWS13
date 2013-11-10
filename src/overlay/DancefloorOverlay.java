package overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.BufferedImageLoader;
import main.GraphicManager;
import objects.Dancefloor;
import objects.DiscoObject;
import player.Player;

public class DancefloorOverlay extends Overlay{


	public Player player;
	public Dancefloor dancefloor;
	JLabel button;
	JLabel progress;
	JLabel progressText;
	Act action;
	public DancefloorOverlay(GraphicManager graphicManager, Player player, String t) {
		super(graphicManager, t);
		this.player=player;
		
		button = new JLabel();
		//button.setIcon(new ImageIcon(graphicManager.dancefloorButton.getImage(0,0)));
		//button.setBounds(BufferedImageLoader.scaleToScreenX(), BufferedImageLoader.scaleToScreenY(), BufferedImageLoader.scaleToScreenX(), BufferedImageLoader.scaleToScreenY());
		//action = new Act(2, new ImageIcon(graphicManager.dancefloorButton.getImage(0,0)), new ImageIcon(graphicManager.dancefloorButton.getImage(0, 60)));
		
		// Progress
		progress = new JLabel();
		progress.setBounds(15, 100, 660, 540);
		progress.setIcon(new ImageIcon(graphicManager.progress0.getImage()));
		progress.setVisible(false);
		add(progress,JLayeredPane.POPUP_LAYER);
		moveToFront(progress);
		
		progressText = new JLabel();
		progressText.setBounds(15, 550, 660, 150);
		progressText.setText("\"Auf geht's, ab geht's, 3 Tage wach!\"");
		progressText.setForeground(new Color(128,0,0));
		progressText.setFont(new Font("Aharoni", 0, 30));
		progressText.setHorizontalTextPosition(JLabel.RIGHT);
		progressText.setVisible(false);
		add(progressText,JLayeredPane.POPUP_LAYER);
		moveToFront(progressText);
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
					DancefloorOverlay overlay = (DancefloorOverlay)((JLabel) e.getSource()).getParent();
					overlay.progress.setVisible(true);
					overlay.progressText.setVisible(true);
					overlay.close.setVisible(false);
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

