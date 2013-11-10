package overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import objects.Bar;
import objects.Bench;
import objects.DiscoObject;
import overlay.BarOverlay.Act;
import main.BufferedImageLoader;
import main.GraphicManager;
import player.Player;

public class BenchOverlay extends Overlay{

	public Player player;
	public Bench bench;
	JLabel progress;
	JLabel progressText;
	JLabel buttons = new JLabel();
	Act actions;
	
	public BenchOverlay(final GraphicManager graphicManager, Player player, String t) {
		super(graphicManager, t);
		this.player = player;
		
		// Buttons
	    buttons = new JLabel();
        buttons.setIcon(new ImageIcon(graphicManager.benchButton.getImage(0,0)));
        buttons.setBounds(BufferedImageLoader.scaleToScreenX(700), BufferedImageLoader.scaleToScreenY(100), BufferedImageLoader.scaleToScreenX(275), BufferedImageLoader.scaleToScreenY(55));
        actions = new Act(4, new ImageIcon(graphicManager.benchButton.getImage(0,0)), new ImageIcon(graphicManager.benchButton.getImage(0,1)));
        add(buttons,JLayeredPane.POPUP_LAYER);
		
		enableActions();
		
		
		// Bank
		JLabel bench = new JLabel();
		bench.setIcon(new ImageIcon(graphicManager.benchOverlay.getImage()));
		bench.setBounds(15, 100, 660, 540);
		add(bench,JLayeredPane.POPUP_LAYER);
		
		// Progress
		progress = new JLabel();
		progress.setBounds(15, 100, 660, 540);
		progress.setIcon(new ImageIcon(graphicManager.progress0.getImage()));
		progress.setVisible(false);
		add(progress,JLayeredPane.POPUP_LAYER);
		moveToFront(progress);
		
		progressText = new JLabel();
		progressText.setBounds(15, 550, 660, 150);
		progressText.setText("\"Ja, ja ein wenig Entspannung...\"");
		progressText.setForeground(new Color(128,0,0));
		progressText.setFont(new Font("Aharoni", 0, 30));
		progressText.setHorizontalTextPosition(JLabel.RIGHT);
		progressText.setVisible(false);
		add(progressText,JLayeredPane.POPUP_LAYER);
		moveToFront(progressText);
	}
	
	public void setVisible(boolean on) {
		super.setVisible(on);
		if (actions != null)
			if(on) enableActions();
			else disableActions();
	}
	
	private void enableActions() {
		
		buttons.addMouseListener(actions);
		
	}
	
	private void disableActions() {
		
		buttons.removeMouseListener(actions);
	
	}
	
	class Act extends MouseAdapter {
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
					BenchOverlay overlay = (BenchOverlay)((JLabel) e.getSource()).getParent();
					overlay.progress.setVisible(true);
					overlay.progressText.setVisible(true);
					overlay.close.setVisible(false);
					while(player.getActivityTimer()>0) {
//						System.out.println("Event"+player.getActivityTimer());
						
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
							Thread.sleep(40);
						} catch (InterruptedException e1) {}
					}
					DiscoObject.setStatusES(player, action);
					((JLabel) e.getSource()).getParent().setVisible(false);
					((JLabel) e.getSource()).getParent().setEnabled(false);
					disableActions();
					player.setActivity(0);	
					System.out.println(player.getActivityTimer());

					
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
