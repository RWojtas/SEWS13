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
		button.setIcon(new ImageIcon(graphicManager.dancefloorButton.getImage(0,0)));
		button.setBounds(BufferedImageLoader.scaleToScreenX(700), BufferedImageLoader.scaleToScreenY(100), BufferedImageLoader.scaleToScreenX(275), BufferedImageLoader.scaleToScreenY(55));
		action = new Act(2, new ImageIcon(graphicManager.dancefloorButton.getImage(0,0)), new ImageIcon(graphicManager.dancefloorButton.getImage(0,1)));
		add(button,JLayeredPane.POPUP_LAYER);
		
		// Dancefloor
		JLabel dancefloor = new JLabel();
		dancefloor.setIcon(new ImageIcon(graphicManager.dancefloorOverlay.getImage()));
		dancefloor.setBounds(15, 100, 660, 540);
		add(dancefloor,JLayeredPane.POPUP_LAYER);
		
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
						
						switch(player.getActivityTimer()*5/1000) {
						case 3:
							overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress1.getImage()));
							break;
						case 2:
							overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress2.getImage()));
							break;
						case 1:
							overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress3.getImage()));
							break;
						case 0:
							overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress4.getImage()));
					}
						
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
					overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress0.getImage()));
					overlay.progress.setVisible(false);
					overlay.progressText.setVisible(false);
					overlay.close.setVisible(true);
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

