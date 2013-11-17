package overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import overlay.BenchOverlay.Act;
import objects.Bench;
import objects.DiscoObject;
import player.AS;
import player.Human;
import player.Player;
import main.BufferedImageLoader;
import main.GraphicManager;
import main.Highscore;

public class FlirtOverlay extends Overlay {
	
	public Player player;
	public AS as;
	JLabel progress;
	JLabel progressText;
	JLabel buttons = new JLabel();
	Act actions = new Act();
	
	public FlirtOverlay(final GraphicManager graphicManager, Player player, String t) {
		super(graphicManager, t);
		this.player = player;
		
		// Buttons
	    buttons = new JLabel();
        buttons.setIcon(new ImageIcon(graphicManager.flirtButton.getImage(0,0)));
        buttons.setBounds(BufferedImageLoader.scaleToScreenX(700,false), BufferedImageLoader.scaleToScreenY(100,false), 
        		BufferedImageLoader.scaleToScreenX(275,false), BufferedImageLoader.scaleToScreenY(55,false));
        actions = new Act(7, new ImageIcon(graphicManager.flirtButton.getImage(0,0)), new ImageIcon(graphicManager.flirtButton.getImage(0,1)));
        add(buttons,JLayeredPane.POPUP_LAYER);
		
		enableActions();
		
		JLabel bench = new JLabel();
		bench.setIcon(new ImageIcon(graphicManager.flirtOverlay.getImage()));
		bench.setBounds(BufferedImageLoader.scaleToScreenX(15,false), BufferedImageLoader.scaleToScreenY(100,false), 
				BufferedImageLoader.scaleToScreenX(660,false), BufferedImageLoader.scaleToScreenY(540,false));
		add(bench,JLayeredPane.POPUP_LAYER);
		
		
		// Progress
		progress = new JLabel();
		progress.setBounds(BufferedImageLoader.scaleToScreenX(15,false), BufferedImageLoader.scaleToScreenY(100,false), 
				BufferedImageLoader.scaleToScreenX(660,false), BufferedImageLoader.scaleToScreenY(540,false));
		progress.setIcon(new ImageIcon(graphicManager.progress0.getImage()));
		progress.setVisible(false);
		add(progress,JLayeredPane.POPUP_LAYER);
		moveToFront(progress);
		
		progressText = new JLabel();
		progressText.setBounds(BufferedImageLoader.scaleToScreenX(15,false), BufferedImageLoader.scaleToScreenY(550,false), 
				BufferedImageLoader.scaleToScreenX(660,false), BufferedImageLoader.scaleToScreenY(150,false));
		progressText.setText("\"Dann macht mal Liebe!\"");
		progressText.setForeground(new Color(128,0,0));
		progressText.setFont(new Font("Aharoni", 0, BufferedImageLoader.scaleToScreenX(30,false)));
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
	
	public void setAS(AS as){
		this.as=as;
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
		public Act() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public void mouseClicked(final MouseEvent e) {
			this.e = e;
			player.setActivityTimer(1000);
			//if(player.getActivityTimer()==0){
			
			new Thread(new Runnable(){
				@Override
				public void run() {
					FlirtOverlay overlay = (FlirtOverlay)((JLabel) e.getSource()).getParent();
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
					
					
					System.out.println(as.getActivity() + "   " + as.getActivityTimer());
					DiscoObject.setStatusES(player, action);
					((JLabel) e.getSource()).getParent().setVisible(false);
					((JLabel) e.getSource()).getParent().setEnabled(false);
					disableActions();
					player.setActivity(0);
					as.setActivity(0);
					as.setActivityTimer(0);
					System.out.println(player.getActivityTimer());
//					bar.openOverlay=false;
					
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
