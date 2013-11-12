package overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import objects.Bar;
import objects.DiscoObject;
import player.Player;
import main.BufferedImageLoader;
import main.GraphicManager;
import main.Highscore;

public class BarOverlay extends Overlay {
	/*  11 - BlauHohn
	 *  12 - RotOchsen
	 *  13 - GelbV�gln
	 *  14 - SchwarzKatzerl
	 *  15 - ZitronenLimonade
	 *  16 - Eistee
	 *  17 - Cocktail
	 *  18 - Shot
	 */
	static final int NUM_BUT = 8;
	public Player player;
	public Bar bar;
	JLabel progress;
	JLabel progressText;
	JLabel buttons[] = new JLabel[NUM_BUT];
	Act actions[] = new Act[NUM_BUT];
	
	public BarOverlay(final GraphicManager graphicManager, Player player, String t) {
		super(graphicManager, t);
		this.player = player;
		
		// Buttons
		for(int i=0;i<NUM_BUT;i++) {
            buttons[i] = new JLabel();
            buttons[i].setIcon(new ImageIcon(graphicManager.drinkButtons.getImage(0,i)));
            buttons[i].setBounds(BufferedImageLoader.scaleToScreenX(700+i%3*95,false), BufferedImageLoader.scaleToScreenY(100+i/3*182,false), BufferedImageLoader.scaleToScreenX(90,false), BufferedImageLoader.scaleToScreenY(176,false));
            actions[i] = new Act(11+i, new ImageIcon(graphicManager.drinkButtons.getImage(0,i)), new ImageIcon(graphicManager.drinkButtons.getImage(1,i)));
            add(buttons[i],JLayeredPane.POPUP_LAYER);
		}
		enableActions();
		
		// Barkeeper
		JLabel barkeeper = new JLabel();
		barkeeper.setIcon(new ImageIcon(graphicManager.barkeeper.getImage()));
		barkeeper.setBounds(15, 100, 660, 540);
		add(barkeeper,JLayeredPane.POPUP_LAYER);
		
		// Progress
		progress = new JLabel();
		progress.setBounds(15, 100, 660, 540);
		progress.setIcon(new ImageIcon(graphicManager.progress0.getImage()));
		progress.setVisible(false);
		add(progress,JLayeredPane.POPUP_LAYER);
		moveToFront(progress);
		
		progressText = new JLabel();
		progressText.setBounds(15, 550, 660, 150);
		progressText.setText("\"Na dann, Prost!\"");
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
		for(int i=0;i<NUM_BUT;i++) {
			buttons[i].addMouseListener(actions[i]);
		}
	}
	
	private void disableActions() {
		for(int i=0;i<NUM_BUT;i++) {
			buttons[i].removeMouseListener(actions[i]);
		}
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
					BarOverlay overlay = (BarOverlay)((JLabel) e.getSource()).getParent();
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
